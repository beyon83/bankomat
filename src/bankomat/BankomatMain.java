package bankomat;

import java.io.FileNotFoundException;

public class BankomatMain {

	public static void main(String[] args) {
		
		AccountFile accountFile = new AccountFile();
		
		BalanceFile readBalance = new BalanceFile();
		
		try {
			readBalance.readBalanceFile();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			accountFile.readAccountFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			accountFile.checkPassword();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}