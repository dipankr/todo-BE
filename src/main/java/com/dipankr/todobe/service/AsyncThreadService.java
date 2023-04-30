package com.dipankr.todobe.service;

public interface AsyncThreadService {

    /**
     * Runs the specified task in a background thread
     *
     * @param r predicate or lambda function
     */
    void async(Runnable r);

}
