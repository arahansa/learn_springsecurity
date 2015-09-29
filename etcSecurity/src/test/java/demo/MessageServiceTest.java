package demo;

import demo.service.HelloMessageService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;




import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by arahansa on 2015-09-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EtcSecurityApplication.class)
@WebAppConfiguration
public class MessageServiceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Autowired
    HelloMessageService messageService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void 인증되지않은사용자는_AuthenticationCredentialsNotFoundException를_발생시킨다() {
        messageService.getMessage();
    }

    @Test
    @WithMockUser
    public void getMessageWithMockUser() {
        String message = messageService.getMessage();
        System.out.println("받은 메시지 :"+message);
        assertNotNull(message);
    }



    @Test
    @WithMockUser(username="admin", authorities = {"ADMIN", "USER"})
    public void getMessageWithMockUserCustomAuthorities() {
        String message = messageService.getMessage();
        System.out.println("어드민으로 받은 메시지?:"+message);
        assertNotNull(message);
    }

    @Test
    public void mvcAdminTest() throws Exception {
        ResultActions result = mvc.perform(
                get("/admin")
                        .with(user("admin").password("pass").roles("USER", "ADMIN")));
        result.andDo(print());
        result.andExpect(status().isOk());
    }


}
