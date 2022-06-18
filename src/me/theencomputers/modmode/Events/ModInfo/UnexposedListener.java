/*
	Author: Theencomputers
	Title: UnexposedListener.java
*/
package me.theencomputers.modmode.Events.ModInfo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

            if(e.getBlock().getType().equals(Material.STONE) && e.getBlock().getLocation().getBlockY() < 33){   //on mine of stone
                Boolean isDiamond = false;
                Boolean foundOre = false;

                for(int x = -1; x < 2; x++){               //loop all blocks around mined block
                    for(int y = -1; y < 2; y++){
                        for(int z = -1; z < 2; z++){
                            Location l = new Location(e.getBlock().getWorld(), e.getBlock().getLocation().getBlockX() + x, e.getBlock().getLocation().getBlockY() + y, e.getBlock().getLocation().getBlockZ() + z);
                            Block b = l.getBlock();
                            if(b.getType().equals(Material.DIAMOND_ORE)){   //for each diamond
                                foundOre = true;
                                isDiamond = true;
                                if(ModModeManager.diamondVeiLocations.contains(l) || ModModeManager.unexposedLocations.contains(l)){        //check if ore has been logged before
                                    return;
                                }       //see if it touches air
                                if(l.add(0,1,0).getBlock().getType().equals(Material.AIR) || l.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                                || l.add(1,0,0).getBlock().getType().equals(Material.AIR) || l.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                                || l.add(0,0,1).getBlock().getType().equals(Material.AIR) || l.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                                    return;
                                }
                            }
                            else if(b.getType().equals(Material.GOLD_ORE)){     //check for gold ore
                                foundOre = true;
                                if(ModModeManager.goldVeinLocations.contains(l) || ModModeManager.unexposedLocations.contains(l)){      //see if it has been logged before
                                    return;
                                }
                                //see if it touches air
                                if(l.add(0,1,0).getBlock().getType().equals(Material.AIR) || l.add(0,-1,0).getBlock().getType().equals(Material.AIR)
                                || l.add(1,0,0).getBlock().getType().equals(Material.AIR) || l.add(-1,0,0).getBlock().getType().equals(Material.AIR)
                                || l.add(0,0,1).getBlock().getType().equals(Material.AIR) || l.add(0,0,-1).getBlock().getType().equals(Material.AIR)){
                                    return;     
                                }
                            }
                        }
                    }
                }
                if(isDiamond && foundOre){      //if we get this far it is unexposed
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){        //send json message to mods

                        if(ModModeManager.inModMode.contains(iPlayer)){
                            TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §e§lUnexposed §9Diamond Ore");
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getWorld().getName() + " " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                            iPlayer.spigot().sendMessage(msg);
                        }
                    }
                    for(int x = -3; x < 4; x++){
                        for(int z = -3; z < 4; z++){
                            for(int y = 3; y > -4; y--){        //add unexposed locations to prevent spam
                                Location unexposedLoction = new Location(e.getBlock().getLocation().getWorld(), e.getBlock().getLocation().getBlockX() + x, e.getBlock().getLocation().getBlockY() + y, e.getBlock().getLocation().getBlockZ() + z);
                                if(unexposedLoction.getBlock().getType().equals(Material.DIAMOND_ORE) || unexposedLoction.getBlock().getType().equals(Material.GOLD_ORE)){
                                    ModModeManager.unexposedLocations.add(unexposedLoction);
                                }
                            }
                        }
                    }

                }
                else if(foundOre){  //send json alert for unexposed gold to mods
                    for(Player iPlayer : Bukkit.getOnlinePlayers()){
                        if(ModModeManager.inModMode.contains(iPlayer)){
                            TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §e§lUnexposed §eGold Ore");
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getWorld().getName() + " " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                            iPlayer.spigot().sendMessage(msg);
                        }
		            }
                    for(int x = -3; x < 4; x++){        //add gold to unexposed list
                        for(int z = -3; z < 4; z++){
                            for(int y = 3; y > -4; y--){
                                Location unexposedLoction = new Location(e.getBlock().getLocation().getWorld(), e.getBlock().getLocation().getBlockX() + x, e.getBlock().getLocation().getBlockY() + y, e.getBlock().getLocation().getBlockZ() + z);
                                if(unexposedLoction.getBlock().getType().equals(Material.DIAMOND_ORE) || unexposedLoction.getBlock().getType().equals(Material.GOLD_ORE)){
                                    ModModeManager.unexposedLocations.add(unexposedLoction);
                                }
                            }
                        }
                    }
                }
                }
        }
    }
