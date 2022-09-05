package br.com.alura.java;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import br.com.alura.java.model.Movie;

public class HtmlGenerator {

	private final String template = """
				<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
				<h4 class=\"card-header\">%s</h4>
				<div class=\"card-body\">
					<img class=\"card-img\" src=\"%s\" alt=\"%s\">
					<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
				</div>
			</div>
			""";

	private final String head = """
			<head>
				<meta charset=\"utf-8\">
				<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
				<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
				integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
			</head>
				""";

	private Writer writer;

	public HtmlGenerator(Writer writer) {
		this.writer = writer;
	}

	public void generate(List<Movie> movies) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(head);
		sb.append("<body>");
		for (Movie movie : movies) {
			String card = String.format(template, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear());
			sb.append(card);
		}
		sb.append("</body>");
		sb.append("</html>");

		try {
			System.out.println(sb);
			this.writer.write(new String(sb));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
