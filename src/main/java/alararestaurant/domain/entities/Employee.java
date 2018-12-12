package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    private String name;
    private Integer age;
    private Position position;
    private Set<Order> orders;


    public Employee() {
    }

    @Size(min = 3, max = 30)
    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public Integer getAge() {
        return this.age;
    }

    @NotNull
    @ManyToOne(targetEntity = Position.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Position getPosition() {
        return this.position;
    }

    @OneToMany(mappedBy = "employee", targetEntity = Order.class, fetch = FetchType.LAZY)
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder ordersStringBuilder = new StringBuilder();
        if (orders.size() != 0) {
            orders.forEach(o -> {
                ordersStringBuilder.append("\nCustomer: " + o.getCustomer());
                o.getOrderItems().forEach(oI -> ordersStringBuilder.append(oI.toString()));
            });
        }
        return "\n" + "Name: " + name + '\n' +
                "Orders:" + ordersStringBuilder.toString();
    }
}
