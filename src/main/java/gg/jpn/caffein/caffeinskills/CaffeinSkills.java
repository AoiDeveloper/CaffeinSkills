package gg.jpn.caffein.caffeinskills;

import gg.jpn.caffein.caffeinskills.Commands.StatusCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CaffeinSkills extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("status").setExecutor(new StatusCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
