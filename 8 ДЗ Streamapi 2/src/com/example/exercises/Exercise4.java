package com.example.exercises;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise4 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the highest populated capital city
		List<City> res = new ArrayList<>();
		Map<Object,Object> Capitals = countryDao.findAllCountries().stream()
				.collect(Collectors.toMap(Country::getCode, Country::getCapital));
		Capitals.forEach((code, cap) -> { if (cityDao.findCityById((Integer) cap) != null) { res.add(cityDao.findCityById((Integer) cap));}});
		System.out.println(res.stream().max(Comparator.comparingInt(City::getPopulation)).get());

	}

}
