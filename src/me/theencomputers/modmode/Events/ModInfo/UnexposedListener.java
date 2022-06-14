package me.theencomputers.modmode.Events.ModInfo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;

import me.theencomputers.modmode.ModModeManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UnexposedListener implements Listener{
    @EventHandler
    public void onMineBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (true || ModModeManager.inVanishList.contains(p)){       //TODO make for mod mode only
            if(e.getBlock().getType().equals(Material.STONE) && e.getBlock().getLocation().getBlockY() < 17){

                Location loc = e.getBlock().getLocation();
                Boolean isnexpDia = false;
                Boolean isUnexpGold = false;

                if(loc.add(0,1,0).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, 1, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }

                if(loc.add(0,-2,0).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, -1, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(1,1,0).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(1, 0, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(-2,0,0).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(-1, 0, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(1,0,1).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, 0, 1);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(0,0,-2).getBlock().getType().equals(Material.DIAMOND_ORE)){
                    isnexpDia = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, 0, -1);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(isnexpDia){
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){

                        if(iPlayer.hasPermission("modmode.staff")){
                            TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §e§lUnexposed §9Diamond Ore");
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                            iPlayer.spigot().sendMessage(msg);
                            return;
                        }
                    }
                }
                if(loc.add(0,1,0).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, 1, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(0,-1,0).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, -2, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(1,0,0).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(1, 1, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(-1,0,0).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(-2, 0, 0);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(0,0,1).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(1, 0, 1);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(loc.add(0,0,-1).getBlock().getType().equals(Material.GOLD_ORE)){
                    isUnexpGold = true;
                    Location oreLoc = e.getBlock().getLocation().add(0, 0, -2);
                    if(oreLoc.add(0,1,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(1,0,0).getBlock().getType().equals(Material.AIR) || oreLoc.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                    || oreLoc.add(0,0,1).getBlock().getType().equals(Material.AIR) || oreLoc.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                    return;
                    }
                }
                if(isUnexpGold){
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){
                        if(iPlayer.hasPermission("modmode.staff")){
                            TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §e§lUnexposed §eGold Ore");
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                            iPlayer.spigot().sendMessage(msg);
                        }
		            }
                }
            }
        }
    }
}