package bankomat;

import java.util.ArrayList;

public class Bankomat {
	
	/**
	 * Napraviti konzolni bankomat.
	 * Bankomat ima presetovanu koliÄ�inu novca u sebi podeljen na segmente
	 * 60 novÄ�anica od 10KM, 30 novÄ�anica od 20KM, 20 novÄ�anica od 50KM 
	 * 10 novÄ�anica od 100KM. 
	 * Dakle imamo 3200 KM u bankomatu(kad pokrenemo program).
	 * Bankomat takodje u sebi ima listu Korisnika koji imaju svoj user name password 
	 * i odredjenu sumu novca na raÄ�unu.
	 * Prilikom pokretanja programa bankomat od korisnika trazi User name 
	 * (pravicemo se da je ovo kartica) i password.
	 * Ukoliko ste obiÄ�an korisnik nudi mogucnost da dignete novac i da pogeldate 
	 * stanje na svom racunu, i da se izlogujete.
	 * Ukoliko ste Admin korisnik imate mogucnost da dodate novog korisnika,
	 * briÅ¡ete postojeceg korisnika, promenite stanje novcanica 
	 * (primer: izvadite sve pedesetice,dodate jos dvadesetki) i naravno 
	 * da se izlogujete.
	 * Jos bi dodao kao bonus poene da prilikom dizanja novca bankomat 
	 * proraÄ�una koje novÄ�anice da vam vrati, tipa ako ste digli 160KM a bankomat ima 
	 * samo 5 dvadesetki da vam pedesetku i desetku umesto 3 dvadesetke, u suÅ¡tini bankomat 
	 * se trudi da ima svih novÄ�anica najmanje 5 komada da te ugrozene novÄ�anice daje samo 
	 * kada mora!
	 * Ukoliko korisnik pokuÅ¡a da digne viÅ¡e od sume koju on poseduje da mu se 
	 * kulturno kaÅ¾e da se "ne zanosi" i da proba ponovo, a ukoliko korisnik ima na raÄ�unu 
	 * ali bankomat nema da mu isplati da ga kulturno zamolimo da ode na drugi bankomat ili
	 * u banku direktno.
	 */
	
	private int desetke = 60;
	private int dvadesetke = 30;
	private int pedesetke = 20;
	private int stotke = 10;
	private int ukupnoNovca = 3200;
	
	ArrayList<Bankomat> bankomatList = new ArrayList<>();

	Bankomat() {
		
	}
	
	Bankomat(int desetke, int dvadesetke, int pedesetke, int stotke) {
		this.desetke = getDesetke() + desetke;
		this.dvadesetke = getDvadesetke() + dvadesetke;
		this.pedesetke = getPedesetke() + pedesetke;
		this.stotke = getStotke() + stotke;
		this.ukupnoNovca = getStotke() + getPedesetke() + getDvadesetke() + getDesetke();
	}
	
	Bankomat(int desetke, int dvadesetke, int pedesetke, int stotke, int ukupnoNovca) {
		this.desetke = desetke;
		this.dvadesetke = dvadesetke;
		this.pedesetke = pedesetke;
		this.stotke = stotke;
		this.ukupnoNovca = ukupnoNovca;
	}

	public int getDesetke() {
		return desetke;
	}

	public void setDesetke(int desetke) {
		this.desetke = desetke;
	}

	public int getDvadesetke() {
		return dvadesetke;
	}

	public void setDvadesetke(int dvadesetke) {
		this.dvadesetke = dvadesetke;
	}

	public int getPedesetke() {
		return pedesetke;
	}

	public void setPedesetke(int pedesetke) {
		this.pedesetke = pedesetke;
	}

	public int getStotke() {
		return stotke;
	}

	public void setStotke(int stotke) {
		this.stotke = stotke;
	}

	public int getUkupnoNovca() {
	//	ukupnoNovca = (getDesetke()) + (getDvadesetke()) + (getPedesetke()) + (getStotke());
		return ukupnoNovca;
	}

	public void setUkupnoNovca(int ukupnoNovca) {
		this.ukupnoNovca = ukupnoNovca;
	}
	
	public void withdrawFromBankomat(int amount) {
		while(amount >= 100 && this.stotke > 0) { 
			this.stotke = getStotke() - 100;
			amount = amount - 100;
		}
		while(amount >= 50 && this.pedesetke > 0) {
			this.pedesetke = getPedesetke() - 50;
			amount -= 50;
		}
		while(amount >= 20 && this.dvadesetke > 0) {
			this.dvadesetke = getDvadesetke() - 20;
			amount -= 20;
		}
		while(amount >= 10 && this.desetke > 0) {
			this.desetke = getDesetke() - 10;
			amount -= 10;
		}
		this.ukupnoNovca = getStotke() + getPedesetke() + getDvadesetke() + getDesetke();
		this.ukupnoNovca = getUkupnoNovca() - amount;
	}
	
}
