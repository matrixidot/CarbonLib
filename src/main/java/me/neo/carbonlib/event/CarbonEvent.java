package me.neo.carbonlib.event;

import me.neo.carbonlib.CarbonAPI;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public class CarbonEvent {
    private static final Plugin carbon = CarbonAPI.getInstance();

    /**
     * Registers an Event with an Event class
     * @param clazz The event class
     * @param consumer Consumer for Type parameter E
     * @param <E> Type parameter E referring to the event
     */
    public static <E extends Event> void registerEvent(Class<E> clazz, Consumer<E> consumer) {
        registerEvent(clazz, EventPriority.NORMAL, consumer);
    }

    /**
     * Registers an Event with an Event class and an EventPriority
     * @param clazz The event class
     * @param priority Event priority
     * @param consumer The consumer for Type Parameter E
     * @param <E> Type paremeter E referring to the event
     */
    public static <E extends Event> void registerEvent(Class<E> clazz, EventPriority priority, Consumer<E> consumer) {
        carbon.getServer().getPluginManager().registerEvent(clazz, new Listener() {}, priority, (l, e) -> consumer.accept((E) e), carbon, true);
    }




}
