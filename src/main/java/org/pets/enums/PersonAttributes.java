package org.pets.enums;

public enum PersonAttributes {
    FIRST_NAME("1"),
    LAST_NAME("2"),
    HOUSEHOLD("3");

    private final String id;

    PersonAttributes(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static PersonAttributes fromId(String code) {
        for (PersonAttributes attribute : PersonAttributes.values()) {
            if (attribute.getId().equals(code)) {
                return attribute;
            }
        }
        throw new IllegalArgumentException("Invalid attribute chosen: " + code);
    }
}
