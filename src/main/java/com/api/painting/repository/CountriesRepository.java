/**
 * 
 */
package com.api.painting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.painting.model.Countries;

/**
 * @author mindfire
 *
 */
public interface CountriesRepository extends JpaRepository<Countries, Long> {

}
