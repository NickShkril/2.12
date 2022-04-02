package pro.sky22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky22.Exception.EmployeeNotFoundExceptiion;
import pro.sky22.Service.DepartmentServiceImpl;
import pro.sky22.Service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final EmployeeServiceImpl employeeServiceMock = mock(EmployeeServiceImpl.class);

    private final DepartmentServiceImpl out = new DepartmentServiceImpl(employeeServiceMock);

    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    public void addEmployee() {
        Employee employee1 = new Employee("One", "One", 1, 555);
        Employee employee2 = new Employee("Two", "Two", 1, 1111);
        Employee employee3 = new Employee("Three", "Three", 2, 2222);
        Employee employee4 = new Employee("Four", "Four", 2, 4444);
        Employee employee5 = new Employee("Five", "Five", 3, 3333);
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        employeeList.add(employee5);
    }

    @Test
    public void shouldReturnEmployeeWithMaxSalaryWhenCalled() {
        when(employeeServiceMock.getAll()).thenReturn((employeeList));
        assertEquals(out.employeeWithMaxSalary(2), employeeList.get(2));
    }

    @Test
    public void shouldReturnEmployeeWithMinSalary() {
        when(employeeServiceMock.getAll()).thenReturn(employeeList);
        assertEquals(out.employeeWithMinSalary(3), employeeList.get(4));
    }

    @Test
    public void shouldGenerateAnExceptionIfThereIsNoSuchDepartment() {
        when(employeeServiceMock.getAll()).thenReturn(employeeList);
        assertThrows(EmployeeNotFoundExceptiion.class, () -> out.employeeWithMaxSalary(5));
    }

    @Test
    public void shouldReturnListOfDepartmentEmployees() {
        when(employeeServiceMock.getAll()).thenReturn(employeeList);

        List<Employee> employees = List.of(new Employee("One", "One", 1, 555),
                new Employee("Two", "Two", 1, 1111));

        assertEquals(employees, out.findEmployeesByDepartment(1));
    }

    @Test
    public void shouldReturnListOfEmployeesSortedByDepartment() {
        when(employeeServiceMock.getAll()).thenReturn(employeeList);

        List<Employee> employees = new ArrayList<>();
        employees.addAll(employeeList);

        employees.sort(Comparator.comparing(Employee::getDepartment));

        assertEquals(employees, out.findAllEmployees());
    }


}
