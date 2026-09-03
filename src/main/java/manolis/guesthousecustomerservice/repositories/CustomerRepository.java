package manolis.guesthousecustomerservice.repositories;

import com.example.guesthousebookingsystem.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
