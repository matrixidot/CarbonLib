package me.neo.carbonlib.item.eventHandling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void register(PlayerInteractEvent e) {
        // Checks if the cache does not contain the item the player was holding. If it does not then it returns otherwise it continues
            if (!CarbonItemCache.getCache().hasItem(e.getPlayer().getInventory().getItemInMainHand())) return;
            Player player = e.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            // Creates the builder and does the simple consumer logic. Can add more events later.
            CarbonItemCache.getCache().getItem(item).ifPresent(builder -> {
                if (e.getAction() == Action.RIGHT_CLICK_AIR) builder.getBuilder().getRightClick().accept(e);
                else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) builder.getBuilder().getRightClickBlock().accept(e);
                else if (e.getAction() == Action.LEFT_CLICK_AIR) builder.getBuilder().getLeftClick().accept(e);
                else if (e.getAction() == Action.LEFT_CLICK_BLOCK) builder.getBuilder().getLeftClickBlock().accept(e);
            });
    }
}
