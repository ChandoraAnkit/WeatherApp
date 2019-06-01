package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import androidx.room.Embedded;
import androidx.room.TypeConverters;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("dt")
	private int dt;

	@Embedded
	@SerializedName("rain")
	private Rain rain;

	@SerializedName("dt_txt")
	private String dtTxt;

	@Embedded
	@SerializedName("snow")
	private Snow snow;

	@TypeConverters(WeatherItem.class)
	@SerializedName("weather")
	private List<WeatherItem> weather;

	@Embedded
	@SerializedName("main")
	private Main main;

	@Embedded
	@SerializedName("clouds")
	private Clouds clouds;

	@Embedded
	@SerializedName("sys")
	private Sys sys;

	@Embedded
	@SerializedName("wind")
	private Wind wind;

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
	}

	public void setRain(Rain rain){
		this.rain = rain;
	}

	public Rain getRain(){
		return rain;
	}

	public void setDtTxt(String dtTxt){
		this.dtTxt = dtTxt;
	}

	public String getDtTxt(){
		return dtTxt;
	}

	public void setSnow(Snow snow){
		this.snow = snow;
	}

	public Snow getSnow(){
		return snow;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setClouds(Clouds clouds){
		this.clouds = clouds;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	public void setWind(Wind wind){
		this.wind = wind;
	}

	public Wind getWind(){
		return wind;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"dt = '" + dt + '\'' + 
			",rain = '" + rain + '\'' + 
			",dt_txt = '" + dtTxt + '\'' + 
			",snow = '" + snow + '\'' + 
			",weather = '" + weather + '\'' + 
			",main = '" + main + '\'' + 
			",clouds = '" + clouds + '\'' + 
			",sys = '" + sys + '\'' + 
			",wind = '" + wind + '\'' + 
			"}";
		}
}