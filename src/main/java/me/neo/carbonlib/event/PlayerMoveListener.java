package me.neo.carbonlib.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    /**
     * Registers does the Logic for the PlayerJumpEvent. Nothing else really important.
     * To use PlayerJumpEvent do
     * CarbonEvent.registerEvent(PlayerJumpEvent.class, event -> {
     *     logic
     * });
     */
    public void onMove() {
        CarbonEvent.registerEvent(PlayerMoveEvent.class, event -> {
            double y1 = event.getFrom().getY();
            double y2 = event.getTo().getY();
            Player player = event.getPlayer();
            if (player.isInsideVehicle() || y1 >= y2 || y2 - y1 < 0.4D || ((Entity) player).isOnGround() || y2-y1 == 0.5D ||
                    player.getWorld().getBlockAt(player.getLocation()).getType().toString().contains("WATER") ||
                    player.getWorld().getBlockAt(player.getLocation()).getType().toString().contains("LADDER") ||
                    player.getWorld().getBlockAt(player.getLocation()).getType().toString().contains("VINE")) return;
            PlayerJumpEvent playerJumpEvent = new PlayerJumpEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent((Event)playerJumpEvent);
            if (playerJumpEvent.isCancelled()) {
                event.setCancelled(true);
                return;
            }
            event.setFrom(playerJumpEvent.getFromLocation());
            event.setTo(playerJumpEvent.getResultLocation());
        });
    }


}
