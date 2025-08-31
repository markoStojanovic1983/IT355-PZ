package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355pz.model.Income;

import java.util.List;

@Service
public class IncomeService {
    
    private final DataStorageService dataStorageService;

    public IncomeService(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    /**
     * Fetch all incomes from the data storage.
     * @return list of all incomes
     */
    public List<Income> findAllIncomes() {
        return dataStorageService.getAllIncomes();
    }

    /**
     * Find income by its ID.
     * @param id the ID of the income to find
     * @return the income with the specified ID, or null if not found
     */
    public Income findIncomeById(Long id) {
        return dataStorageService.getIncomeById(id);
    }

    /**
     * Save or update an income.
     * @param income the income to save or update
     * @return the saved or updated income
     */
    public Income saveIncome(Income income) {
        return dataStorageService.saveIncome(income);
    }

    /**
     * Delete an income by its ID.
     * @param id the ID of the income to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteIncomeById(Long id) {
        return dataStorageService.deleteIncomeById(id);
    }

    /**
     * Get the total amount of all incomes.
     * @return the total amount of all incomes
     */
    public double getTotalIncomes() {
        return dataStorageService.getAllIncomes().stream()
            .mapToDouble(Income::getAmount)
            .sum();
    }

    /**
     * Get the total count of incomes.
     * @return the number of incomes
     */
    public int getIncomeCount() {
        return dataStorageService.getAllIncomes().size();
    }
}