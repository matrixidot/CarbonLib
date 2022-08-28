package me.neo.carbonlib.gui;

import me.neo.carbonlib.event.CarbonEvent;
import me.neo.carbonlib.gui.misc.CarbonInventoryCache;
import me.neo.carbonlib.gui.misc.CarbonInventoryObject;
import me.neo.carbonlib.gui.misc.IHolder;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Optional;
import java.util.UUID;

public class InventoryListener {

    public InventoryListener() {
        CarbonEvent.registerEvent(PlayerItemConsumeEvent.class, e -> {
            InventoryHolder holder = e.getPlayer().getOpenInventory().getTopInventory().getHolder();
            e.setCancelled(holder instanceof IHolder);
        });

        CarbonEvent.registerEvent(InventoryClickEvent.class, e -> {
            if (e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR) && e.getInventory().getHolder() instanceof IHolder) e.setCancelled(true);
        });

        CarbonEvent.registerEvent(InventoryClickEvent.class, e -> {
            Inventory inventory = e.getClickedInventory();
            if (inventory == null) return;
            if (!(inventory.getHolder() instanceof IHolder)) return;
            UUID uuid = e.getWhoClicked().getUniqueId();
            CarbonInventoryCache.getCache().getLastInventory(uuid).ifPresent(builder -> {
                builder.getBuilder().getClickEvent().accept(e);
                if (builder.isMainBuilder()) builder.getBuilder().getItem(e.getSlot()).ifPresent(item -> item.getClickAction().accept(e));
                else builder.getPageBuilder().getItem(e.getSlot()).ifPresent(item -> item.getClickAction().accept(e));
            });
        });

        CarbonEvent.registerEvent(InventoryDragEvent.class, e -> {
            if (!(e.getInventory().getHolder() instanceof IHolder)) return;
            UUID uuid = e.getWhoClicked().getUniqueId();
            CarbonInventoryCache.getCache().getLastInventory(uuid).ifPresent(builder -> {
                builder.getBuilder().getDragEvent().accept(e);
                if (builder.isMainBuilder()) e.getInventorySlots().forEach(slot -> builder.getBuilder().getItem(slot).ifPresent(item -> item.getDragAction().accept(e)));
                else e.getInventorySlots().forEach(slot -> builder.getPageBuilder().getItem(slot).ifPresent(item -> item.getDragAction().accept(e)));
            });
        });

        CarbonEvent.registerEvent(InventoryCloseEvent.class, e -> {
            UUID uuid = e.getPlayer().getUniqueId();
            CarbonInventoryCache cache = CarbonInventoryCache.getCache();
            Optional<CarbonInventoryObject> optional = cache.getLastInventory(uuid);
            cache.removeLastInventory(uuid);
            optional.ifPresent(builder -> builder.getBuilder().getCloseEvent().accept(e));
        });
    }
}
