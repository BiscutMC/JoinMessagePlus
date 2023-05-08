package eu.andycraftz.joinmessageplus.bungeecord;

import java.util.logging.Level;

import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import org.bstats.bungeecord.Metrics;

/**
 * JoinMessagePlus - Simple Join-Message Plugin
 *
 * @author AndyCraftz <info@andycraftz.eu>
 * @version 3.4
 */
public class JoinMessagePlus extends Plugin {

    // Config
    public Config cfg;
    public BungeeAudiences bungeeAudiences;

    @Override
    public void onEnable() {
        // Message
        getLogger().log(Level.INFO, "[#]==========< JoinMessagePlus >==========[#]");
        getLogger().log(Level.INFO, "Version: {0}", getDescription().getVersion());
        getLogger().log(Level.INFO, "Web: https://dev.bukkit.org/bukkit-plugins/join-message-plus/");
        getLogger().log(Level.INFO, "Plugin by AndyCrafz");
        // Metrics
        Metrics metrics = new Metrics(this, 3041);
        // Config
        cfg = new Config(this);
        // Events
        PluginManager pm = getProxy().getPluginManager();
        pm.registerListener(this, new MessagesL(this));
        this.bungeeAudiences = BungeeAudiences.create(this);
    }

    @Override
    public void onDisable() {
        this.bungeeAudiences.close();
        this.bungeeAudiences = null;
    }
}
