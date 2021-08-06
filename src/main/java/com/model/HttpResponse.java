package com.model;

import com.enums.StatusCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.http.HttpClient;

@AllArgsConstructor
@Getter
public class HttpResponse {

    private StatusCodes statusCode;
    private String date;
    private HttpClient.Version version;
    private String body;

    @Override
    public String toString() {
        return
                "statusCode:" + statusCode + "--" +
                        "date:" + date + "--" +
                        "version:" + version + "--" +
                        "body: " + body;
    }
}
