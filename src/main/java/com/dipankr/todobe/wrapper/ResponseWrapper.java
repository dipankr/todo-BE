package com.dipankr.todobe.wrapper;

import com.dipankr.todobe.entity.Todo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Deprecated
public class ResponseWrapper {

    /**
     * Construct a Response using the given data, error, message and httpStatus
     *
     * @param data list of todos
     * @param error flag to indicate if there's an error
     * @param message message to display
     * @param httpStatus status code
     * @return ResponseEntity containing the response in JSON format
     */
    public static ResponseEntity<?> getResponseJson(List<Todo> data, Boolean error, String message, @NonNull HttpStatus httpStatus) {
        return wrapResponse(data, error, message, httpStatus);
    }

    /**
     * Construct a Response using the given data, error, message and httpStatus <br/> Uses a map to set the key:value in the response
     *
     * @param data list of todos
     * @param error flag to indicate if there's an error
     * @param message message to display
     * @param httpStatus status code
     * @return ResponseEntity containing the response in JSON format
     */
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
