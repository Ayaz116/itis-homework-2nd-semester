package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise9 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Sort the countries by their population densities in descending order ignoring
		// zero population countries

		List<Country> res = worldDao.findAllCountries().stream()
				.filter(country -> country.getPopulation()!=0)
				.sorted(Comparator.comparingInt(x-> -(int) (x.getSurfaceArea()/x.getPopulation()))).toList();
		System.out.println(res);
	}

}
