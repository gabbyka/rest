package guru.springframework.rest.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.rest.domain.Category;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.repositories.CategoryRepository;
import guru.springframework.rest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {
    
    Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        createCategories();
        createCustomers();
        
    }

    private void createCustomers() {
        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Speedy");
        
        Customer jack = new Customer();
        jack.setFirstName("Jack");
        jack.setLastName("Whiskey");
        
        customerRepository.save(joe);
        customerRepository.save(jack);
        
        logger.info("Customer Data Loaded = {}", customerRepository.count());
    }

    private void createCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.saveAndFlush(nuts);

        logger.info("Category Data Loaded = {}", categoryRepository.count());
    }

}
