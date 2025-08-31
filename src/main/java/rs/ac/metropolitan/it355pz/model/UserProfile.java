package rs.ac.metropolitan.it355pz.model;

public class UserProfile {
    private String userName;
    private Double monthlyBudget;

    // Default constructor
    public UserProfile() {
    }

    // Constructor with parameters
    public UserProfile(String userName, Double monthlyBudget) {
        this.userName = userName;
        this.monthlyBudget = monthlyBudget;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(Double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userName='" + userName + '\'' +
                ", monthlyBudget=" + monthlyBudget +
                '}';
    }
}
