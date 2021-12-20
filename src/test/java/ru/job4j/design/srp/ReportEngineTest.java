package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.ReportEngine;
import ru.job4j.design.srp.report.forms.ReportFormattedToEngineByHTML;
import ru.job4j.design.srp.report.forms.ReportFormatted;
import ru.job4j.design.srp.report.forms.ReportFormattedToEngineByStandard;
import ru.job4j.design.srp.repository.MemStore;

import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldStandardGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        ReportFormatted formatted = new ReportFormattedToEngineByStandard();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, formatted);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        ReportFormatted formatted = new ReportFormattedToEngineByHTML();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, formatted);
        StringBuilder expect = new StringBuilder()
                .append("<h1>Name; Hired; Fired; Salary;</h1>")
                .append(System.lineSeparator())
                .append("<p>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</p>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}