package pro.sky22.Service;

import pro.sky22.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName);


    Employee remove(String firstName, String lastName, int department, int salary);

    Employee find(String firstName, String lastName);

    List<Employee> getAll();
}
