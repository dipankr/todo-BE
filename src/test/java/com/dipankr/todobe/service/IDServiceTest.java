package com.dipankr.todobe.service;

import org.junit.jupiter.api.Test;

class IDServiceTest {

    @Test
    void testGetID() {
        for(int i=0; i<10; i++) {
            System.out.println(IDService.getInstance().getID());
        }
    }
}