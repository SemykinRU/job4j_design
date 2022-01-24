package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.repository.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
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
