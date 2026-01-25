package com.rustambek.clinic.pharmacy_products.model;

public enum UnitType {

    // Songa bogliq
    DONA("Soni bilan (pcs)"),
    AMPULA("Ampula soni"),
    FLAKON("Flakon soni"),
    VIAL("Vial (kichik flakon) soni"),
    SACHET("Paketcha (sachet) soni"),
    TUBE("Tuba (maz/gel) soni"),
    QUTI("Quti (box) soni"),
    BLISTER("Blister soni"),
    PACK("O‘ram/pachka (pack)"),
    BOTTLE("Butilka (bottle) soni"),

    // Hajmga bogliq
    ML("Millilitr (sirop, eritma, suyuqlik)"),
    L("Litr (katta hajmli suyuqlik)"),

    // Og‘irlikga bogliq
    MG("Milligram"),
    G("Gram"),

    // Boshqa
    DROP("Tomchi (drop)");

    private final String description;

    UnitType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

