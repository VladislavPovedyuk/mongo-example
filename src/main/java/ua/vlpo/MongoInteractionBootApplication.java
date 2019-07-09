package ua.vlpo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.vlpo.entity.CustomerEntity;
import ua.vlpo.repository.CustomerRepository;
import ua.vlpo.util.DBPopulator;

@SpringBootApplication(scanBasePackages = "ua.vlpo")
public class MongoInteractionBootApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Autowired
    public MongoInteractionBootApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoInteractionBootApplication.class, args);
    }

    public void run(String... args) throws Exception {
        customerRepository.insert(DBPopulator.getOneJpa());
        customerRepository.insert(DBPopulator.getManyJpa());
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        System.out.println("=== Number of documents in collection ===");
        System.out.println(allCustomers.size());
        printCurrentCollectionState(allCustomers);

        System.out.println("=== First document ===");
        CustomerEntity first = customerRepository.findTopByOrderByIdAsc();
        System.out.println(first);

        System.out.println("=== Find all who have cars and age is over 27 ===");
        List<CustomerEntity> allByCarsExistsAndAgeGreaterThan = customerRepository.findAllByCarsExistsAndAgeGreaterThan(true,27);
        for (CustomerEntity customer : allByCarsExistsAndAgeGreaterThan) {
            System.out.println(customer);
        }

        System.out.println("=== Delete where age equals 29 ===");
        customerRepository.deleteByAgeEquals(29);
        allCustomers = customerRepository.findAll();
        printCurrentCollectionState(allCustomers);

        System.out.println("=== Give Oleksii a car ===");
        CustomerEntity oleksii = customerRepository.findTopByCarsExistsOrderByIdAsc(false);
        oleksii.setCars(Arrays.asList("YA3 Patriot"));
        customerRepository.save(oleksii);

        allCustomers = customerRepository.findAll();
        printCurrentCollectionState(allCustomers);

        System.out.println("=== Mass update ===");
        List<CustomerEntity> allByAgeLessThanEqual = customerRepository.findAllByAgeLessThanEqual(23);
        int count = 0;
        for (CustomerEntity customer : allByAgeLessThanEqual) {
            customer.setAge(customer.getAge() + 1);
            customerRepository.save(customer);
            count++;
        }
        System.out.println("=== Updated records count === ");
        System.out.println(count);
        allCustomers = customerRepository.findAll();
        printCurrentCollectionState(allCustomers);

        System.out.println("=== Find YA3 (as second car) owner ===");
        List<CustomerEntity> findAllByCarsContains = customerRepository.findAllByCarsContains("YA3 Patriot");
        List<CustomerEntity> ya3_patriot = findAllByCarsContains.stream().filter(e -> e.getCars().size() >= 2).filter(e -> e.getCars().get(1).equals("YA3 Patriot")).collect(Collectors.toList());
        printCurrentCollectionState(ya3_patriot);
        customerRepository.deleteAll();
    }

    private static void printCurrentCollectionState(List<CustomerEntity> customers) {
        System.out.println("=== Documents present ===");
        for (CustomerEntity customer : customers) {
            System.out.println(customer);
        }
    }
}