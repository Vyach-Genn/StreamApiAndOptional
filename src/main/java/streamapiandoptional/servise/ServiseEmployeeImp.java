package streamapiandoptional.servise;

import org.springframework.stereotype.Service;
import streamapiandoptional.employee.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class ServiseEmployeeImp implements ServiseEmployee {


    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 100000, 3),
            new Employee("Irina", "Sidorovav", 95000, 2),
            new Employee("Fedor", "Petrov", 101000, 1),
            new Employee("Stepan", "egorov", 99000, 3),
            new Employee("Sveta", "Fedotova", 103000, 2),
            new Employee("Andrey", "Svetlov", 110000, 1)
    ));

    @Override
    public Map<Integer, List<Employee>> getEmployeesToTheDepartament() {
         return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));

    }

    @Override
    public List<Employee> getEmployeesDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Employee> getEmployeeMinSalariDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .min(Comparator.comparing(Employee::getSalary));

    }

    @Override
    public Optional<Employee> getEmployeeMaxSalariDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .max(Comparator.comparing(Employee::getSalary));

    }

    @Override
    public String getEmployeAverageSalariDepartments(int number) {
        Double averageSalari = employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        return ("Average salary for department " + number + ": " + averageSalari);


    }

    @Override
    public String getEmployeesAverageSalari() {
        Double averageSalari = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        return ("Average salary for all employees: " + averageSalari);

    }

    @Override
    public List<Employee> indexingSalari(int percent) {
        Double percentValue = percent / 100.0;
        List<Employee> indexedEmployees = employees.stream()
                .map(employee -> {
                    Double newSalari = employee.getSalary() + (employee.getSalary() * percentValue);
                    employee.setSalary(newSalari);
                    return employee;
                })
                .collect(Collectors.toList());
        System.out.println("salaries have been indexed by : " + percent + " percent");
        indexedEmployees.forEach(System.out::println);

        return indexedEmployees;
    }

}
