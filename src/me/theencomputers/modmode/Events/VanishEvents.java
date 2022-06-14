package me.theencomputers.modmode.Events;

import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.theencomputers.modmode.ModModeManager;

public class VanishEvents implements Listener{
    @EventHandler
    public void onModHunger(FoodLevelChangeEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
        }
    }
    @EventHandler
    public void onVanishInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.CHEST)){
            e.setCancelled(true);
            Chest c = (Chest) e.getClickedBlock();
            p.openInventory(c.getInventory());
        }
        else if(ModModeManager.inVanishList.contains(p)){
            e.setCancelled(true); 
        }
    }
    @EventHandler
    public void onVanishInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if(ModModeManager.inVanishList.contains(p)){
            e.setCancelled(true);
        } 
    }

    @EventHandler
    public void onVanishHurtEnityt(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && ModModeManager.inVanishList.contains((Player) e.getDamager())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onVanishPickup(PlayerPickupItemEvent e) {
        if(ModModeManager.inVanishList.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
            Player p = (Player) e.getWhoClicked();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
            Player p = e.getPlayer();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
    }
    
    @EventHandler
    public void onModDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(ModModeManager.inModMode.contains(p)){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onEnitityTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();
            if (ModModeManager.inVanishList.contains(p)){
                e.setCancelled(true); 
            }
        }
    }
}
