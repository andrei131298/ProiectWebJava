package ro.unibuc.tennistournaments.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.tennistournaments.domain.Category;
import ro.unibuc.tennistournaments.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto mapToDto(Category category);
    Category mapToEntity(CategoryDto categoryDto);
}
