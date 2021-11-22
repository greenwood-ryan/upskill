/*
The facade pattern (also spelled façade) is a software-design pattern commonly used in object-oriented programming. Analogous to a facade in architecture, a facade is an object that serves as a front-facing interface masking more complex underlying or structural code. A facade can:

	improve the readability and usability of a software library by masking interaction with more complex components behind a single (and often simplified) API
	provide a context-specific interface to more generic functionality (complete with context-specific input validation)
	serve as a launching point for a broader refactor of monolithic or tightly-coupled systems in favor of more loosely-coupled code
Developers often use the facade design pattern when a system is very complex or difficult to understand because the system has many interdependent classes or because its source code is unavailable. This pattern hides the complexities of the larger system and provides a simpler interface to the client. It typically involves a single wrapper class that contains a set of members required by the client. These members access the system on behalf of the facade client and hide the implementation details

The facade pattern is typically used when

a simple interface is required to access a complex system,
a system is very complex or difficult to understand,
an entry point is needed to each level of layered software, or
the abstractions and implementations of a subsystem are tightly coupled.
*/

public class FacadePatternTest{
	public static void main(String[] args){
		BankAccountFacade accessingBank = new BankAccountFacade(123456789, 1234);
		accessingBank.withdrawCash(900.00);
		accessingBank.withdrawCash(50.00);
	}
}

class BankAccountFacade {

	private int accountNumber;
	private int securityCode;
	
	AccountNumberCheck acctChecker;
	SecurityCodeCheck codeChecker;
	FundsCheck fundChecker;
	WelcomeToBank bankWelcome;
	
	public BankAccountFacade(int newAcctNum, int newSecCode){
		accountNumber = newAcctNum;
		securityCode = newSecCode;
		bankWelcome = new WelcomeToBank();
		acctChecker = new AccountNumberCheck();
		codeChecker = new SecurityCodeCheck();
		fundChecker = new FundsCheck();
	}
	
	public int getAccountNumber() { return accountNumber; }

	public int getSecurityCode() { return securityCode; }

	public void withdrawCash(double cashToGet){
		if(acctChecker.accountActive(getAccountNumber()) &&
			codeChecker.isCodeCorrect(getSecurityCode()) &&
			fundChecker.haveEnoughMoney(cashToGet)) {
			System.out.println("Transaction Complete\n");
		} else {
			System.out.println("Transaction Failed\n");
		}
	}

	public void depositCash(double cashToDeposit){
		if(acctChecker.accountActive(getAccountNumber()) &&
			codeChecker.isCodeCorrect(getSecurityCode())) {
			fundChecker.makeDeposit(cashToDeposit);
			System.out.println("Transaction Complete\n");
		} else {
			System.out.println("Transaction Failed\n");
		}
	}
}

class WelcomeToBank{
	public WelcomeToBank() {
	System.out.println("Welcome to ABC Bank");
	System.out.println("We are happy to give you your money if we can find it\n");
	}
}

class AccountNumberCheck{
	private int accountNumber = 123456789;

	public int getAccountNumber() { return accountNumber; }

	public boolean accountActive(int acctNumToCheck){
		if(acctNumToCheck == getAccountNumber()) {
			return true;
		} else {
			return false;
		}
	}
}

class SecurityCodeCheck {

	private int securityCode = 1234;

	public int getSecurityCode() { return securityCode; }

	public boolean isCodeCorrect(int secCodeToCheck){
		if(secCodeToCheck == getSecurityCode()) {
			return true;
		} else {
			return false;
		}
	}
}

class FundsCheck {
	private double cashInAccount = 1000.00;

	public double getCashInAccount() { return cashInAccount; }

	public void decreaseCashInAccount(double cashWithdrawn) { cashInAccount -= cashWithdrawn; }

	public void increaseCashInAccount(double cashDeposited) { cashInAccount += cashDeposited; }

	public boolean haveEnoughMoney(double cashToWithdrawal) {
		if(cashToWithdrawal > getCashInAccount()) {
			System.out.println("Error: You don't have enough money");
			System.out.println("Current Balance: " + getCashInAccount());
			return false;
		} else {
			decreaseCashInAccount(cashToWithdrawal);
			System.out.println("Withdrawal Complete: Current Balance is " + getCashInAccount());
			return true;
		}
	}

	public void makeDeposit(double cashToDeposit) {
		increaseCashInAccount(cashToDeposit);
		System.out.println("Deposit Complete: Current Balance is " + getCashInAccount());
	}
}


