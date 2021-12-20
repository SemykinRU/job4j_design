package ru.job4j.design.srp.report.forms;

import ru.job4j.design.srp.model.Employee;

import java.util.List;

public class ReportFormattedToEngineByStandard implements ReportFormatted {

    @Override
    public String getFormattedReport(List<Employee> employees) {
        StringBuilder result = new StringBuilder("Name; Hired; Fired; Salary;");
        for (var employee : employees) {
            result.append(String.format("%n%s;%s;%s;%s;%n",
                    employee.getName(),
                    employee.getHired(),
                    employee.getFired(),
                    employee.getSalary()));
        }
        return result.toString();
    }
}
