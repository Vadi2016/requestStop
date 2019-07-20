/**
 * 
 */
package ru.requeststop;

import java.math.BigDecimal;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.funsys.avalanche.Application;

/**
 * Класс реализации функций REST сервиса
 * 
 * @author Валерий Лиховских
 *
 */
@Path("/route")
public class ApplicationREST extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4406005218081523140L;
	
	private static final String UTF_8 = "utf8"; // NOI18N 
	
	private static final String ROUTE001E = "ROUTE001E"; // NOI18N 

	/**
	 * Адаптер доступа к функии поиска маршрутов
	 * Для инициализации этого поля в файле конфигурации должен быть 
	 * определен дочерний элкемент <b>{@code <adapter>}</b> с атрибутом <b>{@code name}</b>
	 * с установленным значением <b>{@code adapterRoute}</b>. 
	 * 
	 * <pre>
	 * &lt;adapter class="ru.requeststop.AdapterRoute" name="adapterRoute" uri="function-adapter" /&gt;	
	 * </pre> 
	 */
	private AdapterRoute adapterRoute;
	
	/**
	 * Выполнить поиск маршрутов
	 * 
	 * @param lang язык запроса, значение по умолчанию - ru
	 * @param distance радиус поиска остановок в метрах от указанных точек запроса,
	 *                 значение по умолчанию - 300. 
	 * @param firstLatitude широта начальной точки поиска маршрутов
	 * @param firstLongitude долгота начальной точки поиска маршрутов
	 * @param lastLatitude широта конечной точки поиска маршрутов
	 * @param lastLongitude долгота конечной точки поиска маршрутов
	 * 
	 * @return массив найденных маршрутов или пустой массив, если ни один маршрут не найден
	 */
	@GET
	@Path("/find")
	@Produces( "application/json" )
	public Response findStop(@QueryParam("lang") @DefaultValue("ru") String lang,
			                 @QueryParam("distance") @DefaultValue("300") int distance,
			                 @QueryParam("firstLatitude") BigDecimal firstLatitude,
			                 @QueryParam("firstLongitude") BigDecimal firstLongitude,
							 @QueryParam("lastLatitude") BigDecimal lastLatitude,
                             @QueryParam("lastLongitude") BigDecimal lastLongitude) {
    	try {
			return Response.ok(adapterRoute.findRoute(lang, distance, firstLatitude, firstLongitude, lastLatitude, lastLongitude)).type(MediaType.APPLICATION_JSON_TYPE.withCharset(UTF_8)).build();
		} catch (Throwable throwable) {
			// TODO: handle exception
			return RestExceptionMapper.newRestException(logger, throwable, ROUTE001E, lang);
		}
		
	}

}
