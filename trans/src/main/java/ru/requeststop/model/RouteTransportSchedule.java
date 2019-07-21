/**
 * 
 */
package ru.requeststop.model;

/**
 * Класс расписания маршрута общественного транспорта. В зависимости от параметров запроса
 * этот класс может содержать либо весть маршрут общественного транспорта, либо
 * только от остановки посадки пользоваля до остановки его выхода
 * 
 * @author Валерий Лиховских
 *
 */
public class RouteTransportSchedule {

	private String mnemocode;
	private String first;
	private String last;
	private int timer;
	
	private RoutePointSchedule[] points;

	public RouteTransportSchedule(String mnemocode, String first, String last, int timer, RoutePointSchedule[] points) {
		super();
		this.mnemocode = mnemocode;
		this.first = first;
		this.last = last;
		this.timer = timer;
		this.points = points;
	}

	public String getMnemocode() {
		return mnemocode;
	}

	public void setMnemocode(String mnemocode) {
		this.mnemocode = mnemocode;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public RoutePointSchedule[] getPoints() {
		return points;
	}

	public void setPoints(RoutePointSchedule[] points) {
		this.points = points;
	}
	
	
}
