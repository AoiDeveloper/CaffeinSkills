package gg.jpn.caffein.caffeinskills.Status;

public enum STATUS_TYPES {
    HIT_POINT("hitPoint"),
    MAGIC_POINT("magicPoint"),
    ATTACK("attack"),
    DEFENSE("defense"),
    AGILITY("agility");


    private final String type;

    STATUS_TYPES(String type) {
        this.type = type;
    }

    public static STATUS_TYPES getStatusByName(String name) {
        STATUS_TYPES[] types = STATUS_TYPES.values();
        for (STATUS_TYPES type : types) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return type;
    }
}
