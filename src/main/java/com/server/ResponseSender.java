package com.server;

import com.model.HttpResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ResponseSender {

    private Socket socket;
    private HttpResponse response;

    public ResponseSender(Socket socket, HttpResponse message) {
        this.socket = socket;
        this.response = message;
    }

    @SneakyThrows(IOException.class)
    public void send() {
        OutputStream outputStream = socket.getOutputStream();

        System.out.println("Sending response to" + socket.getInetAddress());
        outputStream.write(response.toString().getBytes());
        outputStream.flush();
    }
}
