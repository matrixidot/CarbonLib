package me.neo.carbonlib.misc;

import org.bukkit.entity.Player;

import java.util.List;

public class CarbonTitle {
    private String title;
    private String subTitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;
    private List<Player> viewers;
    public CarbonTitle(String title) {
        this.title = title;
    }

    public CarbonTitle setTitle(String title) {
        this.title = title;
        return this;
    }

    public CarbonTitle setSubtitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }
    public CarbonTitle setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }
    public CarbonTitle setStay(int stay) {
        this.stay = stay;
        return this;
    }
    public CarbonTitle setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }
    public CarbonTitle addViewer(Player viewer) {
        this.viewers.add(viewer);
        return this;
    }
    public CarbonTitle setViewers(List<Player> viewers) {
        this.viewers = viewers;
        return this;
    }
    public List<Player> getViewers() {
        return viewers;
    }
    public CarbonTitle display() {
        if (viewers == null) return this;
        viewers.forEach(player -> player.sendTitle(this.title, this.subTitle, this.fadeIn, this.stay, this.fadeOut));
        return this;
    }


}
