package com.model;

import com.enums.HTTPMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.http.HttpClient;
import java.util.Map;

@AllArgsConstructor
@Getter
public class HttpRequest {

    private HTTPMethod method;
    private String path;
    private HttpClient.Version version;
    private Map<String, String> headers;

    @Override
    public String toString() {
        return "uri:" + path + "--" +
                "method:" + method + "--" +
                "version:" + version + "--" +
                "headers: " + headers.toString().substring(1, headers.toString().length() - 1);
    }
}
