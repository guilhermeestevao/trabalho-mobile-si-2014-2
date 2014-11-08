package com.ufc.mobile.quest1.model;

import java.util.Arrays;

public class Location {

	private String address;
	private String cc;
	private String city;
	private String country;
	private String[] formattedAddress;
	private Double lat;
	private Double lng;
	private String crossStreet;
	private String postalCode;
	private String state;
	
	public Location() {
	}

	public Location(String address, String cc, String city, String country,
			String[] formattedAddress, Double lat, Double lng,
			String postalCode, String state, String crossStreet) {
		super();
		this.address = address;
		this.cc = cc;
		this.city = city;
		this.country = country;
		this.formattedAddress = formattedAddress;
		this.lat = lat;
		this.lng = lng;
		this.postalCode = postalCode;
		this.state = state;
		this.crossStreet = crossStreet;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String[] getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String[] formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCrossStreet() {
		return crossStreet;
	}

	public void setCrossStreet(String crossStreet) {
		this.crossStreet = crossStreet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((crossStreet == null) ? 0 : crossStreet.hashCode());
		result = prime * result + Arrays.hashCode(formattedAddress);
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (crossStreet == null) {
			if (other.crossStreet != null)
				return false;
		} else if (!crossStreet.equals(other.crossStreet))
			return false;
		if (!Arrays.equals(formattedAddress, other.formattedAddress))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", cc=" + cc + ", city=" + city
				+ ", country=" + country + ", formattedAddress="
				+ Arrays.toString(formattedAddress) + ", lat=" + lat + ", lng="
				+ lng + ", crossStreet=" + crossStreet + ", postalCode="
				+ postalCode + ", state=" + state + "]";
	}
	
}
