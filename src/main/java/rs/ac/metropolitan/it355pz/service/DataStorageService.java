package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import rs.ac.metropolitan.it355pz.model.Category;
import rs.ac.metropolitan.it355pz.model.Expense;
import rs.ac.metropolitan.it355pz.model.Income;
import rs.ac.metropolitan.it355pz.model.UserProfile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Application-scoped storage service that manages in-memory data persistence.
 * This service acts as the data layer and maintains application state across requests.
 */
@Service
@ApplicationScope
public class DataStorageService {
    
    // Data collections
    private final List<Category> categories = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final List<Income> incomes = new ArrayList<>();
    private UserProfile userProfile;
    
    // ID generators for each entity type
    private final AtomicLong categoryIdGenerator = new AtomicLong();
    private final AtomicLong expenseIdGenerator = new AtomicLong();
    private final AtomicLong incomeIdGenerator = new AtomicLong();

    /**
     * Initialize with sample data for demonstration purposes
     */
    public DataStorageService() {
        initializeSampleData();
    }

    // ==================== Category Storage Operations ====================

    /**
     * Get all categories
     * @return list of categories
     */
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
    
    public Category getCategoryById(Long id) {
        return categories.stream()
            .filter(category -> category.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /**
     * Save or update a category
     * @param category the category to save or update
     * @return the saved or updated category
     */
    public Category saveCategory(Category category) {
        if (category.getId() == null) {
            // New category
            category.setId(categoryIdGenerator.incrementAndGet());
            categories.add(category);
        } else {
            // Update existing category
            categories.stream()
                .filter(c -> c.getId().equals(category.getId()))
                .findFirst()
                .ifPresent(existingCategory -> existingCategory.setName(category.getName()));
        }
        return category;
    }

    /**
     * Delete a category by ID
     * @param id the ID of the category to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteCategoryById(Long id) {
        return categories.removeIf(category -> category.getId().equals(id));
    }

    /**
     * Delete all expenses associated with a specific category ID
     * @param categoryId the ID of the category whose expenses should be deleted
     * @return the number of deleted expenses
     */
    public int deleteExpensesByCategory(Long categoryId) {
        int initialSize = expenses.size();
        expenses.removeIf(expense -> expense.getCategory() != null &&
                expense.getCategory().getId().equals(categoryId));
        return initialSize - expenses.size(); // Return number of deleted expenses
    }

    /**
     * Get all expenses
     * @return list of expenses
     */
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
    
    public Expense getExpenseById(Long id) {
        return expenses.stream()
            .filter(expense -> expense.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /**
     * Save or update an expense
     * @param expense the expense to save or update
     * @return the saved or updated expense
     */
    public Expense saveExpense(Expense expense) {
        if (expense.getId() == null) {
            // New expense
            expense.setId(expenseIdGenerator.incrementAndGet());
            expenses.add(expense);
        } else {
            // Update existing expense
            expenses.stream()
                .filter(e -> e.getId().equals(expense.getId()))
                .findFirst()
                .ifPresent(existingExpense -> {
                    existingExpense.setDescription(expense.getDescription());
                    existingExpense.setAmount(expense.getAmount());
                    existingExpense.setDate(expense.getDate());
                    existingExpense.setCategory(expense.getCategory());
                });
        }
        return expense;
    }

    /**
     * Delete an expense by ID
     * @param id the ID of the expense to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteExpenseById(Long id) {
        return expenses.removeIf(expense -> expense.getId().equals(id));
    }
    
    public List<Expense> getExpensesByCategory(Long categoryId) {
        return expenses.stream()
            .filter(expense -> expense.getCategory() != null && 
                             expense.getCategory().getId().equals(categoryId))
            .toList();
    }

    // ==================== Income Storage Operations ====================

    /**
     * Get all incomes
     * @return list of incomes
     */
    public List<Income> getAllIncomes() {
        return new ArrayList<>(incomes);
    }
    
    public Income getIncomeById(Long id) {
        return incomes.stream()
            .filter(income -> income.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /**
     * Save or update an income
     * @param income the income to save or update
     * @return the saved or updated income
     */
    public Income saveIncome(Income income) {
        if (income.getId() == null) {
            // New income
            income.setId(incomeIdGenerator.incrementAndGet());
            incomes.add(income);
        } else {
            // Update existing income
            incomes.stream()
                .filter(i -> i.getId().equals(income.getId()))
                .findFirst()
                .ifPresent(existingIncome -> {
                    existingIncome.setDescription(income.getDescription());
                    existingIncome.setAmount(income.getAmount());
                    existingIncome.setDate(income.getDate());
                });
        }
        return income;
    }

    /**
     * Delete an income by ID
     * @param id the ID of the income to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteIncomeById(Long id) {
        return incomes.removeIf(income -> income.getId().equals(id));
    }

    /**
     * Get the user profile
     * @return the user profile
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Save or update the user profile
     * @param userProfile the user profile to save or update
     * @return the saved or updated user profile
     */
    public UserProfile saveUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return userProfile;
    }


    /**
     * Initialize the service with sample data
     */
    private void initializeSampleData() {
        // Initialize default categories
        saveCategory(new Category(null, "Food"));
        saveCategory(new Category(null, "Transport"));
        saveCategory(new Category(null, "Utilities"));
        saveCategory(new Category(null, "Entertainment"));
        saveCategory(new Category(null, "Healthcare"));
        
        // Initialize sample expenses
        saveExpense(new Expense(null, "Groceries", 75.50, LocalDate.now().minusDays(1), getCategoryById(1L)));
        saveExpense(new Expense(null, "Bus ticket", 2.50, LocalDate.now().minusDays(2), getCategoryById(2L)));
        saveExpense(new Expense(null, "Electricity bill", 120.00, LocalDate.now().minusDays(3), getCategoryById(3L)));
        saveExpense(new Expense(null, "Movie night", 25.00, LocalDate.now().minusDays(4), getCategoryById(4L)));
        
        // Initialize sample incomes
        saveIncome(new Income(null, "Salary", 2500.00, LocalDate.now().minusDays(1)));
        saveIncome(new Income(null, "Freelance work", 300.00, LocalDate.now().minusDays(5)));
        
        // Initialize default user profile
        userProfile = new UserProfile("User", 2000.00);
    }
}
