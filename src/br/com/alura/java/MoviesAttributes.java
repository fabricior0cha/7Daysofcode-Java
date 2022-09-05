package br.com.alura.java;

public enum MoviesAttributes {
	TITLE(3),
	YEAR(4),
	URLIMAGE(5),
	RATING(7);

	private final int indexOfAttribute;
	
	MoviesAttributes(int indexOfAttribute) {
		this.indexOfAttribute = indexOfAttribute;
	}
	
	public int getIndex() {
		return indexOfAttribute;
	}
	
}
