package com.springboot.jwt.controller;

import com.springboot.jwt.config.TokenProvider;
import com.springboot.jwt.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private UserServiceImpl userService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = {"USER","ADMIN"})
    public void givenCredentials_whenRole_ReturnCurrentUser() throws Exception{

        User user = (User) User.builder().username("admin.joe").password("jwtpass").authorities(new String[]{"ROLE_ADMIN"}).build();
        Mockito.when(tokenProvider.getUsernameFromToken(ArgumentMatchers.any())).thenReturn(user.getUsername());
        Mockito.when(userService.loadUserByUsername(user.getUsername())).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/").header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9"))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldGet_Unauthorized_WithoutRole() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/"))
                .andExpect(status().isUnauthorized());
    }
}