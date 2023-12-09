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
import ua.com.foxminded.cardatabase.dao.impl.ModelDaoImpl;
import ua.com.foxminded.cardatabase.dao.repository.ModelRepository;
import ua.com.foxminded.cardatabase.model.Model;
import ua.com.foxminded.cardatabase.service.impl.ModelServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ModelRestController.class)
public class ModelRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelRepository modelRepository;

    @MockBean
    private ModelDaoImpl modelDao;

    @MockBean
    private ModelServiceImpl modelService;

    private int testModelId = 1;
    private Model testModel = new Model();

    @Test
    @WithMockUser(value = "USER")
    public void testGetAllModels_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/models/get/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testGetSingleModel_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/models/get/"+testModelId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testCreateModel_shouldReturnStatusIsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/models").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testModel)))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testUpdateModel_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/makes/"+testModelId).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testModel)))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testDeleteModel_shouldReturnStatusIsNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/models/"+testModelId).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testModel)))
            .andExpect(status().isNoContent());
    }
}
