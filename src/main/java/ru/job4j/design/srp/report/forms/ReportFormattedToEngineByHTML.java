package ru.job4j.design.srp.report.forms;

import ru.job4j.design.srp.model.Employee;

import java.util.List;

public class ReportFormattedToEngineByHTML implements ReportFormatted {

    @Override
    public String getFormattedReport(List<Employee> employees) {
        StringBuilder result = new StringBuilder(("<h1>Name; Hired; Fired; Salary;</h1>"));
        for (var employee : employees) {
            result.append(String.format("%n<p>%s;%s;%s;%s;</p>%n",
                    employee.getName(),
                    employee.getHired(),
                    employee.getFired(),
                    employee.getSalary()));
        }
        return result.toString();
    }
}
