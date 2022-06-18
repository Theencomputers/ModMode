/*
	Author: Theencomputers
	Title: Spec.java
*/
package me.theencomputers.modmode.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.theencomputers.modmode.ModModeManager;


public class Spec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(ModModeManager.inModMode.contains(p)){       //only used by players in mod mode
                if(args.length == 1){
                    try {
                        p.teleport(Bukkit.getPlayer(args[0]));      //teleport player 
                        p.sendMessage("§aTeleporting...");
                    } catch (Exception e) {
                        p.sendMessage("§cError: Could not find player");        //no player
                    }

                }
                else{
                    p.sendMessage("§cError Usage: /spec <player>");

                }
            }
            else{
                p.sendMessage("§cSorry! You must be in Mod Mode to use this command!");

            }
        }
        return true;
    }
}
