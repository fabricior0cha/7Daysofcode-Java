package br.com.alura.java.model;

public class Movie {
	private String title;
	private String urlImage;
	private Double rating;
	private Integer year;

	public Movie(String title, String urlImage, Double rating, Integer year) {
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

	public Double getRating() {
		return rating;
	}

	public Integer getYear() {
		return year;
	}

}
