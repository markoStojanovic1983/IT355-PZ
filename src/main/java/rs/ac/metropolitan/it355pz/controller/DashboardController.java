package rs.ac.metropolitan.it355pz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.ac.metropolitan.it355pz.service.DashboardService;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {
        double totalExpenses = dashboardService.getTotalExpenses();
        double totalIncomes = dashboardService.getTotalIncomes();
        double balance = dashboardService.getCurrentBalance();
        
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("totalIncomes", totalIncomes);
        model.addAttribute("balance", balance);
        model.addAttribute("expenseCount", dashboardService.getExpenseCount());
        model.addAttribute("incomeCount", dashboardService.getIncomeCount());
        model.addAttribute("categoryCount", dashboardService.getCategoryCount());
        model.addAttribute("userProfile", dashboardService.getUserProfile());
        

        
        return "dashboard";
    }
}
