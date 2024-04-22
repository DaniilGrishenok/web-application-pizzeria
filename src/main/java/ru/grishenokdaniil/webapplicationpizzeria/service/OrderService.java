package ru.grishenokdaniil.webapplicationpizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }

    public void editOrder(Long id, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(order -> {
            order.setStatus(orderStatus);
            orderRepository.save(order);
        });
    }
}
