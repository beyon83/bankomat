package bankomat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BalanceFile extends Bankomat {
	
	File file = new File("balance.txt");
	

	/** Read from file and send information to the Array List */
	public void readBalanceFile() throws FileNotFoundException {
		File readFile = new File("balance.txt");
		Scanner readScan = new Scanner(readFile);
		while(readScan.hasNext()) {
			int desetke = readScan.nextInt();
			int dvadesetke = readScan.nextInt();
			int pedesetke = readScan.nextInt();
			int stotke = readScan.nextInt();
			int ukupnoNovca = readScan.nextInt();
			Bankomat bankBalance = new Bankomat(desetke, dvadesetke, pedesetke, stotke, ukupnoNovca);
			bankomatList.add(bankBalance);
		//	System.out.println(bankBalance.getDesetke() + " " + bankBalance.getDvadesetke() + " " + bankBalance.getPedesetke() + " " + bankBalance.getStotke() + " " + bankBalance.getUkupnoNovca());
		}
		readScan.close();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** Write new balance to the file */
	public void writeBalance() throws FileNotFoundException {
	//	PrintWriter writer = new PrintWriter(new FileOutputStream(file, false)); // For overwriting data rather than appending it
	//	PrintWriter writer = new PrintWriter(new FileWriter(file, false));
		PrintWriter writer = new PrintWriter(file);
		try {
			for(Bankomat bList: bankomatList) {
				writer.println(bList.getDesetke());
				writer.println(bList.getDvadesetke());
				writer.println(bList.getPedesetke());
			    writer.println(bList.getStotke());
				writer.println(bList.getUkupnoNovca());
				writer.println();
			//	System.out.println(bList.getDesetke() + " " + bList.getDvadesetke() + " " + bList.getPedesetke() + " " + bList.getStotke() + " " + bList.getUkupnoNovca());
			}
		}
		finally {
			writer.close();
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void bankomatContains() {
		for(Bankomat b: bankomatList) {
			System.out.println("Bankomat contains: ");
			System.out.println("10KM bills: x" + b.getDesetke() / 10 + "(" + b.getDesetke() + "KM)");
			System.out.println("20KM bills: x" + b.getDvadesetke() / 20 + "(" + b.getDvadesetke() + "KM)");
			System.out.println("50KM bills: x" + b.getPedesetke() / 50 + "(" + b.getPedesetke() + "KM)");
			System.out.println("100KM bills: x" + b.getStotke() / 100 + "(" + b.getStotke() + "KM)");
			System.out.println();
		}
	}
	
}