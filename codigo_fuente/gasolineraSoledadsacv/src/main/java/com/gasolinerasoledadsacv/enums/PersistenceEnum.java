package com.gasolinerasoledadsacv.enums;

public enum PersistenceEnum {

    PERSISTENCE_UNIT_NAME("persistense_unit_gasolinera");

    private final String value;

    private PersistenceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
