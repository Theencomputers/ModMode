package me.theencomputers.modmode.Commands;

import me.theencomputers.modmode.ModModeManager;
import me.theencomputers.modmode.Commands.Vanish;
import net.minecraft.util.com.google.common.collect.FluentIterable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class ModMode implements CommandExecutor {

    public void putInModMode(Player modPlayer){
        Vanish v = new Vanish();
        if(!ModModeManager.inModMode.contains(modPlayer)){
            ModModeManager.inModMode.add(modPlayer);
        }
        v.putInVanish(modPlayer);
        modPlayer.setGameMode(GameMode.ADVENTURE);
        modPlayer.setAllowFlight(true);
        modPlayer.setFlying(true);
        modPlayer.setFlySpeed(0.2f);
        modPlayer.setWalkSpeed(0.2f);

        modPlayer.getInventory().clear();

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

        modPlayer.setHealth(20f);
        modPlayer.setExp(0f);
        modPlayer.setFoodLevel(20);

        modPlayer.sendMessage("§aModMode Features have been enabled");
    
    }

    public void removeFromModMode(Player modPlayer){
        Vanish v = new Vanish();
        if(ModModeManager.inModMode.contains(modPlayer)){
            ModModeManager.inModMode.remove(modPlayer);
        }
        v.removeFromVanish(modPlayer);
        modPlayer.setGameMode(GameMode.SURVIVAL);
        modPlayer.setAllowFlight(false);
        modPlayer.setFlying(false);
        modPlayer.setFlySpeed(0.2f);
        modPlayer.setWalkSpeed(0.2f);
        modPlayer.getInventory().clear();
        modPlayer.sendMessage("§aModMode Features have been disabled");
        modPlayer.performCommand("spawn");
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
                                putInModMode(modPlayer);
                            }
                            else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")){
                                removeFromModMode(modPlayer);
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
                                String mods = "vanish: §a";
                                for(int i = 0; i > ModModeManager.inVanishList.size(); i++){
                                    mods = mods + ModModeManager.inVanishList.get(i);
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
                                    modPlayer.sendMessage("§cError cannot find player named \"" + args[1] + "\"");
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
