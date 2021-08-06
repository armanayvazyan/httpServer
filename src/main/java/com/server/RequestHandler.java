package com.server;

import com.enums.HTTPMethod;
import com.model.HttpRequest;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import com.google.common.base.Splitter;

import java.net.Socket;
import java.net.http.HttpClient;
import java.util.Map;

import static com.utils.StringUtils.splitToMap;

public class RequestHandler {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows(IOException.class)
    public HttpRequest getRequest() {
        System.out.println("Accepted: " + socket.getInetAddress());

        InputStream inputStream = socket.getInputStream();

        byte[] buf = new byte[32 * 1024];
        int read = inputStream.read(buf);
        String line = new String(buf, 0, read);

        Map<String, String> request = splitToMap(line);

        HTTPMethod method = HTTPMethod.valueOf(request.get("method"));
        String url = request.get("uri");
        HttpClient.Version version = HttpClient.Version.valueOf(request.get("version"));
        Map<String, String> headers =  Splitter.on(", ").withKeyValueSeparator("=").split(request.get("headers"));
        System.out.println(request.toString());

        return new HttpRequest(method, url, version, headers);
    }
}
