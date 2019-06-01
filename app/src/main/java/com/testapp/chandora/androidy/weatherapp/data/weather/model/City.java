package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class City{

	@SerializedName("country")
	private String country;

	@Embedded
	@SerializedName("coord")
	private Coord coord;

	@SerializedName("name")
	private String cityName;

	@SerializedName("id")
	private int cityId;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCoord(Coord coord){
		this.coord = coord;
	}

	public Coord getCoord(){
		return coord;
	}

	public void setCityName(String name){
		this.cityName = name;
	}

	public String getCityName(){
		return cityName;
	}

	public void setCityId(int id){
		this.cityId = id;
	}

	public int getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"City{" + 
			"country = '" + country + '\'' + 
			",coord = '" + coord + '\'' + 
			",name = '" + cityName + '\'' +
			",id = '" + cityId + '\'' +
			"}";
		}
}