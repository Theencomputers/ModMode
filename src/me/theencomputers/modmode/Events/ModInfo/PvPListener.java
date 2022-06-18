/*
	Author: Theencomputers
	Title: PvPListener.java
*/
package me.theencomputers.modmode.Events.ModInfo;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.theencomputers.modmode.ModModeManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PvPListener implements Listener{
    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPvPDamage(EntityDamageByEntityEvent e) {          //PvP Alerts
        if(e.getDamager() instanceof Player){
            if(e.getEntity() instanceof Player){        //if both entities are players
                Player Attacker = (Player) e.getDamager();
                Player Victim = (Player) e.getEntity();
                if(Attacker.getGameMode().equals(GameMode.SURVIVAL) && Victim.getGameMode().equals(GameMode.SURVIVAL)){     //if both are in survial log
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){    //send json message to all mods
                    
                        if(ModModeManager.inModMode.contains(iPlayer)){     
                            TextComponent msg = new TextComponent("§cPvP §4" + Attacker.getName() + "§7 [" + e.getDamage() + "] §8=> §e" + Victim.getName());
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + Attacker.getLocation().getWorld().getName() + " " + Attacker.getLocation().getBlockX() + " " + Attacker.getLocation().getBlockY() + " "+ Attacker.getLocation().getBlockZ()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                            iPlayer.spigot().sendMessage(msg);
                        }
                    }
                }
            }
        }
    }
    
}
