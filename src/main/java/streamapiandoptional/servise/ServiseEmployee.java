package streamapiandoptional.servise;

import streamapiandoptional.employee.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiseEmployee {
    Map<Integer, List<Employee>> getEmployeesToTheDepartament();

    List<Employee> getEmployeesDepartament(int number);

    Optional<Employee> getEmployeeMinSalariDepartament(int number);

    Optional<Employee> getEmployeeMaxSalariDepartament(int number);

    String getEmployeAverageSalariDepartments(int number);

    String getEmployeesAverageSalari();

    List<Employee> indexingSalari(int percent);
}
