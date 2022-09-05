package br.com.alura.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("API_IMDB_KEY"));
		HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
	
		String [] movies = parseJson(json);
		
		List<String> titles = parseMovieTitle(movies);
		
		
		List<String> urlImages = parseUrlImages(movies);
		List<String> ratings = parseRatings(movies);
		List<String> years = parseYears(movies);
		years.forEach(System.out::println);
	}
	
	private static List<String> parseYears(String[] moviesArray) {
		return parseAttribute(moviesArray, MoviesAttributes.YEAR);
	}

	private static List<String> parseRatings(String[] moviesArray) {
		return parseAttribute(moviesArray, MoviesAttributes.RATING);
	}

	private static List<String> parseUrlImages(String[] moviesArray) {
		return parseAttribute(moviesArray, MoviesAttributes.URLIMAGE);
	}

	private static String[] parseJson(String json) {
		Matcher matcher = Pattern.compile(".*\\[(\\{(.*)\\})\\].*").matcher(json);
		if(!matcher.matches()) throw new IllegalStateException("No match found in json");		
		String moviesArray = matcher.group(2);
		String [] movies = moviesArray.split("\\},\\{");
		return movies;
	}
	
	private static List<String> parseMovieTitle(String[]moviesArray){
		return parseAttribute(moviesArray, MoviesAttributes.TITLE);
	}
	
	private static List<String> parseAttribute(String[] moviesArray, MoviesAttributes attribute) {
		return Stream.of(moviesArray).map(e -> e.split("\",\"")[attribute.getIndex()])
				.map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"","")).collect(Collectors.toList());
	}
	
}




