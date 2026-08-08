// Order.java
package com.example.orders;

public record Order(
        int id,
        String product,
        int quantity,
        double price
) {}
