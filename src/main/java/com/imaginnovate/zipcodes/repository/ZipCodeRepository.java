package com.imaginnovate.zipcodes.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.imaginnovate.zipcodes.entity.ZipCodes;
import com.imaginnovate.zipcodes.model.ZipCodeResponse;

@Repository
public interface ZipCodeRepository extends MongoRepository<ZipCodes, String>{

	//@Query("select count(*) from ZipCodes where lat>?0 and lat<?1 and lng>?2 and lng<?3")
	@Query("{ 'lat' : { $gt: ?0, $lt: ?1 }, 'lng' : { $gt: ?2, $lt: ?3 } }")
	List<ZipCodes> getZipCodes(double latMin, double latMax, double lngMin, double lngMax);
	
	@Query("{ 'lat' : { $gt: ?0, $lt: ?1 }, 'lng' : { $gt: ?2, $lt: ?3 } }")
	List<ZipCodeResponse> findZipCodes(double latMin, double latMax, double lngMin, double lngMax);
}
