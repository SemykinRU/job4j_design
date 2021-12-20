package ru.job4j.design.srp.report.forms;

import ru.job4j.design.srp.model.Employee;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class ReportFormattedToAccountingBySalaryFormAfterTax implements ReportFormatted {
    private final Double tax;

    public ReportFormattedToAccountingBySalaryFormAfterTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public String getFormattedReport(List<Employee> employees) {
        StringBuilder result = new StringBuilder("Name; Hired; Fired; Salary after taxes;");
        for (var employee : employees) {
            result.append(String.format("%n%s;%s;%s;%s;%n",
                    employee.getName(),
                    employee.getHired(),
                    employee.getFired(),
                    calculateSalaryAfterTax(employee.getSalary())));
        }
        return result.toString();
    }

    private String calculateSalaryAfterTax(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        return decimalFormat.format(value - ((value / 100) * this.tax));
    }
}
