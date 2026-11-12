package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeOrderBoard {
    private static final Logger log = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private int nextOrderNumber = 0;

    private final List<Order> orders = new ArrayList<>();

    public Order add(String clientName) {
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be null or blank");
        }

        Order order = new Order(++nextOrderNumber, clientName);

        orders.add(order);

        log.info("Order #{} added for '{}'", order.number(), order.clientName());
        log.debug("Orders in queue: {}", orders.size());

        return order;
    }

    public Optional<Order> deliver() {
        if (this.orders.isEmpty()) {
            log.warn("Nothing to deliver: queue is empty");
            return Optional.empty();
        }

        Order order = orders.removeFirst();

        log.info("Order #{} delivered to '{}'", order.number(), order.clientName());
        log.debug("Orders in queue: {}", orders.size());

        return Optional.of(order);
    }

    public Optional<Order> deliver(int orderNumber) {
        log.debug("Looking for order #{} to deliver", orderNumber);

        Iterator<Order> iterator = this.orders.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();

            if (order.number() == orderNumber) {
                iterator.remove();

                log.info("Order #{} delivered to '{}' by number", order.number(), order.clientName());
                log.debug("Orders in queue: {}", orders.size());

                return Optional.of(order);
            }
        }

        log.warn("Order #{} not found, nothing delivered", orderNumber);

        return Optional.empty();
    }

    public void draw() {
        log.debug("Drawing queue with {} order(s)", orders.size());

        String header = "Num | Name";

        if (this.orders.isEmpty()) {
            System.out.println(header);
            return;
        }

        StringBuilder sb = new StringBuilder(header);
        Order firstOrder = this.orders.getFirst();

        sb.append("%n%d | %s".formatted(firstOrder.number(), firstOrder.clientName()));

        for (int i = 1; i < this.orders.size(); i++) {
            Order order = this.orders.get(i);

            sb.append("%n%d | %s".formatted(order.number(), order.clientName()));
        }

        System.out.println(sb);
    }
}
