package gg.jpn.caffein.caffeinskills.sql;

import dev.m1n1don.simplesql.db.IQuery;

public enum SQLQuery implements IQuery
{
    CREATE_TABLE_STATUS(
            "CREATE TABLE IF NOT EXISTS Status (" +
                    "uuid STRING PRIMARY KEY," +
                    "hitPoint INTEGER," +
                    "magicPoint INTEGER," +
                    "attack INTEGER," +
                    "defense INTEGER," +
                    "agility INTEGER)"
    ),
    INSERT_STATUS(
            "INSERT INTO Status" +
                    "(uuid, hitPoint, magicPoint, attack, defense, agility) " +
                    "VALUES (?, ?, ?, ?, ?, ?)"
    ),
    UPDATE_STATUS(
            "UPDATE Status SET " +
                    "hitPoint = ?, magicPoint = ?, attack = ?, defense = ?, agility = ? WHERE uuid = ?"
    ),
    SELECT_STATUS(
            "SELECT * FROM Status WHERE uuid = ?"
    );

    private final String sql;

    SQLQuery(String sql)
    {
        this.sql = sql;
    }

    @Override
    public String toString()
    {
        return sql;
    }
}
