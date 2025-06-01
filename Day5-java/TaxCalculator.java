package MayBatchJava.Jun1;

import java.util.Scanner;

public class TaxCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Annual Salary (in INR): ");
        double salary = scanner.nextDouble();

        System.out.print("Enter your Age (in years): ");
        int age = scanner.nextInt();

        System.out.print("Enter Investment in Tax-saving Instruments (in INR): ");
        double investment = scanner.nextDouble();

        System.out.print("Enter Health Insurance Premium (in INR): ");
        double healthInsurance = scanner.nextDouble();

        System.out.print("Enter Home Loan Interest Paid Annually (in INR): ");
        double homeLoanInterest = scanner.nextDouble();

        // Deductions
        double deduction80C = Math.min(investment, 150000);
        double deduction80D = (age < 60) ? Math.min(healthInsurance, 25000) : Math.min(healthInsurance, 50000);
        double deduction24 = Math.min(homeLoanInterest, 200000);
        double totalDeductions = deduction80C + deduction80D + deduction24;

        // Taxable income after deductions
        double taxableIncome = Math.max(0, salary - totalDeductions);

        // Calculate tax before rebate
        double tax = calculateTax(taxableIncome, age);

        // Section 87A rebate
        if (taxableIncome <= 500000) {
            double rebate = Math.min(tax, 12500);
            tax -= rebate;
        }

        // Output
        System.out.println("\n----- Tax Summary -----");
        System.out.println("Total Deductions: ₹" + totalDeductions);
        System.out.println("Taxable Income: ₹" + taxableIncome);
        System.out.println("Total Tax Owed: ₹" + tax);
    }

    public static double calculateTax(double income, int age) {
        if (age < 60) {
            if (income <= 250000) return 0;
            if (income <= 500000) return (income - 250000) * 0.05;
            if (income <= 1000000)
                return (250000 * 0.05) + (income - 500000) * 0.20;
            return (250000 * 0.05) + (500000 * 0.20) + (income - 1000000) * 0.30;

        } else if (age <= 80) {
            if (income <= 300000) return 0;
            if (income <= 500000) return (income - 300000) * 0.05;
            if (income <= 1000000)
                return (200000 * 0.05) + (income - 500000) * 0.20;
            return (200000 * 0.05) + (500000 * 0.20) + (income - 1000000) * 0.30;

        } else {
            if (income <= 500000) return 0;
            if (income <= 1000000)
                return (income - 500000) * 0.20;
            return (500000 * 0.20) + (income - 1000000) * 0.30;
        }
    }
}
