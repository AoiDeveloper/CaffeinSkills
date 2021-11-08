package gg.jpn.caffein.caffeinskills.Status;

import java.util.HashMap;

public class Status {
    HashMap<STATUS_TYPES, Integer> status = new HashMap<>();
    public Status() {
        for (STATUS_TYPES type : STATUS_TYPES.values()) {
            status.put(type, 0);
        }
    }

    public int getStatus(STATUS_TYPES type) {
        return status.get(type);
    }

    public void setStatus(STATUS_TYPES type, Integer value) {
        status.put(type, value);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(Integer value : status.values()) {
            ret.append(value.toString()).append(' ');
        }
        return ret.toString();
    }
}
