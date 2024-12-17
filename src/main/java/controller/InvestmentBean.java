package controller;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

@ManagedBean
@ViewScoped
public class InvestmentBean implements Serializable {

	private double investedAmount;
	private double currentEarnings;
	private double totalValue;
	private double taxAmount;
	private double totalAfterTax;

	private String investedAmountFormatted;
	private String currentEarningsFormatted;
	private String totalValueFormatted;
	private String taxAmountFormatted;
	private String totalAfterTaxFormatted;

	@PostConstruct
	public void init() {
		investedAmount = 10000.0; // Exemplo de valor aplicado
		currentEarnings = 500.0; // Exemplo de rendimento
		totalValue = investedAmount + currentEarnings; // Calcula o valor total

		taxAmount = currentEarnings * 0.225; // Calcula 22,5% de imposto sobre o rendimento
		totalAfterTax = totalValue - taxAmount; // Calcula o valor total após dedução do imposto

		// Formata os valores como moeda brasileira
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		investedAmountFormatted = currencyFormat.format(investedAmount);
		currentEarningsFormatted = currencyFormat.format(currentEarnings);
		totalValueFormatted = currencyFormat.format(totalValue);
		taxAmountFormatted = currencyFormat.format(taxAmount);
		totalAfterTaxFormatted = currencyFormat.format(totalAfterTax);

	}

	// Getters para os valores formatados
	public String getInvestedAmountFormatted() {
		return investedAmountFormatted;
	}

	public String getCurrentEarningsFormatted() {
		return currentEarningsFormatted;
	}

	public String getTotalValueFormatted() {
		return totalValueFormatted;
	}

	public String getTaxAmountFormatted() {
		return taxAmountFormatted;
	}

	public String getTotalAfterTaxFormatted() {
		return totalAfterTaxFormatted;
	}

}
