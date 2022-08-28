package me.neo.carbonlib.misc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CarbonSound {
    private Sound sound;
    private Entity entity;
    private SoundCategory category;
    private float volume;
    private float pitch;
    private List<Player> receivers;
    private Location loc;
    public CarbonSound(Sound sound) {
        this.sound = sound;
    }

    public CarbonSound setSound(Sound sound) {
        this.sound = sound;
        return this;
    }
    public Sound getSound() {
        return this.sound;
    }

    public CarbonSound setTarget(Entity target) {
        this.entity = entity;
        return this;
    }
    public Entity getTarget() {
        return entity;
    }

    public CarbonSound setCategory(SoundCategory category) {
        this.category = category;
        return this;
    }
    public SoundCategory getCategory() {
        return category;
    }

    public CarbonSound setVolume(float volume) {
        this.volume = volume;
        return this;
    }
    public float getVolume() {
        return volume;
    }

    public CarbonSound setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }
    public float getPitch() {
        return pitch;
    }

    public CarbonSound setReceivers(List<Player> receivers) {
        this.receivers = receivers;
        return this;
    }
    public List<Player> getReceivers() {
        return receivers;
    }

    public CarbonSound setLocation(Location location) {
        this.loc = location;
        return this;
    }
    public Location getLocation() {
        return loc;
    }

    public CarbonSound playToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            play(player);
        }
        return this;
    }
    public CarbonSound play() {
        return play(getLocation());
    }
    public CarbonSound play(Location loc) {
        if (receivers == null) return this;
        receivers.forEach(player -> play(player, loc));
        return this;
    }
    public CarbonSound play(@NotNull Player player, @NotNull Location loc) {
        if (sound == null) return this;
        if (category == null) player.playSound(loc, sound, volume, pitch);
        else player.playSound(loc, sound, category, volume, pitch);
        return this;
    }
    public CarbonSound play(@NotNull Player player) {
        return play(player, player.getLocation());
    }

}
