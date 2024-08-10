package pr.cubicdev.plugin.Utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class generalUtils {
    //A que bloque esta mirando el jugador?
    public static Block getBlockLooking(Player player){
        Block block = player.getTargetBlockExact(20);
        return block;
    }
}
