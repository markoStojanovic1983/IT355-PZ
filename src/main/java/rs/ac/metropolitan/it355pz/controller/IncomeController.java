package rs.ac.metropolitan.it355pz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355pz.model.Income;
import rs.ac.metropolitan.it355pz.service.IncomeService;

@Controller
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public String listIncomes(Model model) {
        model.addAttribute("incomes", incomeService.findAllIncomes());
        return "incomes";
    }

    @GetMapping("/new")
    public String showIncomeForm(Model model) {
        model.addAttribute("income", new Income());
        return "income-form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditIncomeForm(@PathVariable Long id, Model model) {
        Income income = incomeService.findIncomeById(id);
        if (income == null) {
            return "redirect:/incomes";
        }
        model.addAttribute("income", income);
        return "income-form";
    }

    @PostMapping("/save")
    public String saveIncome(@ModelAttribute("income") Income income) {
        incomeService.saveIncome(income);
        return "redirect:/incomes";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncomeById(id);
        return "redirect:/incomes";
    }
}
