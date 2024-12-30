package org.example.bookapi;


import jakarta.transaction.Transactional;
import org.example.bookapi.dao.CustomerDao;
import org.example.bookapi.dao.RoleDao;
import org.example.bookapi.entity.Customer;
import org.example.bookapi.entity.Role;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BookApiApplication {
	private final CustomerDao customerDao;
	private final RoleDao roleDao;
	private final PasswordEncoder passwordEncoder;

	public BookApiApplication(CustomerDao customerDao, RoleDao roleDao,PasswordEncoder passwordEncoder) {
		this.customerDao = customerDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	@Transactional
	@Profile("dev")
	public ApplicationRunner init() {
		return args -> {
			Customer customer1 = new Customer("Pyae Phyo","pyaephyo", passwordEncoder.encode("12345"), "pyaephyo@gmail.com");
			Customer customer2 = new Customer("John Doe", "johndoe", passwordEncoder.encode("12345"), "johndoe@gmail.com");

			Role adminRole = new Role("ROLE_ADMIN");
			Role userRole = new Role("ROLE_USER");

			customer1.addRole(adminRole);
			customer2.addRole(userRole);

			customerDao.save(customer1);
			customerDao.save(customer2);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}

}
