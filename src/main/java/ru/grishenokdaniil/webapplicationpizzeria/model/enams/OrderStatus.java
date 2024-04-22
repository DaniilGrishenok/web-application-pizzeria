package ru.grishenokdaniil.webapplicationpizzeria.model.enams;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AwaitingPayment( "В ожидании оплаты"),
    PaymentReceived( "Получен платеж"),
    PaymentFailed( "Ошибка оплаты"),
    InProcessing("В обработке"),
    ReadyForShipment("Готов к отгрузке"),
    Shipped("Отправлен"),
    OutForDelivery("В пути"),
    Delivered( "Доставлен"),
    Cancelled( "Отменён"),
    Completed("Завершён");


    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
