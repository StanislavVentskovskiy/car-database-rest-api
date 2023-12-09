package ua.com.foxminded.cardatabase.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.dao.impl.TypeDaoImpl;
import ua.com.foxminded.cardatabase.model.Type;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {TypeServiceImpl.class})
public class TypeServiceImplTest {

    @Autowired
    private TypeServiceImpl typeService;

    @MockBean
    private TypeDaoImpl typeDao;

    private int typeTestId = 1;
    private String typeTestName = "test";
    private Type testType = new Type();

    @Test
    public void testDeleteType_shouldCallDaoMethod() {
        typeDao.deleteType(typeTestId);
        verify(typeDao, Mockito.times(1)).deleteType(typeTestId);
    }

    @Test
    public void testAddType_shouldCallDaoMethod() {
        typeDao.addType(testType);
        verify(typeDao, Mockito.times(1)).addType(testType);
    }

    @Test
    public void testGetType_shouldCallDaoMethod() {
        typeDao.getType(typeTestId);
        verify(typeDao, Mockito.times(1)).getType(typeTestId);
    }

    @Test
    public void testGetAllType_shouldCallDaoMethod() {
        typeDao.getAllTypes();
        verify(typeDao,Mockito.times(1)).getAllTypes();
    }

    @Test
    public void testGetTypeByName_shouldCallDaoMethod() {
        typeDao.getTypeByName(typeTestName);
        verify(typeDao,Mockito.times(1)).getTypeByName(typeTestName);
    }
}
