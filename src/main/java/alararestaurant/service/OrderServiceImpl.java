package alararestaurant.service;

import alararestaurant.config.constants.Constants;
import alararestaurant.domain.dtos.OrderImportDto;
import alararestaurant.domain.dtos.OrderImportRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.parsers.JaxbParser;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validator;
    private final ModelMapper mapper;
    private final JaxbParser jaxbParser;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, FileUtil fileUtil,
                            ValidationUtil validator, ModelMapper mapper, JaxbParser jaxbParser,
                            EmployeeRepository employeeRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.fileUtil = fileUtil;
        this.validator = validator;
        this.mapper = mapper;

        this.jaxbParser = jaxbParser;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {

        return fileUtil.readFile(Constants.ORDERS_XML_PATH_RELATIVE);
    }

    @Override
    public String importOrders() throws JAXBException {
        OrderImportRootDto orderImportDtos = jaxbParser.toString(Constants.ORDERS_XML_PATH, OrderImportRootDto.class);
        StringBuilder importResult = new StringBuilder();
        for (OrderImportDto orderImportDto : orderImportDtos.getOrderImportDtos()) {

            if (!validator.isValid(orderImportDto)) {
                importResult.append(Constants.INVALID_DATA_IMPORT)
                        .append(System.lineSeparator());
                continue;
            }
            Employee employee = this.employeeRepository.findByName(orderImportDto.getEmployeeName()).orElse(null);
            if (employee == null) {
                continue;
            }
            Order order = mapper.map(orderImportDto, Order.class);
            orderImportDto.getOrderItemRootImportDto().getItemImportDtos().forEach(itemName ->
            {
                Item item = this.itemRepository.findByName(itemName.getName()).orElse(null);
                if (item != null) {
                    order.addOrderItem(new OrderItem(item, itemName.getQuantity()));
                    order.setEmployee(employee);
                    this.orderRepository.save(order);
                }
            });

            importResult.append(String.format(Constants.SUCCESSFUL_ORDER_IMPORT, orderImportDto.getCustomer(),
                    orderImportDto.getDateTime()))
                    .append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder result = new StringBuilder();
        this.employeeRepository.employeesBurgerPos().stream().forEach(e -> result.append(e)
                .append("\n"));
        return result.toString();
    }
}
