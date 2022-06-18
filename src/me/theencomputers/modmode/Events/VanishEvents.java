/*
	Author: Theencomputers
	Title: VanishEvents.java
*/
package me.theencomputers.modmode.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import me.theencomputers.modmode.ModModeManager;
import me.theencomputers.modmode.Commands.ModMode;

public class VanishEvents implements Listener{
    ModMode mm = new ModMode();
    
    //A bunch of events that makes vanish work properly
    @EventHandler
    public void onModHunger(FoodLevelChangeEvent e) {       //disable hunger
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
        }
    }
    @EventHandler
    public void onVanishInteract(PlayerInteractEvent e) {       //disable interact and make chests open silently
        Player p = e.getPlayer();
        if(ModModeManager.inVanishList.contains(p)){
            e.setCancelled(true);
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.CHEST)){
                Block b = (Block) e.getClickedBlock();
                Chest c = (Chest) b.getState();
                Inventory inv = Bukkit.getServer().createInventory(null, c.getInventory().getSize(), "Chest");
                for(int i = 0; i < c.getInventory().getSize(); i++){
                    if(c.getInventory().getItem(i) != null && !c.getInventory().getItem(i).getType().equals(Material.AIR)){
                        inv.setItem(i, c.getInventory().getItem(i));
                    }

                }
                p.openInventory(inv);
            }
        }

    }
    //disable entity interaction
    @EventHandler
    public void onVanishInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if(ModModeManager.inVanishList.contains(p)){
            e.setCancelled(true);
        } 
    }

    //prevent mod from hurting players
    @EventHandler
    public void onVanishHurtEnityt(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && ModModeManager.inVanishList.contains((Player) e.getDamager())){
            e.setCancelled(true);
        }
    }
    //disable item pick up
    @EventHandler
    public void onVanishPickup(PlayerPickupItemEvent e) {
        if(ModModeManager.inVanishList.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }
    //disable inventory clicking
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
            Player p = (Player) e.getWhoClicked();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
    }
    //disable dropping items
    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
            Player p = e.getPlayer();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
    }
    //dsiable damage to moderator
    @EventHandler
    public void onModDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(ModModeManager.inModMode.contains(p)){
                e.setCancelled(true);
            }
        }
    }
    //make mods ignore vanished players
    @EventHandler
    public void onEnitityTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
        }
    }
    //hide join message and start them in vanish
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("modmode.staff")){
            e.setJoinMessage(null);
            mm.putInModMode(p);
            for(Player iPlayer : Bukkit.getOnlinePlayers()){
                if(iPlayer.hasPermission("modmode.staff")){
                    iPlayer.sendMessage("§7§o" + p.getName() + " has silently joined the server");
                }
            }
        }
        else{
            for(Player iPlayer : Bukkit.getOnlinePlayers()){
                if(ModModeManager.inVanishList.contains(iPlayer)){
                    iPlayer.showPlayer(p);
                    p.hidePlayer(iPlayer);
                }
                else{
                    p.showPlayer(iPlayer); 
                }
                p.spigot().setCollidesWithEntities(true);
            }
        }
    }
    //hide logout messages
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("modmode.staff") && ModModeManager.inModMode.contains(p)){
            e.setQuitMessage(null);
            p.getInventory().clear();
            for(Player iPlayer : Bukkit.getOnlinePlayers()){
                if(iPlayer.hasPermission("modmode.staff")){
                    iPlayer.sendMessage("§7§o" + p.getName() + " has silently left the server");
                }
            }
        }
    }
}
