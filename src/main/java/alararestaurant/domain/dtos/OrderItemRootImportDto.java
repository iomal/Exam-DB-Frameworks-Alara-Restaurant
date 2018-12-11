package alararestaurant.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemRootImportDto {
    @XmlElement (name="item")
    List<OrderItemImportDto> orderItemImportDtos;

    public OrderItemRootImportDto() {
    }

    public List<OrderItemImportDto> getItemImportDtos() {
        return this.orderItemImportDtos;
    }

    public void setItemImportDtos(List<OrderItemImportDto> orderItemImportDtos) {
        this.orderItemImportDtos = orderItemImportDtos;
    }
}
