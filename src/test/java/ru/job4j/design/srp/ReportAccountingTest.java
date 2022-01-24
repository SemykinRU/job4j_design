package ru.job4j.design.srp;

import org.junit.Test;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.ReportAccounting;
import ru.job4j.design.srp.repository.MemStore;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportAccountingTest {

    @Test
    public void whenSalary100AndTax13thenSalary87() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Double tax = 13.0d;
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report reportAccounting = new ReportAccounting(store, tax);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary after taxes;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(87).append(";")
                .append(System.lineSeparator());
        assertThat(reportAccounting.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenSalary10AndTax13thenSalary8dot7() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Double tax = 13.0d;
        Employee worker = new Employee("Ivan", now, now, 10);
        store.add(worker);
        Report reportAccounting = new ReportAccounting(store, tax);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary after taxes;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(8.7).append(";")
                .append(System.lineSeparator());
        assertThat(reportAccounting.generate(em -> true), is(expect.toString()));
    }
}