package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.domain.entities.OrderType;
import alararestaurant.mappings.IHaveCustomMappings;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto implements IHaveCustomMappings {
    @XmlElement
    private String customer;

    @XmlElement (name="employee")
    private String employeeName;
    @XmlElement (name="type")
    private String orderType;
    @XmlElement (name="date-time")
    private String dateTime;
    @XmlElement (name="items")
    private OrderItemRootImportDto orderItemRootImportDto;

    public OrderImportDto() {
    }
    @NotNull
    public String getCustomer() {
        return this.customer;
    }
    @NotNull
    @Size(min = 3, max = 30)
    public String getEmployeeName() {
        return this.employeeName;
    }
    @NotNull
    public String getOrderType() {
        return this.orderType;
    }
    @NotNull
    public String getDateTime() {
        return this.dateTime;
    }

    public OrderItemRootImportDto getOrderItemRootImportDto() {
        return this.orderItemRootImportDto;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setOrderItemRootImportDto(OrderItemRootImportDto orderItemRootImportDto) {
        this.orderItemRootImportDto = orderItemRootImportDto;
    }


    @Override
    public void configureMappings(ModelMapper mapper) {
        Converter<String,LocalDateTime> dateTimeConverter = date->LocalDateTime.parse(date.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        Converter<String,OrderType> orderTypeConverter = type->OrderType.valueOf(OrderType.class,type.getSource());

        mapper.createTypeMap(OrderImportDto.class,Order.class)
                .addMappings(m->m.skip(Order::setEmployee))
                .addMappings(m->m.skip(Order::setOrderItems))
                .addMappings(m->m.using(dateTimeConverter).map(OrderImportDto::getDateTime,Order::setDateTime))
                .addMappings(m->m.using(orderTypeConverter).map(OrderImportDto::getOrderType,Order::setOrderType))
                .addMappings(m->m.skip(Order::setId));;


    }
}
