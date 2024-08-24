package ru.grishenokdaniil.webapplicationpizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Transactional
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }
    @Transactional
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    @Transactional
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }
    @Transactional
    public void editOrder(Long id, Order orderUpdated, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        optionalOrder.ifPresent(order -> {
            order.setFlat(orderUpdated.getFlat());
            order.setStreet(orderUpdated.getStreet());
            order.setDescriptionForCourier(orderUpdated.getDescriptionForCourier());
            order.setName(orderUpdated.getName());
            order.setNumberPhone(orderUpdated.getNumberPhone());
            order.setEmail(orderUpdated.getEmail());
            order.setHouse(orderUpdated.getHouse());
            order.setStatus(orderStatus);
            orderRepository.save(order);
        });
    }
    public int quantityActiveOrders() {
        List<OrderStatus> excludedStatuses = Arrays.asList(OrderStatus.COMPLETED, OrderStatus.CANCELLED);
        return orderRepository.countByStatusNotIn(excludedStatuses);
    }
    public int quantityOrdersInThisDay() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return orderRepository.countOrdersInThisDay(startOfDay, endOfDay);
    }

    public List<Order> getActiveOrders() {
        List<OrderStatus> excludedStatuses = Arrays.asList(OrderStatus.COMPLETED, OrderStatus.CANCELLED);
        return orderRepository.findOrderByStatusNotIn(excludedStatuses);
    }

    public List<Order> getOrdersByEmail(String searchInput) {
        return orderRepository.findOrdersByEmail(searchInput);
    }

    public List<Order> getOrdersById(String searchInput) {
        return orderRepository.findOrdersById(searchInput);
    }
    public void setOrderStatusPaymentReceived(Long id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        orderOptional.ifPresent(order -> order.setStatus(OrderStatus.PAYMENT_RECEIVED));
    }

    public List<Order> getOrdersByDateRange(String startDate, String endDate) {
        return orderRepository.findOrdersByEmail("aa@aa");
    }
}
