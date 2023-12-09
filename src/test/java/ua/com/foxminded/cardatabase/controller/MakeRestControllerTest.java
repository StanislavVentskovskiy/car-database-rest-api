package ua.com.foxminded.cardatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.com.foxminded.cardatabase.dao.impl.MakeDaoImpl;
import ua.com.foxminded.cardatabase.dao.repository.MakeRepository;
import ua.com.foxminded.cardatabase.model.Make;
import ua.com.foxminded.cardatabase.service.impl.MakeServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MakeRestController.class)
public class MakeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MakeRepository makeRepository;

    @MockBean
    private MakeDaoImpl makeDao;

    @MockBean
    private MakeServiceImpl makeService;

    private int testMakeId = 1;
    private Make testMake = new Make();

    @Test
    @WithMockUser(value = "USER")
    public void testGetAllMakes_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/makes/get")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testGetSingleMake_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/makes/get/"+testMakeId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testCreateMake_shouldReturnStatusIsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/makes").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testMake)))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testUpdateMake_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/api/makes/"+testMakeId).with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(testMake)))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testDeleteMake_shouldReturnStatusIsNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/makes/"+testMakeId).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testMake)))
            .andExpect(status().isNoContent());
    }
}
