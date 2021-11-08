package gg.jpn.caffein.caffeinskills.Commands;

import gg.jpn.caffein.caffeinskills.Status.STATUS_TYPES;
import gg.jpn.caffein.caffeinskills.Status.Status;
import gg.jpn.caffein.caffeinskills.sql.StatusAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class StatusCommand implements CommandExecutor {
    private String announceStatus(Status status) {
        StringBuilder builder = new StringBuilder();
        return ChatColor.GOLD + status.toString();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // usage: /status set <PlayerName> <TypeName> <NewStatus>
        // usage: /status get <PlayerName>
        // usage: /status get
        if (args.length == 0) return false;
        StatusAPI statusAPI = new StatusAPI();
        Status status;
        Player player;
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "set":
                if (args.length != 4) return false;
                player = Bukkit.getPlayer(args[1]);
                STATUS_TYPES type = STATUS_TYPES.getStatusByName(args[2]);
                int value;
                try {
                    value = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (player == null || type == null) return false;

                status = statusAPI.getStatus(player);
                status.setStatus(type, value);
                statusAPI.setStatus(player, status);

                player.sendMessage(ChatColor.AQUA + "SERVER: SET YOUR STATUS.");
                player.sendMessage(announceStatus(status));

                return true;

            case "get":
                if (args.length != 1 && args.length != 2) return false;
                if (args.length == 1) {
                    if (sender instanceof Player) {
                        player = (Player) sender;
                        status = statusAPI.getStatus(player);
                        sender.sendMessage(announceStatus(statusAPI.getStatus(player)));
                    } else {
                        return false;
                    }
                } else {
                    player = Bukkit.getPlayer(args[0]);
                    if (player == null) return false;
                    status = statusAPI.getStatus(player);
                    sender.sendMessage(announceStatus(status));
                    return true;
                }
                break;
        }
        return false;
    }
}
