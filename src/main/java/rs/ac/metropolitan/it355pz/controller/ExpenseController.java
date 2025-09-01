package rs.ac.metropolitan.it355pz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355pz.model.Expense;
import rs.ac.metropolitan.it355pz.service.CategoryService;
import rs.ac.metropolitan.it355pz.service.ExpenseService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final CategoryService categoryService;

    public ExpenseController(ExpenseService expenseService, CategoryService categoryService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listExpenses(Model model) {
        model.addAttribute("expenses", expenseService.findAllExpenses());
        return "expenses";
    }

    @GetMapping("/new")
    public String showExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "expense-form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.findExpenseById(id);
        if (expense == null) {
            return "redirect:/expenses";
        }
        model.addAttribute("expense", expense);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "expense-form";
    }

    @PostMapping("/save")
    public String saveExpense(@ModelAttribute("expense") Expense expense, 
                            @RequestParam("categoryId") Long categoryId) {
        expenseService.saveExpense(expense, categoryId);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/expenses";
    }
}
