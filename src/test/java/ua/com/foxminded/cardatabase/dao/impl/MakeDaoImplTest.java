package ua.com.foxminded.cardatabase.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.model.Make;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql"})
@SpringBootTest
public class MakeDaoImplTest {

    @Mock
    MakeDaoImpl makeDaoImpl;

    Optional<Make> expectedOptionalMake;
    Optional<Make> actualOptionalMake;

    private int testMakeId = 1;
    private Make expectedMake;
    private Make actualMake;
    private String testMakeName = "test";
    private List<Make> expectedMakeList;
    private List<Make> actualMakeList;

    @Test
    public void testDeleteId_shouldReturnTrue() {
        makeDaoImpl.deleteMake(testMakeId);
        verify(makeDaoImpl, Mockito.times(1)).deleteMake(Mockito.anyInt());
    }

    @Test
    public void testAddMake_shouldReturnMake() {
        Mockito.when(makeDaoImpl.addMake(expectedMake)).thenReturn(expectedMake);
        actualMake = makeDaoImpl.addMake(expectedMake);
        assertEquals(expectedMake,actualMake);
    }

    @Test
    public void testUpdateMake_shouldReturnMake() {
        Mockito.when(makeDaoImpl.updateMake(expectedMake)).thenReturn(expectedMake);
        actualMake = makeDaoImpl.updateMake(expectedMake);
        assertEquals(expectedMake,actualMake);
    }

    @Test
    public void testGetMake_shouldReturnOptionalMake() {
        Mockito.when(makeDaoImpl.getMake(testMakeId)).thenReturn(expectedOptionalMake);
        actualOptionalMake = makeDaoImpl.getMake(testMakeId);
        assertEquals(expectedOptionalMake,actualOptionalMake);
    }

    @Test
    public void testGetMakeByName_shouldReturnMake() {
        Mockito.when(makeDaoImpl.getMakeByName(testMakeName)).thenReturn(expectedOptionalMake);
        actualOptionalMake = makeDaoImpl.getMakeByName(testMakeName);
        assertEquals(expectedOptionalMake,actualOptionalMake);
    }

    @Test
    public void getAllMakes_shouldReturnMakeList() {
        Mockito.when(makeDaoImpl.getAllMakes()).thenReturn(expectedMakeList);
        actualMakeList = makeDaoImpl.getAllMakes();
        assertEquals(expectedMakeList, actualMakeList);
    }
}
