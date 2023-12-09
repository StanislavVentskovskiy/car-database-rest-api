package ua.com.foxminded.cardatabase.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.dao.impl.MakeDaoImpl;
import ua.com.foxminded.cardatabase.model.Make;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {MakeServiceImpl.class})
public class MakeServiceImplTest {

    @Autowired
    private MakeServiceImpl makeService;

    @MockBean
    private MakeDaoImpl makeDao;

    private int makeTestId = 1;
    private String testMakeName = "test";
    private Make testMake = new Make();

    @Test
    public void testDeleteMake_shouldCallDaoMethod() {
        makeDao.deleteMake(makeTestId);
        verify(makeDao, Mockito.times(1)).deleteMake(makeTestId);
    }

    @Test
    public void testAddMake_shouldCallDaoMethod() {
        makeDao.addMake(testMake);
        verify(makeDao, Mockito.times(1)).addMake(testMake);
    }

    @Test
    public void testGetMake_shouldCallDaoMethod() {
        makeDao.getMake(makeTestId);
        verify(makeDao, Mockito.times(1)).getMake(makeTestId);
    }

    @Test
    public void testGetAllMakes_shouldCallDaoMethod() {
        makeDao.getAllMakes();
        verify(makeDao,Mockito.times(1)).getAllMakes();
    }

    @Test
    public void testGetMakeByName_shouldCallDaoMethod() {
        makeDao.getMakeByName(testMakeName);
        verify(makeDao,Mockito.times(1)).getMakeByName(testMakeName);
    }
}
