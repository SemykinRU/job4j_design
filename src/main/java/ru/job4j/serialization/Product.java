package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)

public class Product {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int count;

    @XmlAttribute
    private boolean isAction;
    private Company company;

    @XmlElementWrapper(name = "movementHistorys")
    @XmlElement(name = "movementHistory")
    private String[] movementHistory;

    public Product() {

    }

    public Product(String name, int count, boolean isAction, Company company, String[] movementHistory) {
        this.name = name;
        this.count = count;
        this.isAction = isAction;
        this.company = company;
        this.movementHistory = movementHistory;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isAction() {
        return isAction;
    }

    public Company getCompany() {
        return company;
    }

    public String[] getMovementHistory() {
        return movementHistory;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", count=" + count
                + ", isAction=" + isAction
                + ", company=" + company
                + ", movementHistory=" + Arrays.toString(movementHistory)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Product product = new Product("Apple", 4, false, new Company("sir Djo", 992399), new String[]{"A1", "B2"});
        JAXBContext context = JAXBContext.newInstance(Product.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(product, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Product result = (Product) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
