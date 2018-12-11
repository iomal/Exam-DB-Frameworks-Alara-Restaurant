package alararestaurant.domain.entities;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "order_item")
@Table(name = "order_items")
public class OrderItem {
    private Integer id;
    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Item item,Integer quantity) {
        this.item = item;
        this.quantity=quantity;
    }

    public OrderItem(Item item) {
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    @ManyToOne(targetEntity = Order.class)
    public Order getOrder() {
        return this.order;
    }

    @ManyToOne(targetEntity = Item.class)
    public Item getItem() {
        return this.item;
    }

    @Min(1)
    @Column(nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return item +"\n"+
                "    Quantity: " + quantity+"\n---------------------------------";
    }
}
