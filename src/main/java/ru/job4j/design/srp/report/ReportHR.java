package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.forms.ReportFormatted;
import ru.job4j.design.srp.repository.Store;

import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;
    private final ReportFormatted formatted;

    public ReportHR(Store store, ReportFormatted formatted) {
        this.store = store;
        this.formatted = formatted;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return formatted.getFormattedReport(store.findBy(filter));
    }
}
