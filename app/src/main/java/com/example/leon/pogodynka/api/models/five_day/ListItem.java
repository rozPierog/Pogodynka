package com.example.leon.pogodynka.api.models.five_day;

import java.util.List;
import javax.annotation.Generated;

import com.example.leon.pogodynka.api.models.Clouds;
import com.example.leon.pogodynka.api.models.Main;
import com.example.leon.pogodynka.api.models.Sys;
import com.example.leon.pogodynka.api.models.WeatherItem;
import com.example.leon.pogodynka.api.models.Wind;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ListItem{

	@SerializedName("dt")
	private int dt;

	@SerializedName("dt_txt")
	private String dtTxt;

	@SerializedName("snow")
	private Snow snow;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("main")
	private Main main;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("sys")
	private Sys sys;

	@SerializedName("wind")
	private Wind wind;

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
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