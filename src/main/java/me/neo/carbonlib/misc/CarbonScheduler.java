package me.neo.carbonlib.misc;

import me.neo.carbonlib.CarbonAPI;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.function.Consumer;

public class CarbonScheduler extends BukkitRunnable implements Serializable {
    private Consumer<CarbonScheduler> consumer;
    private BukkitTask task;

    @Override
    public void run() {
        this.consumer.accept(this);
    }

    public CarbonScheduler(Consumer<CarbonScheduler> consumer) {
        this.consumer = consumer;
    }

    public CarbonScheduler set(Consumer<CarbonScheduler> consumer) {
        this.consumer = consumer;
        return this;
    }

    @Contract("_ -> new")
    public static @NotNull CarbonScheduler get(Consumer<CarbonScheduler> consumer) {
        return new CarbonScheduler(consumer);
    }

    public boolean cancelTask() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
            return true;
        }
        return false;
    }

    // Runs a task on a different thread after the specified delay
    @NotNull
    public synchronized CarbonScheduler runTaskLaterAsynchronously(long delay) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskLaterAsynchronously(CarbonAPI.getInstance(), delay);
        return this;
    }
    // Runs a bukkit task
    @NotNull
    public synchronized CarbonScheduler runTask() throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTask(CarbonAPI.getInstance());
        return this;
    }
    // Runs a task on the main thread after the specified delay
    @NotNull
    public synchronized CarbonScheduler runTaskLater(long delay) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskLater(CarbonAPI.getInstance(), delay);
        return this;
    }
    // Runs a repeating task on a different thread after specified delay and in specified intervals
    @NotNull
    public synchronized CarbonScheduler runTaskTimerAsynchronously(long delay, long period) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskTimerAsynchronously(CarbonAPI.getInstance(), delay, period);
        return this;
    }
    // Runs a repeating task on a different thread after specified delay and in delay intervals
    @NotNull
    public synchronized CarbonScheduler runTaskTimerAsynchronously(long delay) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskTimerAsynchronously(CarbonAPI.getInstance(), delay, delay);
        return this;
    }
    // Runs a repeating task on the main thread after a specified delay and in specified intervals
    @NotNull
    public synchronized CarbonScheduler runTaskTimer(long delay, long period) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskTimer(CarbonAPI.getInstance(), delay, period);
        return this;
    }
    // Runs a repeating task on the main thread after specified delay and in delay intervals
    @NotNull
    public synchronized CarbonScheduler runTaskTimer(long delay) throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskTimer(CarbonAPI.getInstance(), delay, delay);
        return this;
    }
    // Runs a task on a different thread
    @NotNull
    public synchronized CarbonScheduler runTaskAsynchronously() throws IllegalArgumentException, IllegalStateException {
        this.task = super.runTaskAsynchronously(CarbonAPI.getInstance());
        return this;
    }

}
