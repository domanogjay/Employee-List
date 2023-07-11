package Domanog.code.model.repositoryUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Domanog.code.model.Employee;

@Repository
public interface employeeRepo extends JpaRepository<Employee, Long> {
	Employee findByEmail(String email);
}
