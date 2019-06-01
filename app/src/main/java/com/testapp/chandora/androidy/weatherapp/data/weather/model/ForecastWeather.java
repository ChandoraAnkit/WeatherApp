package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import androidx.room.Embedded;
import androidx.room.TypeConverters;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.testapp.chandora.androidy.weatherapp.data.weather.typeConverter.ListItemTypeConverter;

public class ForecastWeather{

	@Embedded
	@SerializedName("city")
	private City city;


	@SerializedName("cnt")
	private int cnt;

	@SerializedName("cod")
	private String responseCode;

	@SerializedName("message")
	private double message;

	@TypeConverters(ListItemTypeConverter.class)
	@SerializedName("list")
	private List<ListItem> list;

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setCnt(int cnt){
		this.cnt = cnt;
	}

	public int getCnt(){
		return cnt;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setMessage(double message){
		this.message = message;
	}

	public double getMessage(){
		return message;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"ForecastWeather{" + 
			"city = '" + city + '\'' + 
			",cnt = '" + cnt + '\'' + 
			",cod = '" + responseCode + '\'' +
			",message = '" + message + '\'' + 
			",list = '" + list + '\'' + 
			"}";
		}

}