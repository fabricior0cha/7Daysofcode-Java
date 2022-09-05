package br.com.alura.java.model;

/**
 * 
 * @author Fabricio Rocha
 *	Optei pela imutabilidade, por conta de que um filme dificilmente vai sofrer alguma alteração
 *	nos seus dados ao longo do tempo.
 */

public class Movie {
	private String title;
	private String urlImage;
	private String rating;
	private String year;

	public Movie(String title, String urlImage, String rating, String year) {
		this.title = title;
		this.urlImage = urlImage;
		this.rating = rating;
		this.year = year;
	}

	public Movie() {
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public String getRating() {
		return rating;
	}

	public String getYear() {
		return year;
	}
	
	@Override
	public String toString() {
		return "Title: " + title + " Rating: " + rating;
	}

}
