package com.springboot.jwt.controller;

import com.springboot.jwt.config.TokenProvider;
import com.springboot.jwt.model.Role;
import com.springboot.jwt.service.UserService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

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
    @WithMockUser(roles = "ADMIN")
    public void givenCredentials_whenRoleAsAdmin_getAllUsers() throws Exception {

        Role role = Role.builder().roleType("ADMIN").description("Admin - Has permission to perform admin tasks").build();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = (User) User.builder().username("admin.joe").password("jwtpass").authorities(new String[]{"ROLE_ADMIN"}).build();

        com.springboot.jwt.model.User user1 = new com.springboot.jwt.model.User().toBuilder().username("admin.joe").firstName("Joe").lastName("Anne").roles(roleSet).build();
        List<com.springboot.jwt.model.User> users = new ArrayList<>();
        users.add(user1);
        Mockito.when(tokenProvider.getUsernameFromToken(ArgumentMatchers.any())).thenReturn(user.getUsername());
        Mockito.when(userService.loadUserByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(userService.findAllUsers()).thenReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/users/").header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", is("admin.joe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName ", is("Anne")));;

    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenCredentials_whenRoleAsUser_getUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/users/"))
                .andExpect(status().isForbidden());
    }

}