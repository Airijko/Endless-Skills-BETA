package me.airijko.endlessskills.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.airijko.endlessskills.leveling.XPConfiguration;
import me.airijko.endlessskills.leveling.LevelThresholdCalculator;

public class ReloadCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final XPConfiguration xpConfiguration;
    private final LevelThresholdCalculator levelThresholdCalculator;

    public ReloadCommand(JavaPlugin plugin, XPConfiguration xpConfiguration, LevelThresholdCalculator levelThresholdCalculator) {
        this.plugin = plugin;
        this.xpConfiguration = xpConfiguration;
        this.levelThresholdCalculator = levelThresholdCalculator;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("endless") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("endless.reload")) {
                // Reload the XP configuration
                xpConfiguration.loadXPConfiguration();
                // Reload the leveling formula configuration
                levelThresholdCalculator.loadLevelingFormula();

                sender.sendMessage("EndlessSkills configuration has been reloaded!");
                return true;
            } else {
                sender.sendMessage("You do not have permission to use this command.");
                return false;
            }
        }
        return false;
    }
}

