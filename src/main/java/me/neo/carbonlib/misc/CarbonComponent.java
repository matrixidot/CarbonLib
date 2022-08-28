package me.neo.carbonlib.misc;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;

public class CarbonComponent {
    private String message;
    private HoverEvent hoverEvent;
    private ClickEvent clickEvent;

    public CarbonComponent(String message) {
        this.message = message;
    }

    public TextComponent build() {
        TextComponent temp = new TextComponent(message);
        if (hoverEvent != null) {
            temp.setHoverEvent(hoverEvent);
        }

        if (clickEvent != null) {
            temp.setClickEvent(clickEvent);
        }
        return temp;
    }

    public String getMessage() {
        return message;
    }

    public CarbonComponent setMessage(String message) {
        this.message = message;
        return this;
    }

    public CarbonComponent setHoverEvent(HoverEvent.Action action, Content content) {
        this.hoverEvent = new HoverEvent(action, content);
        return this;
    }

    public CarbonComponent setHoverEvent(HoverEvent.Action action, String text) {
        return setHoverEvent(action, getTextContent(text));
    }

    public CarbonComponent setClickEvent(ClickEvent.Action action, String string) {
        this.clickEvent = new ClickEvent(action, string);
        return this;
    }

    public Content getTextContent(String s) {
        return new Text(s);
    }

    public Content getItemContent(String id, int count, ItemTag tag) {
        return new Item(id, count, tag);
    }
}
