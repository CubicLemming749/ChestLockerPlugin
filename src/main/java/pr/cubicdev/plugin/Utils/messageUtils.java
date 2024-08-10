package pr.cubicdev.plugin.Utils;

import org.bukkit.ChatColor;

public class messageUtils {
    public static String getColoredMSG(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
