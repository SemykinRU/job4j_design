package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (in.ready()) {
                        String str = in.readLine();
                        if (Arrays.stream(str.split(" "))
                                .flatMap(p -> Arrays.stream(p.split("=")))
                                .anyMatch("Bye"::equals)) {
                            socket.close();
                            server.close();
                            break;
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}