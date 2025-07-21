package org.pets.enums;


public enum Actions {
    NEW_HOUSEHOLD("1"),
    UPDATE_HOUSEHOLD("2"),
    DELETE_HOUSEHOLD("3"),
    NEW_PERSON("4"),
    UPDATE_PERSON("5"),
    DELETE_PERSON("6"),
    EXIT("0");

    private final String code;

    Actions(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static  Actions fromCode(String code) {
        for (Actions action : Actions.values()) {
            if (action.getCode().equals(code)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid action chosen: " + code);
    }
}
