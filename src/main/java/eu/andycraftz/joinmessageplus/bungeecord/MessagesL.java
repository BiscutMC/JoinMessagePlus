package eu.andycraftz.joinmessageplus.bungeecord;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Objects;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @version 3.4
 */
public class MessagesL implements Listener {

    private final JoinMessagePlus plugin;
    private final MiniMessage miniMessage;
    private final String Syntax;
    private final String JoinMessage;
    private final String QuitMessage;

    private final boolean JoinMessageEnabled;
    private final boolean QuitMessageEnabled;
    private final BungeeComponentSerializer Serializer;

    public MessagesL(JoinMessagePlus plugin) {
        this.plugin = plugin;
        this.miniMessage = MiniMessage.miniMessage();

        Syntax = this.plugin.cfg.getConfig().getString("Syntax", "minimessage");

        JoinMessage = this.plugin.cfg.getConfig().getString("GlobalJoinMessage.Message");
        assert JoinMessage != null;

        QuitMessage = this.plugin.cfg.getConfig().getString("GlobalQuitMessage.Message");
        assert QuitMessage != null;

        JoinMessageEnabled = this.plugin.cfg.getConfig().getBoolean("GlobalJoinMessage.Enabled");
        QuitMessageEnabled = this.plugin.cfg.getConfig().getBoolean("GlobalQuitMessage.Enabled");

        Serializer = BungeeComponentSerializer.get();
    }

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        if (JoinMessageEnabled) {
            if (Objects.equals(Syntax, "minimessage")) {
                plugin.getProxy().broadcast(new TextComponent(JoinMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
            } else {
                Component component = miniMessage.deserialize(JoinMessage, Placeholder.component("player_name", Component.text(e.getPlayer().getName())), Placeholder.component("player_displayname", Component.text(e.getPlayer().getDisplayName())));
                plugin.getProxy().broadcast(Serializer.serialize(component));
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        if (QuitMessageEnabled) {
            if (Objects.equals(Syntax, "minimessage")) {
                plugin.getProxy().broadcast(new TextComponent(QuitMessage.replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
            } else {
                Component component = miniMessage.deserialize(QuitMessage, Placeholder.component("player_name", Component.text(e.getPlayer().getName())), Placeholder.component("player_displayname", Component.text(e.getPlayer().getDisplayName())));
                plugin.getProxy().broadcast(Serializer.serialize(component));
            }

        }
    }
}
