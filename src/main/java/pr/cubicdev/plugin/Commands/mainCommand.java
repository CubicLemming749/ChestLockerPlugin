package pr.cubicdev.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pr.cubicdev.plugin.Plugin;
import pr.cubicdev.plugin.Utils.lockUtils;
import pr.cubicdev.plugin.Utils.messageUtils;

public class mainCommand implements CommandExecutor {
    private Plugin Main;
    private lockUtils lockUtils;

    public mainCommand(Plugin main, lockUtils lockUtils){
        this.Main = main;
        this.lockUtils = lockUtils;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //¿Fue ejecutado por consola?
        if(!(commandSender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(Main.getPrefix()+" &cNo puedes ejecutar este comando desde la consola.");
        //Si fue ejecutado por un jugador, ejecutar:
        }else{
            Player player = (Player) commandSender;
            Block block = player.getTargetBlockExact(20);
            Location loc = block.getLocation();
            //Verificar que sea un cofre
            if(block.getType() == Material.CHEST) {
                //Verificar que no este bloqueado
                if (!(pr.cubicdev.plugin.Utils.lockUtils.isLocked(loc))) {
                    lockUtils.playerMap.put(player, loc);
                    player.sendMessage(messageUtils.getColoredMSG(Main.getPrefix() + " &aCofre reclamado con exito. Ahora solo tu puedes abrirlo."));
                    //Si esta bloqueado, decirle al jugador:
                } else {
                    player.sendMessage(messageUtils.getColoredMSG(Main.getPrefix() + " &cEste cofre ya fue bloqueado por: &6" + lockUtils.getWhoLocked(loc)));
                }
            //Si no es un cofre:
            }else{
                player.sendMessage(messageUtils.getColoredMSG(Main.getPrefix()+" &cNo estas mirando a un cofre."));
            }
            return true;
        }
        return false;
    }
}
