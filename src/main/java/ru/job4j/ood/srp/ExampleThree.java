package ru.job4j.ood.srp;

import java.net.Socket;
import java.sql.Connection;

public class ExampleThree {
    /**
     *Наш класс нарушает принцип spr, так как знает как будет произведена инициализация connection и socket, а
     * так же будет отвечать за реализацию по работе с БД и по сети. Нам нужно создать отдельные классы для
     * инициализации connection и socket по отдельности, а так же классы, которые будут уже не посредственно работать
     * с экземплярами классов инициализаторов.
     *
     * */
    private final Connection connection;
    private final Socket socket;

    public ExampleThree(Connection connection, Socket socket) {
        this.connection = connection;
        this.socket = socket;
    }
}
