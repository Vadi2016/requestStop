/**
 * 
 */
package ru.requeststop;

import java.math.BigDecimal;

import ru.funsys.avalanche.AvalancheRemote;
import ru.requeststop.model.RouteTransport;

/**
 * @author Катя
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
			                          BigDecimal lastLatitude, BigDecimal lastLongitude) throws AvalancheRemote;


}
