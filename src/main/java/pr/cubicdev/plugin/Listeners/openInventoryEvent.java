package pr.cubicdev.plugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import pr.cubicdev.plugin.Utils.lockUtils;
import pr.cubicdev.plugin.Utils.generalUtils;
import pr.cubicdev.plugin.Utils.messageUtils;
import pr.cubicdev.plugin.Plugin;

public class openInventoryEvent implements Listener {
    private lockUtils lockUtils;
    private generalUtils generalUtils;

    public openInventoryEvent(generalUtils generalUtils, lockUtils lockUtils) {
        this.generalUtils = generalUtils;
        this.lockUtils = lockUtils;
    }

    //Detectar cuando un jugador abre un cofre
    @EventHandler
    public void onChestOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Block block = pr.cubicdev.plugin.Utils.generalUtils.getBlockLooking(player);
        //Es un cofre?
        if (block.getType() == Material.CHEST) {
            Bukkit.getConsoleSender().sendMessage("Un usuario a abierto un cofre.");
            //El cofre ya esta reclamado?
            //No
            if (!(pr.cubicdev.plugin.Utils.lockUtils.isLocked(block.getLocation())) && lockUtils.getWhoLocked(block.getLocation()) == null) {
                player.sendMessage(messageUtils.getColoredMSG(Plugin.getPrefix() + " &aHas abierto un cofre."));
            //Si
            } else {
                //Es del jugador?
                if(lockUtils.getWhoLocked(block.getLocation()) == player){
                    player.sendMessage(messageUtils.getColoredMSG(Plugin.getPrefix() + " &aHas abierto tu cofre."));
                    e.setCancelled(false);
                }else{
                    player.sendMessage(messageUtils.getColoredMSG(Plugin.getPrefix() + " &cEste cofre esta bloqueado por: &6" + lockUtils.getWhoLocked(block.getLocation())));
                    e.setCancelled(true);
                }
            }
        }
    }
}