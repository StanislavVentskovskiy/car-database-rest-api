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
import ua.com.foxminded.cardatabase.dao.impl.CarDaoImpl;
import ua.com.foxminded.cardatabase.dao.repository.CarRepository;
import ua.com.foxminded.cardatabase.model.Car;
import ua.com.foxminded.cardatabase.service.impl.CarServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarRestController.class)
public class CarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarDaoImpl carDao;

    @MockBean
    private CarServiceImpl carService;

    private String expectedCarId = "cptB1C1NSL";
    private Car testCar = new Car();
    private String testCarId = "test";
    private String testCarMake = "testMake";
    private int testCarYear = 1;

    @Test
    @WithMockUser(value = "USER")
    public void testGetAllCarsRequest_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/cars/get").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testGetSingleCarRequest_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/cars/get/" + testCarId  ).with(csrf())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testPostCar_shouldReturnStatusIsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/cars").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(testCar)))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testUpdateCar_shouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .put("/api/cars/"+testCarId).with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(testCar)))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testDeleteCar_shouldReturnStatusIsNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/cars/"+testCarId).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testCar)))
            .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(value = "USER")
    public void testFindCar_shouldReturnStatusIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/cars/get/search").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .param("make", testCarMake)
                .param("minYear", String.valueOf(testCarYear)))
            .andExpect(status().isOk());
    }
}
