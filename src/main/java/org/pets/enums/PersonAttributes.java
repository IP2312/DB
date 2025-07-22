package org.pets.enums;

public enum PersonAttributes {
    FIRST_NAME("1"),
    LAST_NAME("2"),
    HOUSEHOLD("3");

    private final String code;

    PersonAttributes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PersonAttributes fromCode(String code) {
        for (PersonAttributes attribute : PersonAttributes.values()) {
            if (attribute.getCode().equals(code)) {
                return attribute;
            }
        }
        throw new IllegalArgumentException("Invalid attribute chosen: " + code);
    }
}
