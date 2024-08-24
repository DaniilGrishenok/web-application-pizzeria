package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.service.OrderService;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    @GetMapping("/admin/ordersPanel")
    public String allOrders(Model model){
        List<Order> orders = orderService.getAllOrders();
        int quantityActiveOrders = orderService.quantityActiveOrders();
        int quantityOrdersInThisDay = orderService.quantityOrdersInThisDay();
        List<Order> activeOrders = orderService.getActiveOrders();
        model.addAttribute("activeOrders", activeOrders);
        model.addAttribute("orders", orders);
        model.addAttribute("quantityActiveOrders", quantityActiveOrders);
        model.addAttribute("quantityOrdersInThisDay", quantityOrdersInThisDay);
        model.addAttribute("orderStatus", OrderStatus.values());
        return "adminOrderPage";
    }
    @GetMapping("/order/payment/{id}")
    public String payment(@PathVariable("id") Long id, Model model){
        // Предполагается, что у вас есть сервис orderService, который возвращает объект Order по его id
        Optional<Order> order = orderService.getOrderById(id);
        if (order != null) {
            orderService.setOrderStatusPaymentReceived(id);
            model.addAttribute("order", order);
            return "paymentPage"; // Возвращает страницу оплаты
        } else {
            return "error"; // Если заказ не найден, можно вернуть страницу с ошибкой
        }
    }

    @PostMapping("/admin/orderSearch")
    public String search(@RequestParam String searchType,
                         @RequestParam String searchInput,
                         @RequestParam(required = false) String startDate,
                         @RequestParam(required = false) String endDate,
                         Model model) {

        if ("email".equals(searchType)) {
            List<Order> searchResults = orderService.getOrdersByEmail(searchInput);
            model.addAttribute("searchResults", searchResults);
        } else if ("order-number".equals(searchType)) {
            List<Order> searchResults = orderService.getOrdersById(searchInput);
            model.addAttribute("searchResults", searchResults);
        } else if ("date-range".equals(searchType)) {
            List<Order> searchResults = orderService.getOrdersByDateRange(startDate, endDate);
            model.addAttribute("searchResults", searchResults);
        }

        return "adminOrderPage";
    }
    @PostMapping("/admin/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/ordersPanel";
    }
    @PostMapping("/admin/editOrder/{id}")
    public String editOrder(@PathVariable Long id, @RequestParam Order order, @RequestParam OrderStatus orderStatus){
        orderService.editOrder(id, order, orderStatus);
        return "redirect:/admin/ordersPanel";
    }
    @PostMapping("/admin/viewOrder/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        orderOptional.ifPresent(order -> model.addAttribute("order", order));
        return "redirect:/admin/ordersPanel";
    }

}
