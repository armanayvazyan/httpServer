package com.utils;

import com.model.HttpRequest;
import com.model.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static Map<String, String> splitToMap(String in) {
        String[] split = in.split("--");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(":");
            map.put(split1[0], split1[1]);
        }
        return map;
    }

    public static void printResponsePrettier(HttpResponse response) {
        System.out.println(response.getStatusCode() + " " + response.getStatusCode().getCode());
        System.out.println("Date: " + response.getDate());
        System.out.println("Version: " + response.getVersion());
        System.out.println("-------------------------------------");
        System.out.println("Body: " + response.getBody());
    }

    public static void printRequestPrettier(HttpRequest request) {
        System.out.println("Method:" + request.getMethod());
        System.out.println("URL: " + request.getPath());
        System.out.println("Version: " + request.getVersion());
        System.out.println("Headers: " + request.getHeaders().toString());
    }
}
