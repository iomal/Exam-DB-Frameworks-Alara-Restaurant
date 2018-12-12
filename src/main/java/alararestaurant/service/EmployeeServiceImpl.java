package alararestaurant.service;

import alararestaurant.config.constants.Constants;
import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.parsers.GsonParser;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validator;
    private final ModelMapper mapper;
    private final GsonParser gson;
    private final PositionRepository positionRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               FileUtil fileUtil, ValidationUtil validator,
                               ModelMapper mapper, GsonParser gson, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.validator = validator;
        this.mapper = mapper;
        this.gson = gson;
        this.positionRepository = positionRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {

        return fileUtil.readFile(Constants.EMPLOYEES_JSON_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        EmployeeImportDto[] importDtos = gson.fromString(employees, EmployeeImportDto[].class);
        StringBuilder importResult = new StringBuilder();
        for (EmployeeImportDto importDto : importDtos) {
            if (!validator.isValid(importDto)) {
                importResult.append(Constants.INVALID_DATA_IMPORT)
                        .append(System.lineSeparator());
                continue;
            }
            Position position = this.positionRepository.findByName(importDto.getPosition()).orElse(null);
            if (position == null) {
                position = new Position(importDto.getPosition());
                if (!validator.isValid(position)) {
                    importResult.append(Constants.INVALID_DATA_IMPORT)
                            .append(System.lineSeparator());
                    continue;
                }

                position = new Position(importDto.getPosition());

            }
            Employee employee = mapper.map(importDto, Employee.class);
            employee.setPosition(position);
            this.employeeRepository.save(employee);
            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT, importDto.getName()))
                    .append(System.lineSeparator());
        }
        return importResult.toString();
    }
}
