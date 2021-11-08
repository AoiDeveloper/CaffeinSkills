package gg.jpn.caffein.caffeinskills.sql;

import gg.jpn.caffein.caffeinskills.Status.Status;
import org.bukkit.entity.Player;

public interface API {
    Status getStatus(Player player);
    void setStatus(Player player, Status status);
}
