package pr.cubicdev.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import pr.cubicdev.plugin.Commands.mainCommand;
import pr.cubicdev.plugin.Listeners.onBlockBreakEvent;
import pr.cubicdev.plugin.Listeners.openInventoryEvent;
import pr.cubicdev.plugin.Utils.generalUtils;
import pr.cubicdev.plugin.Utils.messageUtils;
import pr.cubicdev.plugin.Utils.lockUtils;
import java.util.List;
import java.util.Objects;

public final class Plugin extends JavaPlugin {

    //Variables
    private static String prefix = "&7[&eChestLock&7]";
    PluginDescriptionFile pluginDesc = getDescription();
    public String version = pluginDesc.getVersion();
    public List<String> author = pluginDesc.getAuthors();

    //Logica de inicializacion
    public void onEnable(){
        lockUtils lockUtils = new lockUtils();
        generalUtils generalUtils = new generalUtils();
        Bukkit.getConsoleSender().sendMessage(messageUtils.getColoredMSG(prefix+ " &a¡Plugin iniciado con exito! Version: "+version));
        registerEvents(generalUtils, lockUtils);
        registerCommands(lockUtils);
        if(this.getCommand("chestlock") == null){
            Bukkit.getConsoleSender().sendMessage("EL COMANDO NO PUDO SER REGISTRADO! REINICIA EL SERVIDOR");
        }
        Bukkit.getConsoleSender().sendMessage("Comandos cargados con exito.");
    }

    //Logica de desactivacion
    public void onDisable(){

    }

    //Registrar comandos
    public void registerCommands(lockUtils lockUtils){
        Objects.requireNonNull(this.getCommand("chestlock")).setExecutor(new mainCommand(this, lockUtils));
    }

    //Registar eventos
    public void registerEvents(generalUtils generalUtils, lockUtils lockUtils){
        getServer().getPluginManager().registerEvents(new openInventoryEvent(generalUtils, lockUtils), this);
        getServer().getPluginManager().registerEvents(new onBlockBreakEvent(lockUtils), this);
    }

    //Getters y setters

    public static String getPrefix(){
        return prefix;
    }
}
