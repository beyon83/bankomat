package bankomat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountFile extends Account {
	
	File file = new File("account.txt");
	
//////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** Read from file and send information to the Array List */
	public void readAccountFile() throws FileNotFoundException {
		Scanner readScan = new Scanner(file);
		while(readScan.hasNext()) {
			String userName = readScan.next();
			userName += " " + readScan.next();
			int password = readScan.nextInt();
			int balance = readScan.nextInt();
		//	int balance = Integer.parseInt(readScan.next());
			Account users = new Account(userName, password, balance);
			usersList.add(users);
		}
		readScan.close();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Check for password */
	public void checkPassword() throws FileNotFoundException {
		System.out.println("Please enter your user name and your password: ");
		Scanner input = new Scanner(System.in);
		String userName = input.next();
		userName = userName + " " + input.next();
		int password = input.nextInt();
		
		boolean bool = false;

		for(Account list: usersList) {
			if(userName.equals(list.getUserName()) && password == list.getPassword()) {
				if(list.getUserName().equals("Bojan Aleksic")) {
					adminLogin();
				}
				System.out.println("Welcome " + list.getUserName());
				displayMenu();
				System.out.println("(Unesite br. 1 za provjeru stanja racuna, broj 2 za povlacenje novca sa racuna, broj 3 za EXIT)");
				int broj = input.nextInt();
				
				if(broj == 3) {
					System.out.println(">EXIT");
					checkPassword();
					//	System.exit(1);
				}
				
				/** Check if number is greater than 3 or lower than 1 */
				while(broj > 3 || broj < 1) {
					System.out.println("You entered wrong number. Try again.");
					broj = input.nextInt();
				}
				/** Display balance for logged user */
				while(broj == 1 || broj == 2) {
					if(broj == 1) {
						System.out.println("Stanje vaseg racuna je: " + list.getBalance());
						System.out.println("(Unesite broj 1 za provjeru stanja racuna, broj 2 za povlacenje novca sa racuna, broj 3 za EXIT)");
						broj = input.nextInt();
					}
					/** Withdraw */
					else if(broj == 2) {
						
						/** Read balance.txt file */
						readBalanceFile();
						
						System.out.println(">WITHDRAW\nHow much money do you want to withdraw from your account: ");
						int withdraw = input.nextInt();
						for(Bankomat bList: bankomatList) {
							while(withdraw > list.getBalance() || withdraw < 0) {
								System.out.println("You don't have that much money on your account.");
								withdraw = input.nextInt();
							}
							while(withdraw > bList.getUkupnoNovca()) {
								System.out.println("There is no that much money in the Bankomat.");
								withdraw = input.nextInt();
							}
							/** Check if withdrawal amount is divisible by 10 */
							if(withdraw % 10 == 0) {
								list.withdraw(withdraw);
							} else {
								while(withdraw % 10 != 0) {
									System.out.println("Mozete podici samo iznos koji je djeljiv sa 10");
									withdraw = input.nextInt();
								}
							}
						}
							
						/** Write new balance to the balance.txt file */
						for(Bankomat bList: bankomatList) {
							bList.withdrawFromBankomat(withdraw);
						}
						try {
							writeBalance();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
							
						/** Write new balance to the file after withdrawal */
						try {
							writeUser();
						} catch (IOException e) {
							e.printStackTrace();
						}
							
						System.out.println("Stanje vaseg racuna je: " + list.getBalance());
						System.out.println("(Unesite broj 1 za provjeru stanja racuna, broj 2 za povlacenje novca sa racuna, broj 3 za EXIT)");
						broj = input.nextInt();
					}
					/** User logout */
					else if(broj == 3) {
						System.out.println(">EXIT");
						checkPassword();
					//	System.exit(1);
					}
				}
			bool = true;
		} 
	}
	if(bool == false) {
		System.out.println("Sorry, user name or password mismatched.");
	}
	input.close();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** Admin Login */
	public void adminLogin() throws FileNotFoundException {
		for(Account list: usersList) {
			if(list.getUserName().equals("Bojan Aleksic") && list.getPassword() == 1234) {
				
			}
		}
		Scanner input = new Scanner(System.in);
		boolean admin = true;
		
		while(admin) {
			/** Admin's menu */
			System.out.println("Welcome Admin!");
			System.out.println("Choose desirable option: ");
			System.out.println("1. Display users");
			System.out.println("2. Add new user");
			System.out.println("3. Delete user");
			System.out.println("4. Deposit money to the Bankomat");
			System.out.println("5. Exit");
			int option = input.nextInt();
			/** Display accounts */
			if(option == 1) {
				for(Account listDisplay: usersList) {
					System.out.println("User: " + listDisplay.getUserName() + "\nBalance: " + listDisplay.getBalance());
					System.out.println();
				}
			/** Add new user */
			} else if(option == 2) {
				System.out.println("Add new user: ");
				String userName = input.next();
				userName = userName + " " + input.next();
				for(int i = 0; i < usersList.size(); i++) {
					/** Check if user already exists */
					while(usersList.get(i).getUserName().contains(userName)) {
						for(int j = 0; j < usersList.size(); j++) {
							System.out.println("User name: \"" + userName + "\" already exists! Please enter another user name for your account.");
							userName = input.next();
							userName = userName + " " + input.next();
							if(userName.equals("Bojan Aleksic")) {
								System.out.println("You can't add admin's name");
								continue;
							}
							if(!usersList.get(j).getUserName().contains(userName)) {
								break;
							}
							i = 0;
							j = 0;
						}
						i = 0;
					}
				}
				/** Limit the password to contain only 4 numbers */
				String password = input.next();
				while(password.length() != 4) {
					System.out.println("Password must contain exactly 4 numbers.");
					password = input.next();
				} 
				int pass = Integer.parseInt(password);
				
				int balance = input.nextInt();
				Account newUser = new Account(userName, pass, balance);
				usersList.add(newUser);
				PrintWriter writer = new PrintWriter(file);
				for(Account listDisplay: usersList) {
					writer.println(listDisplay.getUserName());
					writer.println(listDisplay.getPassword());
					writer.println(listDisplay.getBalance());
					writer.println();
				}
				writer.close();
				
			/** Delete user */	
			} else if(option == 3) {
				try {
					System.out.println("Enter a user to delete");
				//	String deleteUser = JOptionPane.showInputDialog("Enter a user to delete", input.nextLine());
					Scanner inputDelete = new Scanner(System.in);
					String deleteUser = inputDelete.nextLine();
					while(deleteUser.equals("Bojan Aleksic")) {
						System.out.println("Are you out of your mind?! You can't delete administrator's account!");
						System.out.println("Enter a user to delete");
						deleteUser = inputDelete.nextLine();
					}
					boolean userDeleted = false;
					for(int i = 0; i < usersList.size(); i++) {
						if(usersList.get(i).getUserName().contains(deleteUser)) {
							usersList.remove(i);
							userDeleted = true;
							break;
						} 
						for(int j = 0; j < usersList.size(); j++) {
							if(!usersList.get(j).getUserName().contains(deleteUser)) {
								userDeleted = false;
								break;
							}
						}
					}
					if(userDeleted == true) {
						System.out.println("User " + "\"" + deleteUser + "\" has been successfully deleted.");
					} else {
						System.out.println("User \"" + deleteUser + "\" doesn't exist.");
					}

					/** Write new account balance to the file, after removing accounts */
					try {
						writeUser();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			   	finally {
			   		
				}
				
			/** Add money to the bankomat */	
			} else if(option == 4) {
				
				readBalanceFile();
				
				System.out.println("Add 10KM bills: ");
				int deset = input.nextInt();
				for(Bankomat l: bankomatList) {
					while(deset * 10 + l.getDesetke() > 600) {
						System.out.println("You have exceeded limit of 10s(Max limit is: 600KM). ");
						System.out.println("Current 10 bills balance is: " + l.getDesetke());
						deset = input.nextInt();
					} 
					if(deset != 0) {
						deset *= 10;
					}
				}
				
				System.out.println("Add 20KM bills: ");
				int dvadeset = input.nextInt();
				for(Bankomat l: bankomatList) {
					while(dvadeset * 20 + l.getDvadesetke() > 600) {
						System.out.println("You have exceeded limit of 20s(Max limit is: 600KM). ");
						System.out.println("Current 20 bills balance is: " + l.getDvadesetke());
						dvadeset = input.nextInt();
					}
					if(dvadeset != 0) {
						dvadeset *= 20;
					}
				}
				
				System.out.println("Add 50KM bills: ");
				int pedeset = input.nextInt();
				for(Bankomat l: bankomatList) {
					while(pedeset * 50 + l.getPedesetke() > 1000) {
						System.out.println("You have exceeded limit of 50s(Max limit is: 1000KM). ");
						System.out.println("Current 50 bills balance is: " + l.getPedesetke());
						pedeset = input.nextInt();
					}
					if(pedeset != 0) {
						pedeset *= 50;
					}
				}
				
				
				System.out.println("Add 100KM bills: ");
				int stotinu = input.nextInt();
				for(Bankomat l: bankomatList) {
					while(stotinu * 100 + l.getStotke() > 1000) {
						System.out.println("You have exceeded limit of 100s(Max limit is: 1000KM). ");
						System.out.println("Current 100 bills balance is: " + l.getStotke());
						stotinu = input.nextInt();
					}
					if(stotinu != 0) {
						stotinu *= 100;
					}
				}
				
				int ukupno = deset + dvadeset + pedeset + stotinu;
				
				/** Clear and reset the ArrayList */
				bankomatList.clear();
				
				/** Read balance.txt file and add new balance to the ArrayList */
				File readFile = new File("balance.txt");
				Scanner readScan = new Scanner(readFile);
				while(readScan.hasNext()) {
					int desetke = readScan.nextInt();
					int dvadesetke = readScan.nextInt();
					int pedesetke = readScan.nextInt();
					int stotke = readScan.nextInt();
					int ukupnoNovca = readScan.nextInt();
					desetke += deset;
					dvadesetke += dvadeset;
					pedesetke += pedeset;
					stotke += stotinu;
					ukupnoNovca += ukupno;

					Bankomat bankBalance = new Bankomat(desetke, dvadesetke, pedesetke, stotke, ukupnoNovca);
					bankomatList.add(bankBalance);
					
				}
				readScan.close();
				
				/** Write new balance to the balance.txt file */
				try {
					writeBalance();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			    /** Display content of the bankomat */
				bankomatContains();
				
			} else if(option == 5) {
				System.out.println(">EXIT");
				checkPassword();
			//	System.exit(3);
			}
		}
		input.close();
	}
		
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Display menu */
	public void displayMenu() {
		System.out.println("Main menu: ");
		System.out.println("1. Check balance");
		System.out.println("2. Withdraw");
		System.out.println("3. Exit");
	}
	
	/** Write new balance to the file */
	public void writeUser() throws IOException {
		//FileWriter writer = new FileWriter(file);
		PrintWriter writer = new PrintWriter(file);
		try {
			for(Account list: usersList) {
				writer.println(list.getUserName());
				writer.println(list.getPassword());
				writer.println(list.getBalance());
				writer.println();
			}
	    } finally {
	    	writer.close();
	    }
	}

}
