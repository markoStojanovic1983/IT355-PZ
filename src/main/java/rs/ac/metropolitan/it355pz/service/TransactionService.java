package rs.ac.metropolitan.it355pz.service;

import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355pz.model.Expense;
import rs.ac.metropolitan.it355pz.model.Income;
import rs.ac.metropolitan.it355pz.model.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService {
    
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public TransactionService(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    /**
     * Get all transactions (incomes and expenses combined) sorted by date (newest first)
     * @return list of all transactions
     */
    public List<Transaction> findAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        
        // Convert expenses to transactions
        for (Expense expense : expenseService.findAllExpenses()) {
            Transaction transaction = new Transaction();
            transaction.setId(expense.getId());
            transaction.setDescription(expense.getDescription());
            transaction.setAmount(expense.getAmount());
            transaction.setDate(expense.getDate());
            transaction.setType("EXPENSE");
            transaction.setCategory(expense.getCategory());
            transactions.add(transaction);
        }
        
        // Convert incomes to transactions
        for (Income income : incomeService.findAllIncomes()) {
            Transaction transaction = new Transaction();
            transaction.setId(income.getId());
            transaction.setDescription(income.getDescription());
            transaction.setAmount(income.getAmount());
            transaction.setDate(income.getDate());
            transaction.setType("INCOME");
            transaction.setCategory(null); // Incomes don't have categories
            transactions.add(transaction);
        }
        
        // Sort by date (newest first)
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        
        return transactions;
    }

    /**
     * Get count of all transactions
     * @return total number of transactions
     */
    public int getTransactionCount() {
        return expenseService.getExpenseCount() + incomeService.getIncomeCount();
    }
}
