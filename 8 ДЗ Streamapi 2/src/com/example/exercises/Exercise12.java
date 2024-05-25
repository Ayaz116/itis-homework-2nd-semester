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
public class Exercise12 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the minimum, the maximum and the average population of each continent.
		for(String coontinent: worldDao.getAllContinents()){
			System.out.println(coontinent + ": "+ '\n'+
					"Max: " + worldDao.findAllCountries().stream().filter(country -> country.getContinent().equals(coontinent)).max(Comparator.comparingInt(Country::getPopulation)).get().getPopulation() +
					'\n' + "Min: " + worldDao.findAllCountries().stream().filter(country -> country.getContinent().equals(coontinent)).min(Comparator.comparingInt(Country::getPopulation)).get().getPopulation() +
					'\n' + "Average: " +  String.format("%.0f", worldDao.findAllCountries().stream().filter(country -> country.getContinent().equals(coontinent)).mapToLong(Country::getPopulation).average().getAsDouble()));
		}
	}

}
