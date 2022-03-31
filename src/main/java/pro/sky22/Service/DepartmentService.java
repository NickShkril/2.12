package pro.sky22.Service;

import pro.sky22.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface DepartmentService {
    Employee employeeWithMaxSalary(int departmentId);

    Employee employeeWithMinSalary(int departmentId);

    Map<Integer, List<Employee>> findAllEmployees();

    Collection<Employee> findEmployeesByDepartment(Integer departmentId);
}
