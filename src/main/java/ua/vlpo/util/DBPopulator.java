package ua.vlpo.util;

import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import ua.vlpo.entity.CustomerEntity;
import ua.vlpo.model.Customer;

public abstract class DBPopulator {
    public static Document getOne() {
        return new Document()
                .append("name", "Vlad")
                .append("age", 29)
                .append("cars", Arrays.asList("Nissan Note", "Hyundai Santa Fe"));
    }

    public static List<Document> getMany() {
        Document first = new Document()
                .append("name", "Oleksandr")
                .append("age", 27)
                .append("cars", Arrays.asList("Mitsubishi Outlander", "Lamborghini Aventador"));

        Document second = new Document()
                .append("name", "Oleksii")
                .append("age", 23);

        Document third = new Document()
                .append("name", "Alexey")
                .append("age", 33)
                .append("cars", Arrays.asList("Mitsubishi Outlander XL", "YA3 Patriot"));

        return Arrays.asList(first, second, third);
    }

    public static Customer getOneSerialized() {
        return new Customer("Vlad", 29, Arrays.asList("Nissan Note", "Hyundai Santa Fe"));
    }

    public static List<Customer> getManySerialized() {
        Customer first = new Customer("Oleksandr", 27,  Arrays.asList("Mitsubishi Outlander", "Lamborghini Aventador"));
        Customer second = new Customer("Oleksii", 23);
        Customer third = new Customer("Alexey", 33, Arrays.asList("Mitsubishi Outlander XL", "YA3 Patriot"));

        return Arrays.asList(first, second, third);
    }

    public static CustomerEntity getOneJpa() {
        return new CustomerEntity("Vlad", 29, Arrays.asList("Nissan Note", "Hyundai Santa Fe"));
    }

    public static List<CustomerEntity> getManyJpa() {
        CustomerEntity first = new CustomerEntity("Oleksandr", 27,  Arrays.asList("Mitsubishi Outlander", "Lamborghini Aventador"));
        CustomerEntity second = new CustomerEntity("Oleksii", 23);
        CustomerEntity third = new CustomerEntity("Alexey", 33, Arrays.asList("Mitsubishi Outlander XL", "YA3 Patriot"));

        return Arrays.asList(first, second, third);
    }
}