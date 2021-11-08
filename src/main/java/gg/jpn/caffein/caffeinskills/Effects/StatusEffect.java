package gg.jpn.caffein.caffeinskills.Effects;

import gg.jpn.caffein.caffeinskills.Status.STATUS_TYPES;
import gg.jpn.caffein.caffeinskills.Status.Status;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class StatusEffect {
    private final Player player;
    public StatusEffect(Player player) {
        this.player = player;
    }

    public void reloadEffects(Status status) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20d + (double)status.getStatus(STATUS_TYPES.HIT_POINT));
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(5d * (double)status.getStatus(STATUS_TYPES.DEFENSE));
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1d + 0.02d * (double)status.getStatus(STATUS_TYPES.AGILITY));
    }
}
