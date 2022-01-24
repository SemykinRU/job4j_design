package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.repository.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
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