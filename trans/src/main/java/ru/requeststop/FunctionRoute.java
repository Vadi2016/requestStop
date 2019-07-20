/**
 * 
 */
package ru.requeststop;

import java.math.BigDecimal;

import ru.requeststop.model.RoutePoint;
import ru.requeststop.model.RouteTransport;

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
	 * @param lang
	 * @param distance
	 * @param firstLatitude
	 * @param firstLongitude
	 * @param lastLatitude
	 * @param lastLongitude
	 * @return массив найденных маршрутов или пустой массив, если маршруты не найдены
	 */
	public RouteTransport[] findRoute(String lang, int distance,
			                          BigDecimal firstLatitude, BigDecimal firstLongitude,
			                          BigDecimal lastLatitude, BigDecimal lastLongitude) {
		// В реализации проекта хакатона возвращаются фиксированные данные	
		RouteTransport[] routes;
		
		RoutePoint[] points = new RoutePoint[15];
		points[0] = new RoutePoint("Университет имени Лобачевского", new BigDecimal("56.298343"), new BigDecimal("43.983657"));
		points[1] = new RoutePoint("улица студенческая", new BigDecimal("56.304374"), new BigDecimal("43.984215"));
		points[2] = new RoutePoint("средной рынок", new BigDecimal("56.308090"), new BigDecimal("43.988941"));
		points[3] = new RoutePoint("студеная улица", new BigDecimal("56.310249"), new BigDecimal("43.999161"));
		points[4] = new RoutePoint("ашхабадская улица", new BigDecimal("56.312658"), new BigDecimal("44.006381"));
		points[5] = new RoutePoint("оперный театр", new BigDecimal("56.316081"), new BigDecimal("44.016649"));
		points[6] = new RoutePoint("улица полтавская", new BigDecimal("56.318120"), new BigDecimal("44.023622"));
		points[7] = new RoutePoint("улица белинского", new BigDecimal("56.319145"), new BigDecimal("44.027721"));
		points[8] = new RoutePoint("Сенная площадь", new BigDecimal("56.322007"), new BigDecimal("44.036883"));
		points[9] = new RoutePoint("донецкая улица", new BigDecimal("56.320683"), new BigDecimal("44.048127"));
		points[10] = new RoutePoint("фруктовая улица", new BigDecimal("56.319801"), new BigDecimal("44.054511"));
		points[11] = new RoutePoint("Учебный комбинат ЗАО Маяк", new BigDecimal("56.318000"), new BigDecimal("44.062622"));
		points[12] = new RoutePoint("Медвежья долина", new BigDecimal("56.315323"), new BigDecimal("44.069756"));
		points[13] = new RoutePoint("Улица Родионова", new BigDecimal("56.310684"), new BigDecimal("44.074498"));
		points[14] = new RoutePoint("Больница имени Семашко", new BigDecimal("56.309414"), new BigDecimal("44.076086"));
		RouteTransport route = new RouteTransport("A1", "Университет имени Лобачевского", "Больница имени Семашко", 25, points);

		routes = new RouteTransport[1];
		routes[0] = route;
		
		return routes;
	}
	
}
