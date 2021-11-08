package gg.jpn.caffein.caffeinskills.sql;

import dev.m1n1don.simplesql.db.Database;
import dev.m1n1don.simplesql.sqlite.SQLite;
import gg.jpn.caffein.caffeinskills.Status.STATUS_TYPES;
import gg.jpn.caffein.caffeinskills.Status.Status;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StatusAPI implements API {

    private Database getDatabase() {
        Database database;
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CaffeinSkills");
        assert plugin != null;
        database = new Database(plugin, new SQLite("status.db", plugin.getDataFolder().toString() + "/database/"));
        database.setup();
        database.executeStatement(SQLQuery.CREATE_TABLE_STATUS);
        return database;
    }

    @Override
    public Status getStatus(Player player) {
        Database database = getDatabase();
        ResultSet result = database.executeResultStatement(SQLQuery.SELECT_STATUS, player.getUniqueId().toString());
        try {
            if(!existsStatus(player)) {
                setStatus(player, new Status());
                return new Status();
            } else {
                result.first();
                Status status = new Status();
                for (STATUS_TYPES type : STATUS_TYPES.values()) {
                    status.setStatus(type, result.getInt(type.toString()));
                }
                return status;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private boolean existsStatus(Player player) throws SQLException {
        Database database = getDatabase();
        ResultSet result = database.executeResultStatement(SQLQuery.SELECT_STATUS, player.getUniqueId().toString());
        result.last();
        return result.getRow() != 0;
    }

    @Override
    public void setStatus(Player player, Status status) {
        Database database = getDatabase();
        try {
            if(existsStatus(player)) {
                database.executeStatement(SQLQuery.UPDATE_STATUS, status.getStatus(STATUS_TYPES.HIT_POINT), status.getStatus(STATUS_TYPES.MAGIC_POINT), status.getStatus(STATUS_TYPES.ATTACK), status.getStatus(STATUS_TYPES.DEFENSE), status.getStatus(STATUS_TYPES.AGILITY), player.getUniqueId().toString());
            } else {
                database.executeStatement(SQLQuery.INSERT_STATUS, player.getUniqueId().toString(), status.getStatus(STATUS_TYPES.HIT_POINT), status.getStatus(STATUS_TYPES.MAGIC_POINT), status.getStatus(STATUS_TYPES.ATTACK), status.getStatus(STATUS_TYPES.DEFENSE), status.getStatus(STATUS_TYPES.AGILITY));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
