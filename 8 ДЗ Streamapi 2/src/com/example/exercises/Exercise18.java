package com.example.exercises;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise18 {
	private static final  MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the year where the maximum number of movie is available
		Map<Integer, List<Movie>> res = movieService.findAllMovies().stream()
				.collect(Collectors.groupingBy(Movie::getYear));
		System.out.println(res.values().stream().max(Comparator.comparingInt(List::size)).get().get(0).getYear());
	}

}
