/**
 * 
 */
package ru.requeststop;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.requeststop.model.ModelData;
import ru.requeststop.model.RoutePointSchedule;
import ru.requeststop.model.RouteTransport;
import ru.requeststop.model.RouteTransportSchedule;

/**
 * Класс реализаций функций для постороения маршрутов
 *  
 * @author Валерий Лиховских
 *
 */
public class FunctionRoute {

	/**
	 * Найти маршруты транспорта в указанном радиусе от начальной до конечной точки.
	 * 
	 * В этом методе сперва выбираются остановки общественного транспорта в указанном
	 * радиусе от начальной и конечной точек. Далее определяютс маршруты, проходящие 
	 * через эти остановки и выбираются только маршруты пересечения.
	 *  
	 * @param lang язык вывода маршрутов
	 * @param distance радиус поиска остановок транспорта от указанных точек
	 * @param firstLatitude широта начальной точки маршрута
	 * @param firstLongitude долгота начальной точки маршрута
	 * @param lastLatitude широта конечной точки маршрута
	 * @param lastLongitude долгота конечной точки маршрута
	 * @return массив найденных маршрутов или пустой массив, если маршруты не найдены
	 */
	public RouteTransport[] findRoute(String lang, int distance,
			                          BigDecimal firstLatitude, BigDecimal firstLongitude,
			                          BigDecimal lastLatitude, BigDecimal lastLongitude) {
		// В реализации проекта хакатона возвращаются эмулируемые данные	
		RouteTransport[] routes;
		routes = new RouteTransport[1];

		routes[0] = ModelData.route1;
		
		return routes;
	}
	
	/**
	 * Получить рассписание маршрута
	 * 
	 * @param lang язак возвращаемого результата
	 * @param mnemocode мнемокод маршрута, например: A1, T40
	 * @param start время запроса рассписания 
	 * 
	 * @return рассписание запрошенного маршрута или null, если маршрут не найден
	 */
	public RouteTransportSchedule findRoute(String lang, String mnemocode, long start) {
		RouteTransportSchedule result;
		
		RouteTransportSchedule data;
		switch (mnemocode) {
		case "A1":
			data = ModelData.routeSchedule1;
			break;
		default:
			return null;
		}
		// Условно считаем
		// начала движения - 06:00
		// окончание движения - 23:30
		// интервал движения - 15 мин
		
		GregorianCalendar calendar = new GregorianCalendar();
		GregorianCalendar request = new GregorianCalendar();
		request.setTimeInMillis(start);
		int hour = request.get(Calendar.HOUR_OF_DAY);
		int minute = request.get(Calendar.MINUTE);
		// дополнить до ближайшего интервала 
		int delta = minute % 15;
		if (delta > 0) calendar.set(Calendar.MINUTE, minute + 15 - delta);
		if (hour < 6) {
			calendar.set(Calendar.HOUR_OF_DAY, 6);
			calendar.set(Calendar.MINUTE, 0);
		} else {
			if (hour == 23 && minute > 30) {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 30);
			}
		}
		
		result = new RouteTransportSchedule(data.getMnemocode(),  data.getFirst(), data.getLast(), data.getTimer(), new RoutePointSchedule[data.getPoints().length]);
		for (int index = 0; index < 15; index++) {
			RoutePointSchedule row = data.getPoints()[index];
			result.getPoints()[index] = new RoutePointSchedule(calendar.getTimeInMillis() + row.getTime(), row.getPoint());
		}
		return result;
	}
	
	

}
