package com.rustambek.clinic.visit.enums;

public enum VisitStatus {
    OPEN,            // Reception tashrif ochgan, jarayon boshlandi
    IN_PROGRESS,     // Ko‘rik yoki xizmatlar davom etmoqda
    WAITING_PAYMENT, // Xizmatlar bajarilgan, to‘lov kutilmoqda
    CLOSED           // To‘lov qilingan va tashrif yopilgan
}
