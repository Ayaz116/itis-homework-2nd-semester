package com.example.exercises;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise15 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Group the countries by continent, and then sort the countries in each continent by number of cities in each country.
		Map<String, List<Country>> m = countryDao.findAllCountries().stream()
				.collect(Collectors.groupingBy(Country::getContinent));
		Map<String, List<Country>> res = new HashMap<>();
		m.forEach((con, cou) -> res.put(con, cou.stream().sorted(Comparator.comparingInt(country -> country.getCities().size())).collect(Collectors.toList())));
		System.out.println(res);
	}

}
