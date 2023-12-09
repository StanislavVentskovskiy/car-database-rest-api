package ua.com.foxminded.cardatabase.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.model.Car;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql"})
@SpringBootTest
public class CarDaoImplTest {

    @Mock
    CarDaoImpl carDaoImpl;

    Optional<Car> expectedOptionalCar;
    Optional<Car> actualOptionalCar;
    private Car expectedCar;
    private Car actualCar;
    private List<Car> expectedCarList;
    private List<Car> actualCarList;
    private String testCarId = "1";
    private String testCarMake = "test";
    private int testCarMinYear = 1;

    @Test
    public void testDeleteCar_shouldReturnTrue() {
        carDaoImpl.deleteCar(testCarId);
        verify(carDaoImpl,Mockito.times(1)).deleteCar(Mockito.any());
    }

    @Test
    public void testAddCar_shouldReturnTrue() {
        Mockito.when(carDaoImpl.addCar(expectedCar)).thenReturn(expectedCar);
        actualCar = carDaoImpl.addCar(expectedCar);
        assertEquals(expectedCar,actualCar);
    }

    @Test
    public void testUpdateCar_shouldReturnTrue() {
        Mockito.when(carDaoImpl.updateCar(expectedCar)).thenReturn(expectedCar);
        actualCar = carDaoImpl.updateCar(expectedCar);
        assertEquals(actualCar, expectedCar);
    }

    @Test
    public void testGetCar_shouldReturnOptionalCar() {
        Mockito.when(carDaoImpl.getCar(testCarId)).thenReturn(expectedOptionalCar);
        actualOptionalCar = carDaoImpl.getCar(testCarId);
        assertEquals(actualOptionalCar,expectedOptionalCar);
    }

    @Test
    public void testGetAllCars_shouldReturnCarList() {
        Mockito.when(carDaoImpl.getAllCars()).thenReturn(expectedCarList);
        actualCarList = carDaoImpl.getAllCars();
        assertEquals(expectedCarList, actualCarList);
    }

    @Test
    public void testSearchCar_shouldReturnCar() {
        Mockito.when(carDaoImpl.searchCars(testCarMake,testCarMinYear)).thenReturn(expectedCarList);
        actualCarList = carDaoImpl.searchCars(testCarMake,testCarMinYear);
        assertEquals(expectedCarList,actualCarList);
    }
}
