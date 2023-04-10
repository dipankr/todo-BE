package com.dipankr.todoBE.service;

public class ResponseService {
    public static String getResponseJson(String data, Boolean error, String message) {
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

        sb.append("}}");
        return sb.toString();
    }
}
