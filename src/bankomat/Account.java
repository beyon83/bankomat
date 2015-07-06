package bankomat;

import java.util.ArrayList;

public class Account extends BalanceFile {
	
	private String userName;
	private int password;
	private int balance;
	
	ArrayList<Account> usersList = new ArrayList<>();
	
	Account() {
		
	}
	
	Account(String userName, int password) {
		this.userName = userName;
		this.password = password;
	}
	
	Account(String userName, int password, int balance) {
		this.userName = userName;
		this.password = password;
		this.balance = balance;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(int password) {
		this.password = password;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getPassword() {
		return password;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void withdraw(int amount) {
		this.balance = getBalance() - amount;
	}
	
	public void deposit(int amount) {
		this.balance = getBalance() + amount;
	}
	
}