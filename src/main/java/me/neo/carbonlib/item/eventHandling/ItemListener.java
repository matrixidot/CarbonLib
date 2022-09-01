package me.neo.carbonlib.item.eventHandling;

import me.neo.carbonlib.gui.IHolder;
import me.neo.carbonlib.plugin.AbstractCarbon;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ItemListener implements Listener {
    private NamespacedKey key = new NamespacedKey(AbstractCarbon.getPlugin(AbstractCarbon.class), "Custom");
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType().isAir()) return;
        // Checks if the cache does not contain the item the player was holding. If it does not then it returns otherwise it continues
        if (!CarbonItemCache.getCache().hasItem(Objects.requireNonNull(e.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(key, PersistentDataType.STRING)))
            return;
        Player player = e.getPlayer();
        String item = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
        // Creates the builder and does the simple consumer logic. Can add more events later.
        CarbonItemCache.getCache().getItem(item).ifPresent(builder -> {
            if (e.getAction() == Action.RIGHT_CLICK_AIR) builder.getBuilder().getRightClick().accept(e);
            else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) builder.getBuilder().getRightClickBlock().accept(e);
            else if (e.getAction() == Action.LEFT_CLICK_AIR) builder.getBuilder().getLeftClick().accept(e);
            else if (e.getAction() == Action.LEFT_CLICK_BLOCK) builder.getBuilder().getLeftClickBlock().accept(e);
        });
    }


        @EventHandler
        public void onInvClick (InventoryClickEvent e){
            Inventory inventory = e.getClickedInventory();
            if (inventory != null) {
                if (inventory.getHolder() instanceof IHolder) {
                    CarbonItemCache.getCache().getInvItem(e.getCurrentItem()).ifPresent(inventoryItem -> {
                        inventoryItem.getInventoryItem().getClickAction().accept(e);
                        e.setCancelled(true);
                    });
                }
            }
        }
        @EventHandler
        public void onInvDrag (InventoryDragEvent e){
            Inventory inventory = e.getInventory();
            if (inventory != null) {
                if (inventory.getHolder() instanceof IHolder) {
                    CarbonItemCache.getCache().getInvItem(e.getCursor()).ifPresent(inventoryItem -> {
                        inventoryItem.getInventoryItem().getDragAction().accept(e);
                        e.setCancelled(true);
                    });
                }
            }
        }

}
