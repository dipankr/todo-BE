package com.dipankr.todobe.service.impl;

import com.dipankr.todobe.service.AsyncThreadService;
import org.springframework.stereotype.Service;

@Service
public class AsyncThreadUsingRunnable implements AsyncThreadService {

    @Override
    public void async(Runnable r) {
        try {
            new Thread(r).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
