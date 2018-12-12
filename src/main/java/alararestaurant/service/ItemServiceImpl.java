package alararestaurant.service;

import alararestaurant.config.constants.Constants;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.parsers.GsonParser;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validator;
    private final ModelMapper mapper;
    private final GsonParser gson;
    private final CategoryRepository categoryRepository;

    public ItemServiceImpl(ItemRepository itemRepository, FileUtil fileUtil,
                           ValidationUtil validator, ModelMapper mapper, GsonParser gson, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.fileUtil = fileUtil;
        this.validator = validator;
        this.mapper = mapper;
        this.gson = gson;

        this.categoryRepository = categoryRepository;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return fileUtil.readFile(Constants.ITEMS_JSON_PATH);
    }

    @Override
    public String importItems(String items) {
        ItemImportDto[] itemImportDtos = gson.fromString(items, ItemImportDto[].class);
        StringBuilder importResult = new StringBuilder();
        for (ItemImportDto itemImportDto : itemImportDtos) {
            if (!validator.isValid(itemImportDto)) {
                importResult.append(Constants.INVALID_DATA_IMPORT)
                        .append(System.lineSeparator());
                continue;
            }
            Item item = this.itemRepository.findByName(itemImportDto.getName()).orElse(null);
            if (item != null) {
                importResult.append(Constants.INVALID_DATA_IMPORT)
                        .append(System.lineSeparator());
                continue;
            }
            item = mapper.map(itemImportDto, Item.class);
            Category category = this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);
            if (category == null) {
                category = new Category(itemImportDto.getCategory());
                if (!validator.isValid(category)) {
                    importResult.append(Constants.INVALID_DATA_IMPORT)
                            .append(System.lineSeparator());
                    continue;
                }
                this.categoryRepository.save(category);
            }
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);
            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT, item.getName()))
                    .append(System.lineSeparator());

        }
        return importResult.toString().trim();
    }
}
