package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderType;
import alararestaurant.mappings.IHaveCustomMappings;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportRootDto {
    @XmlElement(name = "order")
    private List<OrderImportDto> orderImportDtos;

    public OrderImportRootDto() {
    }

    public List<OrderImportDto> getOrderImportDtos() {
        return this.orderImportDtos;
    }

    public void setOrderImportDtos(List<OrderImportDto> orderImportDtos) {
        this.orderImportDtos = orderImportDtos;
    }

}
