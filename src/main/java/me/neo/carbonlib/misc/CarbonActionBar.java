package me.neo.carbonlib.misc;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CarbonActionBar implements Serializable {
    private String text;
    private CarbonComponent actionBar;
    private List<Player> receivers;
    private CarbonScheduler runnable;

    /**
     * The Constructor for CarbonActionBar
     * @param actionBar The CarbonComponent which is used as the message of the ActionBar
     */
    public CarbonActionBar(CarbonComponent actionBar) {
        this.actionBar = actionBar;
    }

    /**
     * Sets the text to be displayed on the action bar
     * @param text The text for the Action Bar
     */
    public CarbonActionBar (String text) {
        this.text = text;
    }

    /**
     * Sends an ActionBar message to a list of receivers
     * @return CarbonActionBar
     */
    public CarbonActionBar send() {
        receivers.forEach(this::send);
        return this;
    }

    /**
     * Sends the ActionBar message to a player
     * @param player The Player to send an actionBar Message to
     * @return CarbonActionBar
     */
    public CarbonActionBar send(@NotNull Player player) {
        return send(Collections.singleton(player), actionBar);
    }

    /**
     * Sends the ActionBar message to a specified player(s) with a specified CarbonComponent as the message
     * @param players The Player(s) to send an ActionBar message to
     * @param component the CarbonComponent used as the message of the ActionBar
     * @return CarbonActionBar
     */
    public CarbonActionBar send(Collection<Player> players, CarbonComponent component) {
        if (actionBar == null) return this;
        for (Player player : players) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component.build());
        }
        return this;
    }

    /**
     * Sends the ActionBar to a specified player with a specified text
     * @param player The Player to send the ActionBar message to
     * @param text The Text of the ActionBar messge
     * @return CarbonActionBar
     */
    public CarbonActionBar send(Player player, String text) {
        return send(Collections.singleton(player), new CarbonComponent(text));
    }

    /**
     * Gets the ActionBar as a CarbonComponent
     * @return The CarbonComponent as the message of the ActionBar
     */
    public CarbonComponent getActionBar() {
        return actionBar;
    }

    /**
     * Sets the actionBar message to a CarbonComponent
     * @param actionBar The CarbonComponent to be used as an ActionBar
     * @return CarbonActionBar
     */
    public CarbonActionBar setActionBar(CarbonComponent actionBar) {
        this.actionBar = actionBar;
        return this;
    }

    /**
     * Returns the list of players who can see the actionBar MEssage
     * @return The List of players
     */
    public List<Player> getReceivers() {
        return receivers;
    }

    /**
     * Sets the people who can see the action bar
     * @param receivers A list of people who can see the action bat
     * @return
     */
    public CarbonActionBar setReceivers(List<Player> receivers) {
        this.receivers = receivers;
        return this;
    }

    /**
     * Adds a player to the list of receivers
     * @param receiver The player to add
     * @return CarbonActionBar
     */
    public CarbonActionBar addReceiver(Player receiver) {
        this.receivers.add(receiver);
        return this;
    }

    /**
     * Sends An actionBar message to the specified players for times number of times
     * @param times The number of times to repeat this message
     * @return CarbonActionBar
     */
    public CarbonActionBar sendLooped(int times) {
        final int[] i = {times};
        runnable = new CarbonScheduler(r -> {
            if (i[0] < 1) {
                sendEmpty();
                r.cancel();
            } else {
                build(text.replace("%times%", String.valueOf(i[0]))).send();
            }
            i[0]--;

        }).runTaskTimerAsynchronously(0, 20);
        return this;
    }

    /**
     * Sends an empty message to the players' Action bars
     * @return CarbonActionBar
     */
    public CarbonActionBar sendEmpty() {
        receivers.forEach(player -> send(Collections.singleton(player), new CarbonComponent("&l")));
        return this;
    }

    /**
     * Cancels the looping ActionBar message
     */
    public void cancel() {
        runnable.cancel();
    }

    /**
     * Gets the message of the actionBar
     * @return String text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the action bar to be displayed
     * @param text The Message to be sent as a string
     * @return CarbonActionBar
     */
    public CarbonActionBar setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Transforms a String into a CarbonComponent to be used in the ActionBar
     * @param text The String to be transformed
     * @return CarbonActionBar
     */
    public CarbonActionBar build(String text) {
        actionBar = new CarbonComponent(text);
        return this;
    }

    /**
     * Builds the set Message into a CarbonComponent
     * @return Calls the build(String text) method to transform the set text into a CarbonComponent
     */
    public CarbonActionBar build() {
        return build(text);
    }

}
