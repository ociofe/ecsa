// default package
// Generated 13-feb-2015 13.52.38 by Hibernate Tools 3.4.0.CR1
package main.webapp.ecsa.hibernate;
/**
 * Genres generated by hbm2java
 */
public class Genres implements java.io.Serializable {

	private Integer id;
	private String genre;

	public Genres() {
	}

	public Genres(String genre) {
		this.genre = genre;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
