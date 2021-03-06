package com.balavignesh.restdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class AccountResourceTest {

    @BeforeAll
    static void header(){
        log.debug("Starting Testing AccountResourceTest");
    }
    @AfterAll
    static void footer(){
        log.debug("Ended Testing AccountResourceTest");
    }

    @Test
    @DisplayName("Testing Get All Account List")
    public void testGetAccountList(){
        log.debug("Entering Test for testGetAccountList");
    }

}
