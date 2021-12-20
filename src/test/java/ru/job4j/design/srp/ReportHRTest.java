package ru.job4j.design.srp;

import org.junit.Test;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.ReportHR;
import ru.job4j.design.srp.report.forms.ReportFormatted;
import ru.job4j.design.srp.report.forms.ReportFormattedToHRByDescendingOrderOfSalary;
import ru.job4j.design.srp.repository.MemStore;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportHRTest {

    @Test
    public void whenSortedBySalaryDescendingOrder() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        ReportFormatted formatted = new ReportFormattedToHRByDescendingOrderOfSalary();
        Employee workerIvan = new Employee("Ivan", now, now, 100);
        Employee workerRoman = new Employee("Roman", now, now, 111);
        store.add(workerIvan);
        store.add(workerRoman);
        Report reportHR = new ReportHR(store, formatted);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary in descending order;")
                .append(System.lineSeparator())
                .append(workerRoman.getName()).append(";")
                .append(workerRoman.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportHR.generate(em -> true), is(expect.toString()));
    }
}