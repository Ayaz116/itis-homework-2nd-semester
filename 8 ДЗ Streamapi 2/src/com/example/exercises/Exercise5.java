package com.example.exercises;

import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise5 {
	private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
	private static final CityDao cityDao = InMemoryWorldDao.getInstance();

	public static void main(String[] args) {
		// Find the highest populated capital city of each continent
		Map<Object, City> res = new HashMap<>();
		Map<Object,Object> Capitals = countryDao.findAllCountries().stream()
				.collect(Collectors.toMap(Country::getCode, Country::getCapital));
		Capitals.forEach((code, cap) -> { if (cityDao.findCityById((Integer) cap) != null) { res.put(countryDao.findCountryByCode(code.toString()), cityDao.findCityById((Integer) cap));}});
		System.out.println(res);
	}

}