package ua.com.foxminded.cardatabase.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.model.Model;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql"})
@SpringBootTest
public class ModelDaoImplTest {

    @Mock
    ModelDaoImpl modelDaoImpl;

    Optional<Model> expectedOptionalModel;
    Optional<Model> actualOptionalModel;

    private int testModelId = 1;
    private String testModelName = "test";
    private Model expectedModel;
    private Model actualModel;
    private List<Model> expectedModelList;
    private List<Model> actualModelList;

    @Test
    public void testDeleteModel_shouldReturnTrue() {
        modelDaoImpl.deleteModel(testModelId);
        verify(modelDaoImpl, Mockito.times(1)).deleteModel(testModelId);
    }

    @Test
    public void testAddModel_shouldReturnModel() {
        Mockito.when(modelDaoImpl.addModel(expectedModel)).thenReturn(expectedModel);
        actualModel = modelDaoImpl.addModel(expectedModel);
        assertEquals(expectedModel,actualModel);
    }

    @Test
    public void testUpdateModel_shouldReturnModel() {
        Mockito.when(modelDaoImpl.updateModel(expectedModel)).thenReturn(expectedModel);
        actualModel = modelDaoImpl.updateModel(expectedModel);
        assertEquals(expectedModel,actualModel);
    }

    @Test
    public void testGetModel_shouldReturnModel() {
        Mockito.when(modelDaoImpl.getModel(testModelId)).thenReturn(expectedOptionalModel);
        actualOptionalModel = modelDaoImpl.getModel(testModelId);
        assertEquals(expectedOptionalModel,actualOptionalModel);
    }

    @Test
    public void testGetModelByName_shouldReturnModel() {
        Mockito.when(modelDaoImpl.getModelByName(testModelName)).thenReturn(expectedOptionalModel);
        actualOptionalModel = modelDaoImpl.getModelByName(testModelName);
        assertEquals(actualOptionalModel,expectedOptionalModel);
    }

    @Test
    public void testGetAllModelList() {
        Mockito.when(modelDaoImpl.getAllModels()).thenReturn(expectedModelList);
        actualModelList = modelDaoImpl.getAllModels();
        assertEquals(expectedModelList, actualModelList);
    }
}
