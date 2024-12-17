package adriano.model;

import java.util.Date;

public class Investment {
    private Date investmentDate;
    private double investedAmount;
    private double interestRate;
    private double currentValue;

    public Investment(Date investmentDate, double investedAmount, double interestRate, double currentValue) {
        this.investmentDate = investmentDate;
        this.investedAmount = investedAmount;
        this.interestRate = interestRate;
        this.currentValue = currentValue;
    }

    // Getters and Setters
    public Date getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(Date investmentDate) {
        this.investmentDate = investmentDate;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
