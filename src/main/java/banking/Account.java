package banking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Abstract bank account class.<br>
 * <br>
 *
 * Private Variables:<br>
 * {@link #accountHolder}: AccountHolder<br>
 * {@link #accountNumber}: Long<br>
 * {@link #pin}: int<br>
 * {@link #balance}: double
 */
public abstract class Account {
	private AccountHolder accountHolder;
	private Long accountNumber;
	private int pin;
	private double balance;

	Lock lock = new ReentrantLock(true);
	protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
		// complete the constructor
		this.accountHolder = accountHolder;
		this.accountNumber  = accountNumber;
		this.pin = pin;
		this.balance = startingDeposit;
	}

	public AccountHolder getAccountHolder() {
		// complete the function
        return this.accountHolder;
	}

	public boolean validatePin(int attemptedPin) {
		// complete the function
		if (attemptedPin == this.pin)
        	return true;
		else
			return false;
	}

	public double getBalance() {
		// complete the function
		lock.lock();
        double bal =  this.balance;
        lock.unlock();
        return bal;
	}

	public Long getAccountNumber() {
		// complete the function
        return this.accountNumber;
	}

	public void creditAccount(double amount) {
		// complete the function
		lock.lock();
		this.balance += amount;
		lock.unlock();
	}

	public boolean debitAccount(double amount) {
		// complete the function
		lock.lock();
		if (balance < amount) {
			return false;
		} else {
			balance -= amount;
		}
		lock.unlock();
        return true;
	}
}
