package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity (name = "item")
@Table (name="items")
public class Item extends BaseEntity {
    private String name;
    private Category category;
    private BigDecimal price;
    private Set<OrderItem> orderItems;

    public Item() {
    }

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @ManyToOne(targetEntity = Category.class, cascade = {CascadeType.MERGE,CascadeType.MERGE})
    public Category getCategory() {
        return this.category;
    }

    @DecimalMin("0.01")
    @Column (nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    @OneToMany (mappedBy = "item",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
              return "\n"+"    Name: " + name + '\n' +
                     "    Price: " + price;
    }
}
