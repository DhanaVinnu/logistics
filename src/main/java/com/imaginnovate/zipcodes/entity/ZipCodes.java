package com.imaginnovate.zipcodes.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.opencsv.bean.CsvBindByName;

@Document(collection = "zip_codes")
public class ZipCodes {

	@Id
	private String _id;
	
	@Field(name = "zip_code")
	@CsvBindByName(column = "Zipcode")
	private String zipCode;
	
	@Field(name = "zip_code_type")
	@CsvBindByName(column = "ZipCodeType")
	private String zipCodeType;
	
	@Field(name = "city")
	@CsvBindByName(column = "City")
	private String city;
	
	@Field(name = "State")
	@CsvBindByName(column = "State")
	private String State;
	
	@Field(name = "location_type")
	@CsvBindByName(column = "LocationType")
	private String locationType;
	
	@Field(name = "lat")
	@CsvBindByName(column = "lat")
	private double lat;
	
	@Field(name = "lng")
	@CsvBindByName(column = "lng")
	private double lng;
	
	@Field(name = "location")
	@CsvBindByName(column = "Location")
	private String location;
	
	@Field(name = "decommisioned")
	@CsvBindByName(column = "decommisioned")
	private String decommisioned;
	
	@Field(name = "tax_returns_filed")
	@CsvBindByName(column = "TaxReturnsFiled")
	private String taxReturnsFiled;
	
	@Field(name = "total_wages")
	@CsvBindByName(column = "TotalWages")
	private String total_wages;
	
	@Field(name = "estimated_population")
	@CsvBindByName(column = "EstimatedPopulation")
	private String estimatedPopulation;

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCodeType() {
		return zipCodeType;
	}

	public void setZipCodeType(String zipCodeType) {
		this.zipCodeType = zipCodeType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDecommisioned() {
		return decommisioned;
	}

	public void setDecommisioned(String decommisioned) {
		this.decommisioned = decommisioned;
	}

	public String getTaxReturnsFiled() {
		return taxReturnsFiled;
	}

	public void setTaxReturnsFiled(String taxReturnsFiled) {
		this.taxReturnsFiled = taxReturnsFiled;
	}

	public String getTotal_wages() {
		return total_wages;
	}

	public void setTotal_wages(String total_wages) {
		this.total_wages = total_wages;
	}

	public String getEstimatedPopulation() {
		return estimatedPopulation;
	}

	public void setEstimatedPopulation(String estimatedPopulation) {
		this.estimatedPopulation = estimatedPopulation;
	}

	
}
