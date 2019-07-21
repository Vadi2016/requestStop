package ru.requeststop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс временной точки расписания маршрута
 * 
 * @author Валерий Лиховских
 *
 */
public class RoutePointSchedule {

	/**
	 * Время прохождения точки маршрута, в милисекундах
	 */
	private long time;

	private String stime;

	private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	/**
	 * Точка маршрута
	 */
	private RoutePoint point;
	
	public RoutePointSchedule(long time, RoutePoint point) {
		super();
		this.time = time;
		this.point = point;
		stime = format.format(new Date(time));
	}

	public long getTime() {
		return time;
		//return format.format(new Date(time));
	}
	
	public String getStime() {
		return stime;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public RoutePoint getPoint() {
		return point;
	}
	
	public void setPoint(RoutePoint point) {
		this.point = point;
	}
	
	
}
