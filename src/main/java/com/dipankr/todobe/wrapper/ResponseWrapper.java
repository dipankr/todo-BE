package com.dipankr.todobe.wrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseWrapper {

    public static ResponseEntity<?> getResponseJson(String data, Boolean error, String message, HttpStatus httpStatus) {
        StringBuilder sb = new StringBuilder().append("{")
            .append("\"response\": ").append("{");

        if (data != null && !data.isEmpty()) {
            sb.append("\"data\":").append(data).append(",");
        }

        if (error != null) {
            sb.append("\"error\":").append(error).append(",");
        }

        sb.append("\"message\":");
        if (message != null && !message.isEmpty()) {
            sb.append("\"").append(message).append("\"");
        } else sb.append("\"\"");
        sb.append(",");

        sb.append("\"code\": ").append(httpStatus.value());
        sb.append("}}");
        return new ResponseEntity<>(sb.toString(), httpStatus);
    }
}
