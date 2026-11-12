package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static void main() {
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        board.add("Client 1");
        board.add("Client 2");
        board.add("Client 3");
        board.add("Client 4");
        board.draw();

        board.deliver();
        board.draw();

        board.deliver(3);
        board.draw();

        board.deliver(100);

        try {
            board.add("  ");
        } catch (IllegalArgumentException ex) {
            log.error("Error placing an order", ex);
        }
    }
}
