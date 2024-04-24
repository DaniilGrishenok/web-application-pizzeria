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
        model.addAttribute("orders", orders);
        model.addAttribute("orderStatus", OrderStatus.values());
        return "adminOrderPage";
    }
    @PostMapping("/admin/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/ordersPanel";
    }
    @PostMapping("/admin/editOrder/{id}")
    public String editOrder(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        orderService.editOrder(id, orderStatus);
        return "redirect:/admin/ordersPanel";
    }
    @PostMapping("/admin/viewOrder/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        orderOptional.ifPresent(order -> model.addAttribute("order", order));
        return "redirect:/admin/ordersPanel"; // Это имя шаблона (viewOrder.html)
    }

}
