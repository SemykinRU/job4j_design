package ru.job4j.design.srp.repository;

import ru.job4j.design.srp.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}