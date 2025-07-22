package org.pets.enums;

public enum PetAttributes {
    NAME("1"),
    SPECIES("2"),
    OWNER("3");

     private final String code;

     PetAttributes(String code) {
         this.code = code;
     }

     public String getCode() {
         return code;
     }

     public static PetAttributes fromCode(String code) {
         for (PetAttributes attribute : PetAttributes.values()) {
             if (attribute.getCode().equals(code)) {
                 return attribute;
             }
         }
         throw new IllegalArgumentException("Invalid attribute chosen: " + code);
     }
}
