package pr.cubicdev.plugin.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class lockUtils {
    //HashMaps para guardar informacion de los cofres bloqueados y nombres de usuarios
    public static HashMap<Location, Boolean> blockMap = new HashMap<>();
    public static HashMap<Player, Location> playerMap = new HashMap<>();

    public static boolean isLocked(Location loc){
        return blockMap.getOrDefault(loc, false);
    }

    //¿Quien a bloqueado este cofre?
    public Player getWhoLocked(Location loc){
        //Recorrer hashmap de jugadores
        for(Map.Entry<Player, Location> entry : playerMap.entrySet()){
            if(entry.getValue().equals(loc)){
                return entry.getKey();
            }
        }
        //Retornar nulo si nadie lo ha reclamado
        return null;
    }
}
