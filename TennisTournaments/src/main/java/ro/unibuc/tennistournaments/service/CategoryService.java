package ro.unibuc.tennistournaments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.tennistournaments.domain.Category;
import ro.unibuc.tennistournaments.domain.Player;
import ro.unibuc.tennistournaments.dto.CategoryDto;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.mapper.CategoryMapper;
import ro.unibuc.tennistournaments.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.mapToDto(savedCategory);
    }

    public Category getOne(Long id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }
}
