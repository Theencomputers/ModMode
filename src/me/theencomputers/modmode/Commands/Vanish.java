package me.theencomputers.modmode.Commands;

import me.theencomputers.modmode.ModModeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;




public class Vanish implements CommandExecutor {
    public void putInVanish(Player modPlayer){
        if(!ModModeManager.inVanishList.contains(modPlayer)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(ModModeManager.inVanishList.contains(p)){
                    p.showPlayer(modPlayer);
                    modPlayer.showPlayer(p);
                }
                p.hidePlayer(modPlayer);
                p.spigot().setCollidesWithEntities(false);
                //p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
            }
            modPlayer.sendMessage("§eYou are now in vanish!");
            ModModeManager.inVanishList.add(modPlayer);
        }
    }
    public void removeFromVanish(Player modPlayer){
        if(ModModeManager.inVanishList.contains(modPlayer)){
            for(Player p : Bukkit.getOnlinePlayers()){
                if(ModModeManager.inVanishList.contains(p)){
                    p.showPlayer(modPlayer);
                    modPlayer.hidePlayer(p);
                }
                p.showPlayer(modPlayer);
                p.spigot().setCollidesWithEntities(true);
            }
            modPlayer.sendMessage("§eYou are no longer in vanish!");
            ModModeManager.inVanishList.remove(modPlayer);

        }
        else{
            modPlayer.sendMessage("§cYou are not currently in vanish mode...");

        }
    }
	@Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                if(sender.hasPermission("modmode.staff")){
                    if(sender instanceof Player){
                        if(!ModModeManager.inVanishList.contains(sender)){
                            putInVanish((Player) sender);
                        }
                        else{
                            removeFromVanish((Player) sender);
                        }
                    }
                }
            return true;
        }
}
