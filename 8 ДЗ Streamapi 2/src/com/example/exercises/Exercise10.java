package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise10 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the richest country of each continent with respect to their GNP (Gross National Product) values.
		Map<String, Country> res = new HashMap<>();
		Map<String, List<Country>> Capitals = worldDao.findAllCountries().stream()
				.collect(Collectors.groupingBy(Country::getContinent));
		Capitals.forEach((con, cou) -> res.put(con, cou.stream().max(Comparator.comparingDouble(Country::getGnp)).get()));
		System.out.println(res);
	}

}
