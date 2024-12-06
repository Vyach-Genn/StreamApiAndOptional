package streamapiandoptional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import streamapiandoptional.employee.Employee;
import streamapiandoptional.servise.ServiseEmployee;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class ControllerEmployee {
    private final ServiseEmployee serviseEmployee;

    public ControllerEmployee(ServiseEmployee serviseEmployee) {
        this.serviseEmployee = serviseEmployee;
    }


    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeeByDepartmen(@RequestParam(
            value = "departmentId", required = false) Integer departmentId) {
        if (departmentId == null) {
           return serviseEmployee.getEmployeesToTheDepartament();
        }
        List<Employee> employees = serviseEmployee.getEmployeesDepartament(departmentId);
        if (employees.isEmpty()) {
            throw new RuntimeException( "Сотрудники не найдены для отдела с ID: " + departmentId);
        }
        return  Map.of(departmentId, employees);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getMinSalariDepartmen(@RequestParam("departmentId") int departmentId) {
        return serviseEmployee.getEmployeeMinSalariDepartament(departmentId);
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getMaxSalariDepartmen(@RequestParam("departmentId") int departmentId) {
        return serviseEmployee.getEmployeeMaxSalariDepartament(departmentId);
    }
}

