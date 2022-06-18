/*
	Author: Theencomputers
	Title: Tppos.java
*/
package me.theencomputers.modmode.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.theencomputers.modmode.ModModeManager;


public class Tppos implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(ModModeManager.inModMode.contains(p)){       //use by moderators only
                if(args.length == 4){
                    try {       //try to parse location
                        Location tpLoc = new Location(Bukkit.getWorld(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        p.teleport(tpLoc);      //try to teleport them
                        p.sendMessage("§aTeleporting...");
                    } catch (Exception e) {     //no location
                        p.sendMessage("§cError: Location does not exist");
                    }

                }
                else{
                    p.sendMessage("§cError Usage: /tppos <world> <x> <y> <z>");

                }
            }
            else{
                p.sendMessage("§cSorry! You must be in Mod Mode to use this command!");

            }
        }
        return true;
    }
}
