package com.example.exercises;

import com.example.domain.Director;
import com.example.domain.Genre;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise3 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the number of genres of each director's movies List<Movie, List<Director>>
		Map <Object, Object> resultList = movieService.findAllMovies().stream()
				.flatMap(movie -> movie.getDirectors().stream().map(director -> Arrays.asList(director, movie.getGenres().size())))
						.collect(Collectors.toMap(movie->movie.get(0), count->count.get(1), (old, neww) -> ((Integer) neww + (Integer)old)));
		System.out.println(resultList);


	}
}