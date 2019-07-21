/**
 * 
 */
package ru.requeststop.model;

import java.math.BigDecimal;

/**
 * Класс эмуляции требуемых выюорок данных. Данные, содержащиеся в этом классе, должны
 * грузиться из источника данных.
 * 
 * @author Валерий Лиховских
 *
 */
public class ModelData {

	public static RouteTransport route1;
	public static RouteTransportSchedule routeSchedule1;

	static {
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
		route1 = new RouteTransport("A1", "Университет имени Лобачевского", "Больница имени Семашко", 28, points);

		RoutePointSchedule[] pointsSchedules = new RoutePointSchedule[15];
		pointsSchedules[0] = new RoutePointSchedule(0, points[0]);
		pointsSchedules[1] = new RoutePointSchedule(120000, points[1]); // 2, 2
		pointsSchedules[2] = new RoutePointSchedule(240000, points[2]); // 2, 4
		pointsSchedules[3] = new RoutePointSchedule(360000, points[3]); // 2, 6
		pointsSchedules[4] = new RoutePointSchedule(480000, points[4]); // 2, 8
		pointsSchedules[5] = new RoutePointSchedule(600000, points[5]); // 2, 10
		pointsSchedules[6] = new RoutePointSchedule(720000, points[6]); // 2, 12
		pointsSchedules[7] = new RoutePointSchedule(840000, points[7]); // 2, 14
		pointsSchedules[8] = new RoutePointSchedule(960000, points[8]); // 2, 16
		pointsSchedules[9] = new RoutePointSchedule(1080000, points[9]); // 2, 18
		pointsSchedules[10] = new RoutePointSchedule(1200000, points[10]); // 2, 20
		pointsSchedules[11] = new RoutePointSchedule(13200000, points[11]); // 2, 22
		pointsSchedules[12] = new RoutePointSchedule(14400000, points[12]); // 2, 24
		pointsSchedules[13] = new RoutePointSchedule(15600000, points[13]); // 2, 26
		pointsSchedules[14] = new RoutePointSchedule(16800000, points[14]); // 2, 28
		routeSchedule1 = new RouteTransportSchedule("A1", "Университет имени Лобачевского", "Больница имени Семашко", 28, pointsSchedules);
		
	}

}
