/*
	Author: Theencomputers
	Title: ModMode.java
*/
package me.theencomputers.modmode.Events;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import me.theencomputers.modmode.Main;
import me.theencomputers.modmode.ModModeManager;
import net.md_5.bungee.api.ChatColor;

public class ModModeItems implements Listener{      //get main instance from the method in Main.java
    private final Main plugin;
        
    public ModModeItems(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemRightClick(PlayerInteractEvent e) {       //handle right clicking mod items 
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            if(ModModeManager.inModMode.contains(p)){
                try {
                    if(p.getItemInHand().getItemMeta().getDisplayName().equals("§eFly Speed")){     //change fly speed
                        try {   //fly throughs null pointer if spammed so it is in try_catch
                            if(p.getFlySpeed() == 0.2f){        
                                p.sendMessage("§aFlight speed set to MEDIUM");
                                p.setFlySpeed(0.3f);
                                p.setWalkSpeed(0.3f);
                            }
                            else if(p.getFlySpeed() == 0.3f){
                                p.sendMessage("§aFlight speed set to §cFAST");
                                p.setFlySpeed(0.5f);
                                p.setWalkSpeed(0.5f); 
                            }
                            else if(p.getFlySpeed() == 0.5f){
                                p.sendMessage("§aFlight speed set to §4MACH");
                                p.setFlySpeed(0.7f);
                                p.setWalkSpeed(0.7f);
                            }
                            else if(p.getFlySpeed() == 0.7){
                                p.sendMessage("§aFlight speed set to §9SLOW");
                                p.setFlySpeed(0.2f);
                                p.setWalkSpeed(0.2f);
                            }
                            else{
                                p.sendMessage("§aFlight speed set to §9SLOW");
                                p.setFlySpeed(0.2f);
                                p.setWalkSpeed(0.2f);
                            }
                        } catch (Exception exception) {}
    
                    }
                    else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§aView Inventory")){       //view inventory
                        String invPlayerName = null;
                        for(Player iPlayer : Bukkit.getOnlinePlayers()){        //iterate through all players
                            if(iPlayer.equals(p)) continue;                     //if the loop player is the mod continue to next entry
                            if(p.getLocation().getWorld().getName().equals(iPlayer.getLocation().getWorld().getName())){    //sort algorithm to find the nearest player
                                if(invPlayerName == null){      //start at null
                                    invPlayerName = iPlayer.getName();
                                }
                                else if( iPlayer.getLocation().distance(p.getLocation()) < (Bukkit.getPlayer(invPlayerName)).getLocation().distance(p.getLocation())){
                                    invPlayerName = iPlayer.getName();      //found closer player change it
                                }
                            }
                        }
                        if(invPlayerName == null){      //if null then no one online
                            p.sendMessage("§cNo Players nearby");
                        }
                        else{
                            try {      //else open player inventory
                                Player invPlayer = (Player) Bukkit.getPlayer(invPlayerName);
                                Inventory inv = plugin.getServer().createInventory(null, 45, ChatColor.GREEN + invPlayerName + "'s Inventory");                            //Inventory inv = plugin.getServer().createInventory(p, 45);
                                for(int i = 0; i < 40; i++){
                                    if(invPlayer.getInventory().getItem(i) != null && !invPlayer.getInventory().getItem(i).getType().equals(Material.AIR)){
                                        inv.setItem(i, invPlayer.getInventory().getItem(i));
                                    }
                                }
                                p.openInventory(inv);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                                p.sendMessage("§cNo Players near you");
                            }
                        }
    
                    }
                    else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§cRandom Teleport")){      //random teleporter
                        if(Bukkit.getServer().getOnlinePlayers().length >= ModModeManager.inModMode.size()){
                            Random rand = new Random();
                            int randomPlayerIndex = rand.nextInt(Bukkit.getServer().getOnlinePlayers().length - ModModeManager.inModMode.size());
                            int currentIndex = 0;
                            for(Player iPlayer : Bukkit.getOnlinePlayers()){        //iterate through all players skip if a mod and find the random number of players
                                if(!ModModeManager.inModMode.contains(iPlayer)){
                                    if(currentIndex == randomPlayerIndex){      //found!
                                        p.sendMessage("§aTeleporting you to " + iPlayer.getName());
                                        p.teleport(iPlayer);    //teleport
                                        break;
                                    }
                                    else{       //increment index
                                        currentIndex++;
                                    }
                                }
                            }
                        }
                        else{
                            p.sendMessage("§cNo one to teleport to");
                        }
                    } 
                } catch (Exception exception) {}

            }
        }

    }
    
}
