package rs.ac.metropolitan.it355pz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.metropolitan.it355pz.service.TransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.findAllTransactions());
        return "transactions";
    }
}
