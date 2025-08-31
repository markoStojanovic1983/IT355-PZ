package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355pz.model.Category;
import rs.ac.metropolitan.it355pz.model.Expense;

import java.util.List;

@Service
public class ExpenseService {
    
    private final DataStorageService dataStorageService;
    private final CategoryService categoryService;

    public ExpenseService(DataStorageService dataStorageService, CategoryService categoryService) {
        this.dataStorageService = dataStorageService;
        this.categoryService = categoryService;
    }

    /**
     * Fetches all expenses from the data storage.
     * @return list of all expenses
     */
    public List<Expense> findAllExpenses() {
        return dataStorageService.getAllExpenses();
    }

    /**
     * Find an expense by its ID.
     * @param id the ID of the expense
     * @return the expense if found, otherwise null
     */
    public Expense findExpenseById(Long id) {
        return dataStorageService.getExpenseById(id);
    }

    /**
     * Saves or updates an expense.
     * @param expense the expense to save or update
     * @return the saved or updated expense
     */
    public Expense saveExpense(Expense expense) {
        return dataStorageService.saveExpense(expense);
    }

    /**
     * Saves or updates an expense with an associated category.
     * @param expense the expense to save or update
     * @param categoryId the ID of the category to associate with the expense
     * @return the saved or updated expense
     */
    public Expense saveExpense(Expense expense, Long categoryId) {
        if (categoryId != null) {
            Category category = categoryService.findCategoryById(categoryId);
            expense.setCategory(category);
        }
        return saveExpense(expense);
    }

    /**
     * Deletes an expense by its ID.
     * @param id the ID of the expense to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteExpenseById(Long id) {
        return dataStorageService.deleteExpenseById(id);
    }

    /**
     * Gets the total amount of all expenses.
     * @return the total amount of all expenses
     */
    public double getTotalExpenses() {
        return dataStorageService.getAllExpenses().stream()
            .mapToDouble(Expense::getAmount)
            .sum();
    }

    /**
     * Gets the total count of expenses.
     * @return the number of expenses
     */
    public int getExpenseCount() {
        return dataStorageService.getAllExpenses().size();
    }
}