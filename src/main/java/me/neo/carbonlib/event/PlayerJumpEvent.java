package me.neo.carbonlib.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJumpEvent extends PlayerEvent implements Cancellable {
    private boolean cancel = false;
    public static HandlerList handlerList = new HandlerList();
    private Location fromLocation;
    private Location resultLocation;

    /**
     * Constructor for PlayerJumpEvent
     * @param player The player that jumped
     * @param initialLoc The initial Location of the player
     * @param resultLoc The resulting location of the player
     */
    public PlayerJumpEvent(Player player, @NotNull Location initialLoc, @NotNull Location resultLoc) {
        super(player);
        this.fromLocation = initialLoc;
        this.resultLocation = resultLoc;
    }

    /**
     * Checks if the event is cancelled
     * @return True if it is cancelled, false if it isnt
     */
    public boolean isCancelled() {
        return this.cancel;
    }

    /**
     * cancels the event. No use of setting it to false as the event was already not cancelled.
     * @param cancel True or false
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the HandlerList of this Event
     * @return The HandlerList
     */
    public HandlerList getHandlersList() {
        return handlerList;
    }

    /**
     * Gets the HandlerList of this Event
     * @return The HandlerList
     */
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     * Gets the location the player started at.
     * @return Location of when the player started moving
     */
    public Location getFromLocation() {
        return this.fromLocation;
    }

    /**
     * Sets the location the player started from
     * @param fromLoc The starting Location of the player
     */
    public void setFromLocation(Location fromLoc) {
        if (fromLoc != null)
            this.fromLocation = fromLoc;
    }

    /**
     * Gets the resulting location of the player after they moved
     * @return The resulting Location
     */
    public Location getResultLocation() {
        return this.resultLocation;
    }

    /**
     * Sets the location the player started from
     * @param resultLoc The Location the player started from
     */
    public void setResultLocation(Location resultLoc) {
        this.resultLocation = resultLoc;
    }
}
