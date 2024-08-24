package ru.grishenokdaniil.webapplicationpizzeria.model.enams;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AWAITING_PAYMENT("В ожидании оплаты"),
    PAYMENT_RECEIVED("Получен платеж"),
    PAYMENT_FAILED("Ошибка оплаты"),
    IN_PROCESSING("В обработке"),
    READY_FOR_SHIPMENT("Готовится"),
    WAITING_FOR_COURIER("Ожидает курьера"),
    OUT_FOR_DELIVERY("В пути"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён"),
    COMPLETED("Завершён");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
