/**
 * 
 */
package ru.requeststop;

import java.math.BigDecimal;

import ru.funsys.avalanche.AvalancheRemote;
import ru.requeststop.model.RouteTransport;
import ru.requeststop.model.RouteTransportSchedule;

/**
 * @author Валерий Лиховских
 *
 */
public interface AdapterRoute {

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
			                          BigDecimal lastLatitude, BigDecimal lastLongitude) throws AvalancheRemote;


	/**
	 * 
	 * 
	 * @param lang язак возвращаемого результата
	 * @param mnemocode мнемокод маршрута, например: A1, T40
	 * @param start время запроса рассписания 
	 * 
	 * @return рассписание запрошенного маршрута или null, если маршрут не найден
	 */
	public RouteTransportSchedule findRoute(String lang, String mnemocode, long start) throws AvalancheRemote;
	
}
