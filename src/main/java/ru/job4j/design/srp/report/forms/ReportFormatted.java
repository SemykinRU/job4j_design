package ru.job4j.design.srp.report.forms;

import ru.job4j.design.srp.model.Employee;

import java.util.List;

public interface ReportFormatted {
    String getFormattedReport(List<Employee> employees);
}
