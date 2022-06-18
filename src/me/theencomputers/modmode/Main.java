/*
	Author: Theencomputers
	Title: Main.java
*/

/*
    Author: Theencomputers
    Title: Main.java
*/

package me.theencomputers.modmode;

import me.theencomputers.modmode.Commands.ModMode;
import me.theencomputers.modmode.Commands.Spec;
import me.theencomputers.modmode.Commands.Tppos;
import me.theencomputers.modmode.Commands.Vanish;
import me.theencomputers.modmode.Events.ModModeItems;
import me.theencomputers.modmode.Events.VanishEvents;
import me.theencomputers.modmode.Events.ModInfo.OreListener;
import me.theencomputers.modmode.Events.ModInfo.PvPListener;
import me.theencomputers.modmode.Events.ModInfo.UnexposedListener;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
    static Main plugin;

    //Register all events
    private void registerAllEvents(){
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new VanishEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new ModModeItems(plugin), this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new UnexposedListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new OreListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener) new PvPListener(), this);
    } 
    //Register all commands
    private void registerAllCommands(){
        getCommand("modmode").setExecutor(new ModMode());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("tppos").setExecutor(new Tppos());
        getCommand("spec").setExecutor(new Spec());
    } 
    //Main Instance
    public static Main getMainInstance(){
        return plugin;
    }

    //Executed when plugin enables
    public void onEnable(){
        plugin = this;
        Logger.getLogger("Minecraft").info("Mod Mode has been Enabled");
        registerAllEvents();
        registerAllCommands();
    }


}
