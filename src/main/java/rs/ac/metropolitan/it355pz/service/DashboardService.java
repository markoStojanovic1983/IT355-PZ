package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355pz.model.UserProfile;

@Service
public class DashboardService {
    
    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final CategoryService categoryService;
    private final DataStorageService dataStorageService;

    public DashboardService(ExpenseService expenseService, 
                          IncomeService incomeService,
                          CategoryService categoryService,
                          DataStorageService dataStorageService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.categoryService = categoryService;
        this.dataStorageService = dataStorageService;
    }

    /**
     * Gets the total expenses from the ExpenseService.
     * @return the total expenses
     */
    public double getTotalExpenses() {
        return expenseService.getTotalExpenses();
    }

    /**
     * Gets the total incomes from the IncomeService.
     * @return the total incomes as a double
     */
    public double getTotalIncomes() {
        return incomeService.getTotalIncomes();
    }

    /**
     * Calculates the current balance by subtracting total expenses from total incomes.
     * @return the current balance
     */
    public double getCurrentBalance() {
        return getTotalIncomes() - getTotalExpenses();
    }

    /**
     * Gets the count of expenses from the ExpenseService.
     * @return the number of expenses
     */
    public int getExpenseCount() {
        return expenseService.getExpenseCount();
    }

    /**
     * Gets the count of incomes from the IncomeService.
     * @return the number of incomes
     */
    public int getIncomeCount() {
        return incomeService.getIncomeCount();
    }

    /**
     * Gets the count of categories from the CategoryService.
     * @return the number of categories
     */
    public int getCategoryCount() {
        return categoryService.getCategoryCount();
    }

    /**
     * Retrieves the user profile from the DataStorageService.
     * @return the UserProfile object
     */
    public UserProfile getUserProfile() {
        return dataStorageService.getUserProfile();
    }

    /**
     * Saves or updates the user profile using the DataStorageService.
     * @param userProfile the UserProfile object to save or update
     * @return the saved or updated UserProfile object
     */
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return dataStorageService.saveUserProfile(userProfile);
    }
}