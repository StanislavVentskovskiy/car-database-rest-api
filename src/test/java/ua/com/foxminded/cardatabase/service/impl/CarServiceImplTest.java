package ua.com.foxminded.cardatabase.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.dao.impl.CarDaoImpl;
import ua.com.foxminded.cardatabase.model.Car;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    @Autowired
    private CarServiceImpl carService;

    @MockBean
    private CarDaoImpl carDao;

    private String carTestId = "test";
    private int testCarYear = 1;
    private Car testCar = new Car();

    @Test
    public void testDeleteCar_shouldCallDaoMethod() {
        carService.deleteCar(carTestId);
        verify(carDao, Mockito.times(1)).deleteCar(Mockito.any());
    }

    @Test
    public void testAddCar_shouldCallDaoMethod() {
        carService.addCar(testCar);
        verify(carDao, Mockito.times(1)).addCar(testCar);
    }

    @Test
    public void testGetCar_shouldCallDaoMethod() {
        carService.getCar(carTestId);
        verify(carDao, Mockito.times(1)).getCar(carTestId);
    }

    @Test
    public void testGetAllCars_shouldCallDaoMethod() {
        carService.getAllCars();
        verify(carDao,Mockito.times(1)).getAllCars();
    }

    @Test
    public void searchCars_shouldCallDaoMethod() {
        carService.searchCars(carTestId, testCarYear);
        verify(carDao,Mockito.times(1)).searchCars(carTestId,testCarYear);
    }
}
