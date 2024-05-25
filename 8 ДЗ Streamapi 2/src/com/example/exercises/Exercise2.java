package com.example.exercises;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;
import com.example.domain.Director;
import com.example.service.MovieService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise2 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the most populated city of each continent
		Map<String, List<Country>> ContinentCountry = countryDao.findAllCountries().stream()
				.collect(Collectors.groupingBy(Country::getContinent));

		Map<String, String> res = new HashMap<>();

		ContinentCountry.forEach((continent, countries) -> {
			City noSuch= new City("Города отсутствуют");
			res.put(continent,  countries.stream()
							.flatMap(c -> c.getCities().stream())
											.max(Comparator.comparingInt(City::getPopulation)).orElse(noSuch).getName());
				});

		System.out.println(res);

	}

}