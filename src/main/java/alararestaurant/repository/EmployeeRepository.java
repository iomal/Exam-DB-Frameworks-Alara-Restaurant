package alararestaurant.repository;

import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);

    @Query (value = "Select e from alararestaurant.domain.entities.Employee as e " +
            "join e.position as p where p.name ='Burger Flipper' ")
    List<Employee> employeesBurgerPos();
}
