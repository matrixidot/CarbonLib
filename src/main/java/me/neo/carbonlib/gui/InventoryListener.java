package me.neo.carbonlib.gui;

import me.neo.carbonlib.gui.misc.IHolder;
import me.neo.carbonlib.gui.misc.InventoryCache;
import me.neo.carbonlib.gui.misc.InventoryObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Optional;
import java.util.UUID;

public class InventoryListener implements Listener {

    @EventHandler
    public void playerItemConsume(PlayerItemConsumeEvent e) {
        InventoryHolder holder = e.getPlayer().getOpenInventory().getTopInventory().getHolder();
        e.setCancelled(holder instanceof IHolder);
    }

    @EventHandler
    public void collectToCursor(InventoryClickEvent e) {
        if (e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR) && e.getInventory().getHolder() instanceof IHolder) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory inventory = e.getClickedInventory();
        if (inventory != null) {
            if (inventory.getHolder() instanceof IHolder) {
                System.out.println(inventory.getHolder() instanceof IHolder);
                UUID uuid = e.getWhoClicked().getUniqueId();
                System.out.println(InventoryCache.getCache().getLastInventory(e.getClickedInventory()));
                InventoryCache.getCache().getLastInventory(e.getClickedInventory()).ifPresent(builder -> {
                    builder.getBuilder().getClickEventConsumer().accept(e);
                    if (builder.isMainBuilder()) {
                        builder.getBuilder().getItem(e.getSlot()).ifPresent(item -> item.getClickAction().accept(e));
                    }
                });
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof IHolder) {
            UUID uuid = e.getWhoClicked().getUniqueId();
            InventoryCache.getCache().getLastInventory(e.getView().getTopInventory()).ifPresent(builder -> {
                builder.getBuilder().getDragEventConsumer().accept(e);
                if (builder.isMainBuilder()) {
                    e.getInventorySlots().forEach(slot -> builder.getBuilder().getItem(slot).ifPresent(item -> item.getDragAction().accept(e)));
                }
            });
        }
    }

    @EventHandler
    public void  onClose(InventoryCloseEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        InventoryCache cache = InventoryCache.getCache();
        Optional<InventoryObject> optional = cache.getLastInventory(e.getView().getTopInventory());
        cache.removeLastInventory(e.getView().getTopInventory());
        optional.ifPresent(builder -> builder.getBuilder().getCloseEventConsumer().accept(e));
    }

}
