package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise17 {
	private static final WorldDao worldDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the minimum, the maximum, the average, and the standard deviation of GNP values.
		System.out.println("Минимальное GNP: " + worldDao.findAllCountries().stream().min(Comparator.comparingDouble(Country::getGnp)).get().getGnp());
		System.out.println("Максимальное GNP: " + worldDao.findAllCountries().stream().max(Comparator.comparingDouble(Country::getGnp)).get().getGnp());
		System.out.println("Среднее GNP: " + String.format("%.0f", worldDao.findAllCountries().stream().mapToDouble(Country::getGnp).average().getAsDouble()));

		System.out.println("Стандартное отклонение GNP: " + Math.sqrt((worldDao.findAllCountries().stream().mapToDouble(Country -> Country.getGnp()* Country.getGnp()).sum()) / worldDao.findAllCountries().size() - Math.pow( worldDao.findAllCountries().stream().mapToDouble(Country::getGnp).average().getAsDouble(), 2)));
	}

}
