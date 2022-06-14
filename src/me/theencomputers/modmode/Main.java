package me.theencomputers.modmode;
import me.theencomputers.modmode.Commands.ModMode;
import me.theencomputers.modmode.Commands.Vanish;
import me.theencomputers.modmode.Events.ModModeItems;
import me.theencomputers.modmode.Events.VanishEvents;
import me.theencomputers.modmode.Events.ModInfo.UnexposedListener;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	static Main plugin;

	public void onEnable(){
		plugin = this;
		Logger.getLogger("Minecraft").info("Mod Mode has been Enabled");
	 		Bukkit.getServer().getPluginManager().registerEvents((Listener) new VanishEvents(), this);
			Bukkit.getServer().getPluginManager().registerEvents((Listener) new ModModeItems(plugin), this);
			Bukkit.getServer().getPluginManager().registerEvents((Listener) new UnexposedListener(), this);

	 		getCommand("modmode").setExecutor(new ModMode());
            getCommand("vanish").setExecutor(new Vanish());

	}
	public static Main getMainInstance(){
        return plugin;
    }

}