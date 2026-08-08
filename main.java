package com.example.orders;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        Order order = new Order(
                1001,
                "Laptop",
                1,
                1299.99
        );

        orderService.placeOrder(order);

        System.out.println("Application started.");
    }
}
