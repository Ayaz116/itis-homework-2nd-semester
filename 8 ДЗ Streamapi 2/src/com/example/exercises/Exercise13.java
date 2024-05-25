package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.Comparator;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise13 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the countries with the minimum and the maximum population
		System.out.println("Max: " + worldDao.findAllCountries().stream().max(Comparator.comparingInt(Country::getPopulation)).get() +
				'\n' + "Min: " + worldDao.findAllCountries().stream().min(Comparator.comparingInt(Country::getPopulation)).get());
	}

}
