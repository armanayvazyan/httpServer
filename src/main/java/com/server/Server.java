package com.server;

import com.config.Configuration;
import com.enums.HTTPMethod;
import com.enums.StatusCodes;
import com.model.HttpRequest;
import com.model.HttpResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpClient;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

    @SneakyThrows({IOException.class, InterruptedException.class, ExecutionException.class})
    static void start() {
        ServerSocket serverSocket = new ServerSocket(Configuration.PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            ExecutorService service = Executors.newSingleThreadExecutor();

            RequestHandler handler = new RequestHandler(socket);
            Future<HttpRequest> submit = service.submit(handler::getRequest);

            HttpRequest request = submit.get();

            if (request.getPath().equals("localhost/") && request.getMethod().equals(HTTPMethod.GET)) {

                HttpResponse response = new HttpResponse(StatusCodes.SUCCESS, new Date().toString(), HttpClient.Version.HTTP_1_1, "Received!!");
                ResponseSender sender = new ResponseSender(socket, response);
                service.submit(sender::send);
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
