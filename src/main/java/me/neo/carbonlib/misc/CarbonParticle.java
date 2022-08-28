package me.neo.carbonlib.misc;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class CarbonParticle {
    private float offsetX, offsetY, offsetZ;
    private int amount;
    private Particle particle;
    private Location loc;
    private List<Player> viewers;
    private CarbonScheduler animatedTask;
    public CarbonParticle(Particle particle) {
        this.particle = particle;
    }

    public CarbonParticle setLocation(Location loc) {
        this.loc = loc;
        return this;
    }
    public Location getLocation() {
        return this.loc;
    }

    public CarbonParticle setOffset(Vector offset) {
        this.offsetX = (float) offset.getX();
        this.offsetY = (float) offset.getY();
        this.offsetZ = (float) offset.getZ();
        return this;
    }

    public CarbonParticle setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        return this;
    }
    public float getOffsetX() {
        return offsetX;
    }

    public CarbonParticle setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        return this;
    }
    public float getOffsetY() {
        return offsetY;
    }

    public CarbonParticle setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
        return this;
    }
    public float getOffsetZ() {
        return offsetZ;
    }

    public CarbonParticle setParticle(Particle particle) {
        this.particle = particle;
        return this;
    }
    public Particle getParticle() {
        return particle;
    }

    public CarbonParticle setViewers(List<Player> viewers) {
        this.viewers = viewers;
        return this;
    }
    public CarbonParticle addViewer(Player viewer) {
        this.viewers.add(viewer);
        return this;
    }
    public List<Player> getViewers() {
        return viewers;
    }

    public CarbonParticle display() {
        return display(getViewers());
    }
    public <T> CarbonParticle display(T specialData) {
        return display(getViewers(), specialData);
    }
    public <T> CarbonParticle display(List<Player> players, T specialData) {
        if (players == null) return this;
        if (loc == null) return this;
        players.forEach(player -> player.spawnParticle(particle, loc.getX(), loc.getY(), loc.getZ(), amount, offsetX, offsetY, offsetZ, specialData));
        return this;
    }

    public CarbonParticle display(Player player) {
        if (player == null) return this;
        if (loc == null) return this;
        player.spawnParticle(particle, loc.getX(), loc.getY(), loc.getZ(), amount, offsetX, offsetY, offsetZ);
        return this;
    }
    public CarbonParticle display(List<Player> players) {
        if (players == null) return this;
        players.forEach(this::display);
        return this;
    }
    public CarbonParticle animateDisplay(int amount, int delay) {
        final int[] i = {0};
        animatedTask = new CarbonScheduler(r -> {
            if (i[0] >= amount) r.cancel();
            display();
            i[0]++;
        }).runTaskTimerAsynchronously(0, delay);
        return this;
    }

    public CarbonParticle displayByNearPlayers(Player origin, int reach) {
        if (origin == null) return this;
        List<Player> players = new ArrayList<>(List.of(origin));
        for (Entity entity : origin.getNearbyEntities(reach, reach, reach)) {
            if (entity instanceof Player) {
                players.add((Player) entity);
            }
        }
        return display(players);

    }

    public void cancelAnimatedTask() {
        animatedTask.cancel();
    }

}
