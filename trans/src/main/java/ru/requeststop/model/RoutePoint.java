/**
 * 
 */
package ru.requeststop.model;

import java.math.BigDecimal;

/**
 * Точка маршрута
 * 
 * @author Валерий Лиховских
 *
 */
public class RoutePoint {

	private BigDecimal latitude; // широта 
	private BigDecimal longitude; // долгота
	private String name; // название остановки или null, если точка не является остановкой
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	
	public RoutePoint(String name, BigDecimal latitude, BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
