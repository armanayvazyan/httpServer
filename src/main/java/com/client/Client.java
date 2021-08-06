package com.client;


import com.config.Configuration;
import com.enums.HTTPMethod;
import com.enums.StatusCodes;
import com.model.HttpRequest;
import com.model.HttpResponse;
import lombok.SneakyThrows;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.util.*;

import static com.utils.StringUtils.*;

public class Client {

    @SneakyThrows({IOException.class})
    public static void main(String[] args) {
        Socket socket = new Socket(Configuration.HOST, Configuration.PORT);

        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(),"UTF8"));

        HttpRequest request = new HttpRequest(HTTPMethod.GET, Configuration.HOST + "/", HttpClient.Version.HTTP_1_1,Map.of("key1", "value1", "key2", "value2"));
        sendMessage(writer, request);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        receiveResponse(reader);

        writer.close();
        reader.close();
    }


    @SneakyThrows(IOException.class)
    private static void receiveResponse(BufferedReader in) {
        System.out.println("\n \nResponse/>");

        char[] buf = new char[32 * 1024];
        int read = in.read(buf);
        String line = new String(buf, 0, read);

        Map<String, String> responseMap = splitToMap(line);

        StatusCodes statusCodes = StatusCodes.valueOf(responseMap.get("statusCode"));
        String data = responseMap.get("date");
        HttpClient.Version version = HttpClient.Version.valueOf(responseMap.get("version"));
        String body = responseMap.get("body");

        HttpResponse response = new HttpResponse(statusCodes, data, version, body);
        printResponsePrettier(response);
    }

    @SneakyThrows(IOException.class)
    private static void sendMessage(BufferedWriter out, HttpRequest request) {
        System.out.println("Request/>");
        printRequestPrettier(request);
        for (String line : getContents(request)) {
            out.write(line);
        }
        out.flush();
    }

    private static List<String> getContents(HttpRequest request) {
        List<String> contents = new ArrayList<>();

        String requestString = request.toString();
        String[] splitRequest = requestString.split("\\{");

        Iterator<String> iterator = Arrays.stream(splitRequest).iterator();

        while (iterator.hasNext()) {
            contents.add(iterator.next());
        }
        return contents;
    }

}
