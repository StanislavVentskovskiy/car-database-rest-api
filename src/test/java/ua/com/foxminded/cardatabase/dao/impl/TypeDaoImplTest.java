package ua.com.foxminded.cardatabase.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.cardatabase.model.Type;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql"})
@SpringBootTest
public class TypeDaoImplTest {

    @Mock
    TypeDaoImpl typeDaoImpl;

    Optional<Type> expectedOptionalType;
    Optional<Type> actualOptionalType;
    private Type expectedType;
    private Type actualType;
    private List<Type> expectedTypeList;
    private List<Type> actualTypeList;
    private int testTypeId = 1;

    @Test
    public void testDeleteType_shouldReturnTrue() {
        typeDaoImpl.deleteType(testTypeId);
        verify(typeDaoImpl,Mockito.times(1)).deleteType(Mockito.anyInt());
    }

    @Test
    public void testAddType_shouldReturnTrue() {
        Mockito.when(typeDaoImpl.addType(expectedType)).thenReturn(expectedType);
        actualType = typeDaoImpl.addType(expectedType);
        assertEquals(expectedType,actualType);
    }

    @Test
    public void testUpdateType_shouldReturnTrue() {
        Mockito.when(typeDaoImpl.updateType(expectedType)).thenReturn(expectedType);
        actualType = typeDaoImpl.updateType(expectedType);
        assertEquals(expectedType,actualType);
    }

    @Test
    public void testGetType_shouldReturnOptionalType() {
        Mockito.when(typeDaoImpl.getType(testTypeId)).thenReturn(expectedOptionalType);
        actualOptionalType = typeDaoImpl.getType(testTypeId);
        assertEquals(expectedOptionalType,actualOptionalType);
    }

    @Test
    public void testGetAllTypes_shouldReturnTypes() {
        Mockito.when(typeDaoImpl.getAllTypes()).thenReturn(expectedTypeList);
        actualTypeList = typeDaoImpl.getAllTypes();
        assertEquals(expectedTypeList,actualTypeList);
    }
}
