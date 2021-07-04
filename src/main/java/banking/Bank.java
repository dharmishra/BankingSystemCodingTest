package banking;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	AtomicLong accountNumber = new AtomicLong(1);
	public Bank() {
		// complete the function
		accounts = new LinkedHashMap<>();
	}

	private Account getAccount(Long accountNumber) {
		// complete the function
        return this.accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		// complete the function
		long accNumber = accountNumber.getAndIncrement();
		CommercialAccount commercialAccount = new CommercialAccount(company,accNumber, pin, startingDeposit);
		accounts.put(accNumber, commercialAccount);
		return accNumber;
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		// complete the function
		long accNumber = accountNumber.getAndIncrement();
		ConsumerAccount consumerAccount = new ConsumerAccount(person,accNumber, pin, startingDeposit);
		accounts.put(accNumber, consumerAccount);
        return accNumber;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		// complete the function
		Account account = getAccount(accountNumber);

		if (account != null && account.validatePin(pin))
        	return true;
		else
			return false;
	}

	public double getBalance(Long accountNumber) {
		// complete the function
		Account account = getAccount(accountNumber);
		if (account != null) {
			return account.getBalance();
		}
        return -1;
	}

	public void credit(Long accountNumber, double amount) {
		// complete the function
		Account account = getAccount(accountNumber);
		if (account != null) {
			account.creditAccount(amount);
		}
	}

	public boolean debit(Long accountNumber, double amount) {
		// complete the function
		Account account = getAccount(accountNumber);
		if (account != null) {
			if (account.getBalance() < amount) {
				System.out.println("Account " + accountNumber + " should have insufficient funds.");
				return false;
			} else {
				account.debitAccount(amount);
			}
		}
        return true;
	}
}
