/*
	Author: Theencomputers
	Title: ModMode.java
*/
package me.theencomputers.modmode.Commands;

import me.theencomputers.modmode.ModModeManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class ModMode implements CommandExecutor {

    //put in modmode method takes argument of player
    public void putInModMode(Player modPlayer){
        Vanish v = new Vanish();
        if(!ModModeManager.inModMode.contains(modPlayer)){
            ModModeManager.inModMode.add(modPlayer);        //add to list
        }

        v.putInVanish(modPlayer);  //use vanish method
        //set spectator settings 
        modPlayer.setGameMode(GameMode.ADVENTURE);
        modPlayer.setAllowFlight(true);
        modPlayer.setFlying(true);
        modPlayer.setFlySpeed(0.2f);
        modPlayer.setWalkSpeed(0.2f);

        //set spectator inventory
        modPlayer.getInventory().clear();
        modPlayer.getInventory().setBoots(null);
        modPlayer.getInventory().setChestplate(null);
        modPlayer.getInventory().setLeggings(null);
        modPlayer.getInventory().setHelmet(null);

        ItemStack flySpeed = new ItemStack(Material.FEATHER);
        ItemStack invSee = new ItemStack(Material.BOOK_AND_QUILL);
        ItemStack randomTP = new ItemStack(Material.BLAZE_ROD);
        
        ItemMeta flySpeedMeta = flySpeed.getItemMeta();
        ItemMeta invSeeMeta = invSee.getItemMeta();
        ItemMeta randomTPMeta = randomTP.getItemMeta();

        flySpeedMeta.setDisplayName("§eFly Speed");
        invSeeMeta.setDisplayName("§aView Inventory");
        randomTPMeta.setDisplayName("§cRandom Teleport");


        flySpeed.setItemMeta(flySpeedMeta);
        invSee.setItemMeta(invSeeMeta);
        randomTP.setItemMeta(randomTPMeta);


        modPlayer.getInventory().setItem(2, flySpeed);
        modPlayer.getInventory().setItem(4, invSee);
        modPlayer.getInventory().setItem(6, randomTP);

        //set other settings
        modPlayer.setHealth(20f);
        modPlayer.setExp(0f);
        modPlayer.setFoodLevel(20);

        modPlayer.sendMessage("§aModMode Features have been enabled");
    
    }

    //remove from mod mode method takes player as argument
    public void removeFromModMode(Player modPlayer){
        Vanish v = new Vanish();
        if(ModModeManager.inModMode.contains(modPlayer)){
            ModModeManager.inModMode.remove(modPlayer);     //remove from list
        }
        //set player settings
        v.removeFromVanish(modPlayer);
        modPlayer.setGameMode(GameMode.SURVIVAL);
        modPlayer.setAllowFlight(false);
        modPlayer.setFlying(false);
        modPlayer.setFlySpeed(0.2f);
        modPlayer.setWalkSpeed(0.2f);
        modPlayer.getInventory().clear();
        modPlayer.sendMessage("§aModMode Features have been disabled\n Executing /spawn...");
        modPlayer.performCommand("spawn");      //goto spawn
    }

	@Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		    if (cmd.getName().equalsIgnoreCase("modmode")) {
                if(sender.hasPermission("modmode.staff")){
                    if(sender instanceof Player){
                        Player modPlayer = (Player) sender;
                        if(args.length == 0){
                            modPlayer.sendMessage("§cError usage: /modmode <on:off:toggle:list> [player]");
                        }
                        else if(args.length == 1){
                            if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")){
                                putInModMode(modPlayer);        //use put in mod mode method
                            }
                            else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")){
                                removeFromModMode(modPlayer);   //use remove from mod mode method
                            }
                            else if(args[0].equalsIgnoreCase("toggle")){
                                if(ModModeManager.inModMode.contains(modPlayer)){
                                    removeFromModMode(modPlayer);
                                }
                                else{
                                    putInModMode(modPlayer);
                                }
                            }
                            else if(args[0].equalsIgnoreCase("list")){
                                String mods = "vanish:§a";
                                for(Player iPlayer : Bukkit.getOnlinePlayers()){        //construct mod list
                                    if(ModModeManager.inVanishList.contains(iPlayer)){
                                        mods = mods + " " + iPlayer.getName(); 
                                    }
                                }
                                modPlayer.sendMessage("§eThe following players are in " + mods);
                            }
                        }
                        else if(args.length == 2){
                            try {
                                Player otherModPlayer = Bukkit.getServer().getPlayer(args[1]);
                                if(otherModPlayer.isOnline()){
                                    if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")){
                                        putInModMode(otherModPlayer);
                                    }
                                    else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")){
                                        removeFromModMode(otherModPlayer);
                                    }
                                    else if(args[0].equalsIgnoreCase("toggle")){
                                        if(ModModeManager.inModMode.contains(modPlayer)){
                                            removeFromModMode(otherModPlayer);
                                        }
                                        else{
                                            putInModMode(otherModPlayer);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") ||
                                args[0].equalsIgnoreCase("toggle") || args[0].equalsIgnoreCase("enable") || 
                                args[0].equalsIgnoreCase("disable")){
                                modPlayer.sendMessage("§cError cannot find player named \"" + args[1] + "\"");      //no player
                                }
                                else{
                                    modPlayer.sendMessage("§cError usage: /modmode <on:off:toggle:list> [player]");
                                }
                            }
                        }
                        else{
                            modPlayer.sendMessage("§cError usage: /modmode <on:off:toggle:list> [player]");
                        }
                    }
                }
             }
            
            return true;
        }
}
