package pro.sky22.Service;

import org.springframework.stereotype.Service;
import pro.sky22.Employee;
import pro.sky22.Exception.EmployeeNotFoundExceptiion;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final EmployeeService employeeService;


    @Override
    public Employee employeeWithMaxSalary(int departmentId) {
        return employeeService.getAll().stream().
                filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundExceptiion("Этот сотрудник не найден"));
    }

    @Override
    public Employee employeeWithMinSalary(int departmentId) {
        return employeeService.getAll().stream().
                filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundExceptiion("Этот сотрудник не найден"));
    }


    @Override
    public Map<Integer, List<Employee>> findAllEmployees() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
}