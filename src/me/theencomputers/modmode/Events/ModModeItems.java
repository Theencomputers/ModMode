package me.theencomputers.modmode.Events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import me.theencomputers.modmode.Main;
import me.theencomputers.modmode.ModModeManager;
import me.theencomputers.modmode.Commands.ModMode;
import net.md_5.bungee.api.ChatColor;

public class ModModeItems implements Listener{
    private Inventory invSee;
    private final Main plugin;
        
    public ModModeItems(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onItemRightClick(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            if(ModModeManager.inModMode.contains(p)){
                if(p.getItemInHand().getItemMeta().getDisplayName().equals("§eFly Speed")){
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
                }
                else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§aView Inventory")){
                    String invPlayerName = null;
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){
                        if(iPlayer.equals(p)) continue;
                        if(p.getLocation().getWorld().getName().equals(iPlayer.getLocation().getWorld().getName())){
                            if(invPlayerName == null){
                                invPlayerName = iPlayer.getName();
                            }
                            else if( iPlayer.getLocation().distance(p.getLocation()) < (Bukkit.getPlayer(invPlayerName)).getLocation().distance(p.getLocation())){
                                invPlayerName = iPlayer.getUniqueId().toString();
                            }
                        }
                    }
                    if(invPlayerName == null){
                        p.sendMessage("§cNo Players nearby");
                    }
                    else{
                        try {
                            Player invPlayer = (Player) Bukkit.getPlayer(invPlayerName);
                            Inventory inv = plugin.getServer().createInventory(null, 45, ChatColor.GREEN + invPlayer.getName() + "'s Inventory");                            //Inventory inv = plugin.getServer().createInventory(p, 45);
                            for(int i = 0; i < 40; i++){
                                if(invPlayer.getItemInHand() != null){
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
                else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§cRandom Teleport")){
                    if(Bukkit.getServer().getOnlinePlayers().length > ModModeManager.inModMode.size()){
                        Random rand = new Random();
                        int randomPlayerIndex = rand.nextInt(Bukkit.getServer().getOnlinePlayers().length -ModModeManager.inModMode.size());
                        int currentIndex = 1;
                        for(Player iPlayer : Bukkit.getOnlinePlayers()){
                            if(!ModModeManager.inModMode.contains(iPlayer)){
                                if(currentIndex == randomPlayerIndex){
                                    p.sendMessage("§aTeleporting you to " + iPlayer.getName());
                                    p.teleport(iPlayer);
                                    break;
                                }
                                else{
                                    currentIndex++;
                                }
                            }
                        }
                    }
                    else{
                        p.sendMessage("§cNo one to teleport to");
                    }
                }
            }
        }

    }
    
}
