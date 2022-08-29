package me.neo.carbonlib.item;

import me.neo.carbonlib.event.CarbonEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener {
    public ItemListener() {
        CarbonEvent.registerEvent(PlayerInteractEvent.class, e -> {
            Player player = e.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            System.out.println(item);
            // Checks if the cache does not contain the item the player was holding. If it does not then it returns otherwise it continues
            if (!CarbonItemCache.getCache().hasItem(item)) return;
            System.out.println("is a custom item");
            // Creates the builder and does the simple consumer logic. Can add more events later.
            CarbonItemCache.getCache().getItem(item).ifPresent(builder -> {
                System.out.println(builder);
                System.out.println(e.getAction());
                switch (e.getAction()) {
                    case RIGHT_CLICK_AIR -> {
                        builder.getBuilder().getRightClick().accept(e);
                    }
                    case RIGHT_CLICK_BLOCK -> {
                        builder.getBuilder().getRightClickBlock().accept(e);
                    }
                    case LEFT_CLICK_AIR -> {
                        builder.getBuilder().getLeftClick().accept(e);
                    }
                    case LEFT_CLICK_BLOCK -> {
                        builder.getBuilder().getLeftClickBlock().accept(e);
                    }
                }
            });
        });
    }
}
