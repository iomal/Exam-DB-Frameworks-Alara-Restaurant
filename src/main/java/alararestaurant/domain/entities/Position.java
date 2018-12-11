package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity (name="position")
@Table (name="positions")
public class Position extends BaseEntity {
    private String name;
    private Set<Employee> employees;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @OneToMany(mappedBy = "position")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name ;
    }
}
