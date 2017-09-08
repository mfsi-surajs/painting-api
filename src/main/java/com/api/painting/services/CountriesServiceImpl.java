package com.api.painting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.painting.model.Countries;
import com.api.painting.repository.CountriesRepository;

@Service
public class CountriesServiceImpl implements CountriesService {

	@Autowired
	private CountriesRepository countriesRepository;
	
	/* (non-Javadoc)
	 * @see com.api.painting.services.CountriesService#getAllCountries()
	 */
	@Override
	public List<Countries> getAllCountries() {
		return countriesRepository.findAll();
	}
}
