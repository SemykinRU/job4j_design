package ru.job4j.design.srp.report.forms;

import ru.job4j.design.srp.model.Employee;

import java.util.Comparator;
import java.util.List;

public class ReportFormattedToHRByDescendingOrderOfSalary implements ReportFormatted {
    @Override
    public String getFormattedReport(List<Employee> employees) {
        StringBuilder result = new StringBuilder("Name; Salary in descending order;");
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (var employee : employees) {
            result.append(String.format("%n%s;%s;%n",
                    employee.getName(),
                    employee.getSalary()));
        }
        return result.toString();
    }
}
