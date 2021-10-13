package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company")
public class Company {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int code;

    public Company() {

    }

    public Company(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Company{"
                + "name='" + name + '\''
                + ", code=" + code
                + '}';
    }
}
