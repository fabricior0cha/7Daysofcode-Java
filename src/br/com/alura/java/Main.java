package br.com.alura.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import br.com.alura.java.model.Movie;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("API_IMDB_KEY"));
		HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
	
		String [] moviesArray = parseJson(json);
		List<Movie> movies = new ArrayList<>();
		
		for(int i = 0; i < moviesArray.length; i++) {
			movies.add(handleMovie(moviesArray[i]));
		}
		
		Writer writer = new PrintWriter("page.html");
		HtmlGenerator hg = new HtmlGenerator(writer);
		hg.generate(movies);
		writer.close();
	
	}
	
	private static Movie handleMovie(String movie) {
		String title = parseMovieTitle(movie);
		String urlImage = parseUrlImage(movie);
		String ratin = parseRating(movie);
		String year = parseYear(movie);
		return new Movie(title, urlImage, ratin, year);
	}
	
	private static String parseYear(String movie) {
		return parseAttribute(movie, MoviesAttributes.YEAR);
	}

	private static String parseRating(String movie) {
		return parseAttribute(movie, MoviesAttributes.RATING);
	}

	private static String parseUrlImage(String movie) {
		return parseAttribute(movie, MoviesAttributes.URLIMAGE);
	}

	private static String[] parseJson(String json) {
		Matcher matcher = Pattern.compile(".*\\[(\\{(.*)\\})\\].*").matcher(json);
		if(!matcher.matches()) throw new IllegalStateException("No match found in json");		
		String moviesArray = matcher.group(2);
		String [] movies = moviesArray.split("\\},\\{");
		return movies;
	}
	
	private static String parseMovieTitle(String movie){
		return parseAttribute(movie, MoviesAttributes.TITLE);
	}
	
	private static String parseAttribute(String movie, MoviesAttributes attribute) {
		return Stream.of(movie).map(e -> e.split("\",\"")[attribute.getIndex()])
				.map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"","")).findFirst().get();
	}
	
}




