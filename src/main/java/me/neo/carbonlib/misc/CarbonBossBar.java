package me.neo.carbonlib.misc;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CarbonBossBar {
    private BossBar bossBar;

    public CarbonBossBar() {
        createBar();
    }

    private void createBar() {
        this.bossBar = Bukkit.createBossBar(null, BarColor.WHITE, BarStyle.SOLID, BarFlag.CREATE_FOG);
    }

    public CarbonBossBar setTitle(String title) {
        this.bossBar.setTitle(title);
        return this;
    }

    public CarbonBossBar setColor(BarColor color) {
        this.bossBar.setColor(color);
        return this;
    }

    public CarbonBossBar setStyle(BarStyle style) {
        this.bossBar.setStyle(style);
        return this;
    }

    public CarbonBossBar addFlag(BarFlag flag) {
        this.bossBar.addFlag(flag);
        return this;
    }
    public CarbonBossBar addFlags(List<BarFlag> flags) {
        for (BarFlag flag : flags) {
            this.bossBar.addFlag(flag);
        }
        return this;
    }

    public CarbonBossBar addPlayer(Player player) {
        this.bossBar.addPlayer(player);
        return this;
    }
    public CarbonBossBar addPlayers(List<Player> players) {
        for (Player player : players) {
            this.bossBar.addPlayer(player);
        }
        return this;
    }


    public CarbonBossBar setProgress(double progress) {
        this.bossBar.setProgress(progress);
        return this;
    }

    public BossBar display(boolean display) {
        this.bossBar.setVisible(display);
        return this.bossBar;
    }

}
