package com.dipankr.todobe.service;

public class AsyncThreadService {

    public static void async(Runnable r) {
        try {
            new Thread(r).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
