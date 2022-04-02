package pro.sky22;


import org.junit.jupiter.api.Test;
import pro.sky22.Exception.EmployeeExistsException;
import pro.sky22.Exception.EmployeeNotFoundExceptiion;
import pro.sky22.Service.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void MustReturnEmployeeWhoWasAdded() {
        Employee employee = new Employee("Ivan", "Ivanov", 1, 1111);
        assertEquals(employee, out.add("Ivan", "Ivanov", 1, 1111));
    }

    @Test
    public void MustReturnEmployeeWhoWasAddedAndThrow() {
        out.add("Ivan", "Ivanov", 1, 1111);
        assertThrows(EmployeeExistsException.class, () -> out.add("Ivan", "Ivanov", 1, 1111));
    }


    @Test
    public void MustReturnEmployeeWhoWasFound() {
        Employee employee = new Employee("Ivan", "Ivanov", 1, 1111);
        out.add("Ivan", "Ivanov", 1, 1111);
        assertEquals(employee, out.find("Ivan", "Ivanov"));
    }
    @Test
    public void MustReturnEmployeeWhoWasDeleted() {
        Employee employee = new Employee("Ivan", "Ivanov", 1, 1111);
        out.add("Ivan", "Ivanov", 1, 1111);
        assertEquals(employee, out.remove("Ivan", "Ivanov", 1, 1111));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenDelete() {
        Employee employee = out.add("Иван", "Иванов", 1, 11111);
        assertThrows(EmployeeNotFoundExceptiion.class, () -> out.remove("Сергей", "Алексеев",1,2122));
    }


}
