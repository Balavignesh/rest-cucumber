package com.balavignesh.restdemo.services;

import com.balavignesh.restdemo.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AccountResourceTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllAccount() throws Exception {
        this.mockMvc.perform(get("/api/account")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("10000")));
    }

    @Test
    public void getAccount() throws Exception {
        this.mockMvc.perform(get("/api/account/10000")).
                andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("10000")));
    }

    @Test
    public void createAccount() throws Exception {
        Account account = new Account();
        account.setAccountName("Testing");
        account.setAccountType("DDA");
        account.setAccountStatus(false);
        this.mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account))).
                andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void getAccountError() throws Exception {
        this.mockMvc.perform(get("/api/account/10005"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void deleteAccount() throws Exception {
        this.mockMvc.perform(delete("/api/account/10002"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Account deleted")));;
    }

    @Test
    public void deleteAccountError() throws Exception {
        this.mockMvc.perform(delete("/api/account/10005"))
                .andDo(print()).andExpect(status().isNotFound());
    }

}
