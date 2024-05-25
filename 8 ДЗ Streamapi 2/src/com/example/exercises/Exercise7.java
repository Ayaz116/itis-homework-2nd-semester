package com.example.exercises;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.List;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise7 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the list of movies having the genres "Drama" and "Comedy" only
		List<Movie> res = movieService.findAllMovies().stream()
				.filter(movie -> movie.getGenres().size()==2 && ((movie.getGenres().get(0) == movieService.findGenreByName("Drama")&&
						movie.getGenres().get(1) == movieService.findGenreByName("Comedy")) ||
						(movie.getGenres().get(1) == movieService.findGenreByName("Drama") &&
						movie.getGenres().get(0) == movieService.findGenreByName("Comedy")))).toList();
		System.out.println(res);
	}

}
