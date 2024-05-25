package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.City;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise16 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the cities with the minimum and the maximum population in countries.
		for (Country c: worldDao.findAllCountries().stream().filter(country -> !country.getCities().isEmpty()).toList()){
			System.out.println(c.getName() + ": "+'\n'+"Max: " + c.getCities().stream().max(Comparator.comparingInt(City::getPopulation)).get() +
					'\n' + "Min: " + c.getCities().stream().min(Comparator.comparingInt(City::getPopulation)).get()+'\n');
		}
	}
}
