package alararestaurant.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private String customer;
    private LocalDateTime dateTime;
    private OrderType orderType = OrderType.ForHere;
    private Employee employee;
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    public Order() {
    }

    @Column(columnDefinition = "TEXT")
    @NotNull
    public String getCustomer() {
        return this.customer;
    }

    @Column(nullable = false)
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }



    @Enumerated(EnumType.STRING)
    @Column (name="order_type",columnDefinition = "ENUM('ForHere','ToGo')default 'ForHere'" )
    public OrderType getOrderType() {
        return this.orderType;
    }

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return this.employee;
    }

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public String toString() {
        StringBuilder orderItemsStringBuilder = new StringBuilder();
        if (orderItems.size()!=0)
        {orderItems.stream().forEach(o-> orderItemsStringBuilder.append(o.getOrder().getOrderItems().toString())); }
          return "\n"+"  Customer: " + customer + '\n' +
                 "  Items: " + orderItemsStringBuilder.toString();
    }
}
