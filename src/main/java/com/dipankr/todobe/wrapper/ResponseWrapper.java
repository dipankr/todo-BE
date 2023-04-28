package com.dipankr.todobe.wrapper;

import com.dipankr.todobe.entity.Todo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ResponseWrapper {

    public static ResponseEntity<?> getResponseJson(List<Todo> data, Boolean error, String message, @NonNull HttpStatus httpStatus) {
        return wrapResponse(data, error, message, httpStatus);
    }

    private static ResponseEntity<?> wrapResponse(List<Todo> data, Boolean error, String message, @NonNull HttpStatus httpStatus) {
        Map<String, Object> body = new HashMap<>();
        if (null != data) {
            body.put("data", data);
        }
        if (null != error) {
            body.put("error", error);
        }
        if (null != message) {
            body.put("message", message);
        }
        body.put("code", httpStatus.value());

        return ResponseEntity.status(httpStatus).body(body);
    }
}
