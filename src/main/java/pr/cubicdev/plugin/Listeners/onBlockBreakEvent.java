package pr.cubicdev.plugin.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pr.cubicdev.plugin.Utils.lockUtils;
import pr.cubicdev.plugin.Utils.messageUtils;
import pr.cubicdev.plugin.Plugin;

public class onBlockBreakEvent implements Listener {
    private lockUtils lockUtils;
    public onBlockBreakEvent(lockUtils lockUtils){
        this.lockUtils = lockUtils;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = (Player) e.getPlayer();
        Block block = e.getBlock();
        Location loc = block.getLocation();
        if(block.getType() == Material.CHEST){
            if(lockUtils.isLocked(loc)){
                player.sendMessage(messageUtils.getColoredMSG(Plugin.getPrefix()+" &cEste cofre esta bloqueado por: &6"+lockUtils.getWhoLocked(loc)));
                e.setCancelled(true);
            }else if(lockUtils.getWhoLocked(loc) == player){
                player.sendMessage(messageUtils.getColoredMSG(Plugin.getPrefix()+ " &aHas roto tu cofre con exito."));
                lockUtils.blockMap.remove(loc, true);
                lockUtils.playerMap.remove(player, loc);
            }
        }
    }
}
