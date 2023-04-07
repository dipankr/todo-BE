package com.todolist.todo.service;

import java.util.concurrent.atomic.AtomicInteger;

public class IDService {
    private final AtomicInteger ID;
    private static IDService idService;

    private IDService() {
        ID = new AtomicInteger(1000);
    }

    public static IDService getInstance() {
        if (null == idService) {
            synchronized (IDService.class) {
                if (null == idService) {
                    idService = new IDService();
                }
            }
        }
        return idService;
    }

    public Integer getID(){
        return idService.ID.addAndGet(1);
    }
}
