package pro.sky22.Service;

import org.springframework.stereotype.Service;
import pro.sky22.Employee;
import pro.sky22.Exception.EmployeeExistsException;
import pro.sky22.Exception.EmployeeNotFoundExceptiion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeList;

    public EmployeeServiceImpl() {
        employeeList = new HashMap<>();
        employeeList.put("one", new Employee("Petya", "Petya", 1, 222));
        employeeList.put("two", new Employee("Vasya", "Vasya", 2, 222));
        employeeList.put("three", new Employee("Sasha", "Sasha", 3, 333));
        employeeList.put("four", new Employee("Sasha", "Sasha", 4, 3333));
    }


    public Employee add(String firstName, String lastName, int department, int salary) {
        validateNames(firstName, lastName);
        Employee newEmployee = new Employee(
                capitalize(firstName),
                capitalize(lastName),
                department,
                salary
        );
        if (employeeList.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeExistsException("Сотрудник уже есть в списке");
        }

        employeeList.put(getKey(firstName, lastName), newEmployee);
        return newEmployee;
    }

    private void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new EmployeeExistsException("Неправильное имя или фамилия");
            }
        }
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        return null;
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, int salary) {
        String key = getKey(firstName, lastName);
        if (employeeList.remove(key) == null) {
            throw new EmployeeNotFoundExceptiion("Сотрудника нет в списке");
        }
        Employee removedEmployee = new Employee(firstName, lastName, department, salary);
        return removedEmployee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employeeList.get(key);
        if (employee == null) {
            throw new RuntimeException("Сотрудник не найден");
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employeeList.values());
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
