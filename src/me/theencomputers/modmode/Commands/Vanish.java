/*
	Author: Theencomputers
	Title: Vanish.java
*/
package me.theencomputers.modmode.Commands;

import me.theencomputers.modmode.ModModeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    //put in vanish method
    public void putInVanish(Player modPlayer){
        if(!ModModeManager.inVanishList.contains(modPlayer)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(ModModeManager.inVanishList.contains(p)){
                    p.showPlayer(modPlayer);        //show player p if they are a mod
                    modPlayer.showPlayer(p);      
                }
                else p.hidePlayer(modPlayer);            //else hide player
            }
            modPlayer.spigot().setCollidesWithEntities(false);      //keeps mod from interferring with entities

            modPlayer.sendMessage("§eYou are now in vanish!");
            ModModeManager.inVanishList.add(modPlayer);
        }
    }
    //remove from vanish method
    public void removeFromVanish(Player modPlayer){
        if(ModModeManager.inVanishList.contains(modPlayer)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(ModModeManager.inVanishList.contains(p)){
                    p.showPlayer(modPlayer);        //hide player from mods
                    modPlayer.hidePlayer(p);
                }
                p.showPlayer(modPlayer);        //show player to other players
                p.spigot().setCollidesWithEntities(true);
            }
            modPlayer.sendMessage("§eYou are no longer in vanish!");
            ModModeManager.inVanishList.remove(modPlayer);

        }
        else{
            modPlayer.sendMessage("§cYou are not currently in vanish mode...");

        }
    }
	@Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                if(sender.hasPermission("modmode.staff")){
                    if(sender instanceof Player){
                        if(!ModModeManager.inVanishList.contains(sender)){
                            putInVanish((Player) sender);       //use our handy methods!
                        }
                        else{
                            removeFromVanish((Player) sender);
                        }
                    }
                }
            return true;
        }
}
