/**
 * 
 */
package main.webapp.ecsa.dao;

/**
 * @author fdelbon
 *
 */
public class Locazione {
	
	public String codFornitore;

	
	public String C1orCH;
	//Getter and setter
	
	public String magazzino;
	
	public String zona;
	
	
	public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public String getC1orCH() {
		return C1orCH;
	}

	public void setC1orCH(String c1orCH) {
		C1orCH = c1orCH;
	}

	public String getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}


}
