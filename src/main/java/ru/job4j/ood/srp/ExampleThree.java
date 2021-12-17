package ru.job4j.ood.srp;

import java.net.Socket;
import java.sql.Connection;

public class ExampleThree {
    private final Connection connection;
    private final Socket socket;

    public ExampleThree(Connection connection, Socket socket) {
        this.connection = connection;
        this.socket = socket;
    }
}
