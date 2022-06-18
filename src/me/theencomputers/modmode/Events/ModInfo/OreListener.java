/*
	Author: Theencomputers
	Title: OreListener.java
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

public class OreListener implements Listener{
    @EventHandler
    public void onMineBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
            Block b = e.getBlock();
            int numVein = 0;

            if(b.getType().equals(Material.DIAMOND_ORE)){           //mined diamonds
                if(ModModeManager.playerDiamondCount.containsKey(e.getPlayer())){       //increment diamond count
                    ModModeManager.playerDiamondCount.put(e.getPlayer(), ModModeManager.playerDiamondCount.get(e.getPlayer()) +1); 
                }
                else{
                    ModModeManager.playerDiamondCount.put(e.getPlayer(), 1);
                }
                for(int x = -3; x < 4; x++){        //iterate through vein and count number of ores then log it
                    for(int z = -3; z < 4; z++){
                        for(int y = 3; y > -4; y--){
                            Location veiLocation = new Location(b.getLocation().getWorld(), b.getLocation().getBlockX() + x, b.getLocation().getBlockY() + y, b.getLocation().getBlockZ() + z);
                            if(ModModeManager.diamondVeiLocations.contains(veiLocation)){
                                return;
                            }
                            else if(b.getLocation().getBlockY() + y < 1){
                                break;
                            }
                            if(veiLocation.getBlock().getType().equals(Material.DIAMOND_ORE)){
                                ModModeManager.diamondVeiLocations.add(veiLocation);        //add locations so they are not logged again
                                numVein++;
                            }
                        }
                    }
                }
                for(Player iPlayer : Bukkit.getOnlinePlayers()){
                    if(ModModeManager.inModMode.contains(iPlayer)){         //send json message
                        TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §aMined §9Diamond Ore §7[V:" + numVein + "] [T:" + ModModeManager.playerDiamondCount.get(p) + "]");
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getWorld().getName() + " " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                        iPlayer.spigot().sendMessage(msg);
                    }
                }
            }
            else if(b.getType().equals(Material.GOLD_ORE)){     //do the same for gold
                if(ModModeManager.playerGoldCount.containsKey(e.getPlayer())){
                    ModModeManager.playerGoldCount.put(e.getPlayer(), ModModeManager.playerGoldCount.get(e.getPlayer()) +1);
                }
                else{
                    ModModeManager.playerGoldCount.put(e.getPlayer(), 1);
                }
                for(int x = -3; x < 4; x++){
                    for(int z = -3; z < 4; z++){
                        for(int y = 3; y > -4; y--){
                            Location veiLocation = new Location(b.getLocation().getWorld(), b.getLocation().getBlockX() + x, b.getLocation().getBlockY() + y, b.getLocation().getBlockZ() + z);
                            if(ModModeManager.goldVeinLocations.contains(veiLocation)){
                                return;
                            }
                            else if(b.getLocation().getBlockY() + y < 1){
                                break;
                            }
                            if(veiLocation.getBlock().getType().equals(Material.GOLD_ORE)){
                                ModModeManager.goldVeinLocations.add(veiLocation);
                                numVein++;
                            }
                        }
                    }
                }
                for(Player iPlayer : Bukkit.getOnlinePlayers()){
                    if(ModModeManager.inModMode.contains(iPlayer)){
                        TextComponent msg = new TextComponent("§a" + p.getName() + "§8 => §aMined §eGold Ore §7[V:" + numVein + "] [T:" + ModModeManager.playerGoldCount.get(p) + "]");
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + e.getBlock().getWorld().getName() + " " + e.getBlock().getLocation().getBlockX() + " " + e.getBlock().getLocation().getBlockY() + " "+ e.getBlock().getLocation().getBlockZ()));
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleport to event").create()));
                        iPlayer.spigot().sendMessage(msg);
                    }
                }
            }

    
    }
}
