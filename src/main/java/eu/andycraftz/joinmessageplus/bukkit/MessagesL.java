package eu.andycraftz.joinmessageplus.bukkit;

import com.earth2me.essentials.User;

import fr.xephi.authme.events.LoginEvent;

import me.clip.placeholderapi.PlaceholderAPI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @version 3.4
 */
public class MessagesL implements Listener {

    private final JoinMessagePlus plugin;
    private final MiniMessage miniMessage;

    private final String JoinMessage;
    private final String QuitMessage;

    private final boolean JoinMessageEnabled;
    private final boolean QuitMessageEnabled;

    public MessagesL(JoinMessagePlus plugin) {
        String QuitMessage1;
        this.plugin = plugin;
        this.miniMessage = MiniMessage.miniMessage();

        if (this.plugin.bungeesupport) {
            JoinMessage = "";
            QuitMessage = "";
        } else {
            JoinMessage = this.plugin.cfg.getConfig().getString("JoinMessage.Message");
            assert JoinMessage != null;

            QuitMessage = this.plugin.cfg.getConfig().getString("QuitMessage.Message");
            assert QuitMessage != null;
        }

        JoinMessageEnabled = this.plugin.cfg.getConfig().getBoolean("JoinMessage.Enabled");
        QuitMessageEnabled = this.plugin.cfg.getConfig().getBoolean("QuitMessage.Enabled");

        PluginManager pm = Bukkit.getPluginManager();
        if (this.plugin.authmeapi) {
            pm.registerEvents(new AuthMeL(), this.plugin);
        }
    }

    private void sendMessage(Player p, Player target, String msg) {
        Component component = miniMessage.deserialize(JoinMessage, Placeholder.component("player_name", Component.text(p.getName())), Placeholder.component("player_displayname", Component.text(p.getDisplayName())));
        Bukkit.getConsoleSender().sendMessage(component); // Send message also to console
        target.sendMessage(component);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        if (!plugin.bungeesupport) {
            if (JoinMessageEnabled) {
                if (plugin.authmeapi) {
                    return;
                }
                if (plugin.essentialsapi) {
                    final User user = plugin.essentialinstance.getUser(e.getPlayer());
                    if (user.isHidden()) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            if (target.hasPermission("essentials.vanish.see")) {
                                sendMessage(e.getPlayer(), target, JoinMessage);
                            }
                        }
                        return;
                    }
                }
                for (Player target : Bukkit.getOnlinePlayers()) {
                    sendMessage(e.getPlayer(), target, JoinMessage);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        if (!plugin.bungeesupport) {
            if (QuitMessageEnabled) {
                if (plugin.authmeapi) {
                    if (!plugin.authmeapiinstance.isAuthenticated(e.getPlayer())) {
                        return;
                    }
                }
                if (plugin.essentialsapi) {
                    final User user = plugin.essentialinstance.getUser(e.getPlayer());
                    if (user.isHidden()) {
                        Bukkit.getOnlinePlayers().stream().filter(target -> (target.hasPermission("essentials.vanish.see"))).forEachOrdered(target -> sendMessage(e.getPlayer(), target, QuitMessage));
                        return;
                    }
                }
                Bukkit.getOnlinePlayers().forEach(target -> sendMessage(e.getPlayer(), target, QuitMessage));
            }
        }
    }

    public class AuthMeL implements Listener {
        @EventHandler
        public void onLogin(LoginEvent e) {
            if (!plugin.bungeesupport) {
                if (JoinMessageEnabled) {
                    if (!plugin.authmeapi) {
                        return;
                    }
                    if (plugin.essentialsapi) {
                        final User user = plugin.essentialinstance.getUser(e.getPlayer());
                        if (user.isHidden()) {
                            Bukkit.getOnlinePlayers().stream().filter(target -> (target.hasPermission("essentials.vanish.see"))).forEachOrdered(target -> sendMessage(e.getPlayer(), target, JoinMessage));
                            return;
                        }
                    }
                    Bukkit.getOnlinePlayers().forEach(target -> sendMessage(e.getPlayer(), target, JoinMessage));
                }
            }
        }
    }

}
