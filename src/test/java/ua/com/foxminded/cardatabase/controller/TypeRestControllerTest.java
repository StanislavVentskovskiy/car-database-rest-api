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
import ua.com.foxminded.cardatabase.dao.impl.TypeDaoImpl;
import ua.com.foxminded.cardatabase.dao.repository.TypeRepository;
import ua.com.foxminded.cardatabase.model.Type;
import ua.com.foxminded.cardatabase.service.impl.TypeServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TypeRestController.class)
public class TypeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeRepository typeRepository;

    @MockBean
    private TypeDaoImpl typeDao;

    @MockBean
    private TypeServiceImpl typeService;

    private int testTypeId = 1;
    private Type testType = new Type();

    @Test
    @WithMockUser(value = "USER")
    public void testGetAllTypes_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/types/get")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testGetSingleType_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/types/get/" + testTypeId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testCreateType_shouldReturnStatusIsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/types").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testType)))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testUpdateType_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/types/" + testTypeId).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testType)))
            .andExpect(status().isNotFound());
    }
}
