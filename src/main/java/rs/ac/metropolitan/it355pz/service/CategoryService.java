package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355pz.model.Category;

import java.util.List;

@Service
public class CategoryService {


    private final DataStorageService dataStorageService;

    public CategoryService(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    /**
     * Fetch all categories from the data storage.
     * @return List of all categories.
     */
    public List<Category> findAllCategories() {
        return dataStorageService.getAllCategories();
    }

    /**
     * Find a category by its ID.
     * @param id The ID of the category.
     * @return The category if found, otherwise null.
     */
    public Category findCategoryById(Long id) {
        return dataStorageService.getCategoryById(id);
    }

    /**
     * Save or update a category.
     * @param category The category to save or update.
     * @return The saved or updated category.
     */
    public Category saveCategory(Category category) {
        return dataStorageService.saveCategory(category);
    }

    /**
     * Delete a category by its ID, along with associated expenses.
     * @param id The ID of the category to delete.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean deleteCategoryById(Long id) {
        // Remove associated expenses first
        dataStorageService.deleteExpensesByCategory(id);
        return dataStorageService.deleteCategoryById(id);
    }

    /**
     * Get the total count of categories.
     * @return The number of categories.
     */
    public int getCategoryCount() {
        return dataStorageService.getAllCategories().size();
    }
}