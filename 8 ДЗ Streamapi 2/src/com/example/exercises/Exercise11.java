package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.City;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise11 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the minimum, the maximum and the average population of world countries
		System.out.println("Max: " + worldDao.findAllCountries().stream().max(Comparator.comparingInt(Country::getPopulation)).get().getPopulation() +
				'\n' + "Min: " + worldDao.findAllCountries().stream().min(Comparator.comparingInt(Country::getPopulation)).get().getPopulation() +
				'\n' + "Average: " +  String.format("%.0f", worldDao.findAllCountries().stream().mapToLong(Country::getPopulation).average().getAsDouble()));
	}

}
