package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.repository.Store;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final Store store;

    private final Double tax;

    public ReportAccounting(Store store, Double tax) {
        this.store = store;
        this.tax = tax;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
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
