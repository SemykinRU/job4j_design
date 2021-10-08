package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (Arrays.stream(str.split(" "))
                                .flatMap(p -> Arrays.stream(p.split("=")))
                                .anyMatch("Exit"::equals)) {
                            out.write(("Server was closed" + System.lineSeparator()).getBytes());
                            server.close();
                            break;
                        }
                        if (Arrays.stream(str.split(" "))
                                .flatMap(p -> Arrays.stream(p.split("=")))
                                .anyMatch("Hello"::equals)) {
                            out.write(("Hello, dear friend." + System.lineSeparator()).getBytes());
                            break;
                        }
                        if (Arrays.stream(str.split(" "))
                                .flatMap(p -> Arrays.stream(p.split("=")))
                                .findFirst().isPresent()) {
                            out.write(("What." + System.lineSeparator()).getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }


}