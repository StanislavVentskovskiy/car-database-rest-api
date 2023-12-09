package ua.com.foxminded.cardatabase.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.dao.impl.ModelDaoImpl;
import ua.com.foxminded.cardatabase.model.Model;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ModelServiceImpl.class})
public class ModelServiceImplTest {

    @Autowired
    private ModelServiceImpl modelService;

    @MockBean
    private ModelDaoImpl modelDao;

    private int modelTestId = 1;
    private String modelTestName = "test";
    private Model testModel = new Model();

    @Test
    public void testDeleteModel_shouldCallDaoMethod() {
        modelDao.deleteModel(modelTestId);
        verify(modelDao, Mockito.times(1)).deleteModel(modelTestId);
    }

    @Test
    public void testAddModel_shouldCallDaoMethod() {
        modelDao.addModel(testModel);
        verify(modelDao, Mockito.times(1)).addModel(testModel);
    }

    @Test
    public void testGetModel_shouldCallDaoMethod() {
        modelDao.getModel(modelTestId);
        verify(modelDao, Mockito.times(1)).getModel(modelTestId);
    }

    @Test
    public void testGetAllModel_shouldCallDaoMethod() {
        modelDao.getAllModels();
        verify(modelDao,Mockito.times(1)).getAllModels();
    }

    @Test
    public void testGetModelByName_shouldCallDaoMethod() {
        modelDao.getModelByName(modelTestName);
        verify(modelDao,Mockito.times(1)).getModelByName(modelTestName);
    }
}
