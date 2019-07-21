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
import ru.requeststop.model.RouteTransportSchedule;

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
	private static final String ROUTE002E = "ROUTE002E"; // NOI18N 
	private static final String ROUTE003E = "ROUTE003E"; // NOI18N 

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
	 * <p> 
	 * Пример возвращаемых данных
	 * 
	 * <pre>
	 * [{"mnemocode":"A1","first":"Университет имени Лобачевского","last":"Больница имени Семашко","timer":25,
	 * "points":[{"latitude":56.298343,"longitude":43.983657,"name":"Университет имени Лобачевского"},
	 * {"latitude":56.304374,"longitude":43.984215,"name":"улица студенческая"},
	 * {"latitude":56.308090,"longitude":43.988941,"name":"средной рынок"},
	 * {"latitude":56.310249,"longitude":43.999161,"name":"студеная улица"},
	 * {"latitude":56.312658,"longitude":44.006381,"name":"ашхабадская улица"},
	 * {"latitude":56.316081,"longitude":44.016649,"name":"оперный театр"},
	 * {"latitude":56.318120,"longitude":44.023622,"name":"улица полтавская"},
	 * {"latitude":56.319145,"longitude":44.027721,"name":"улица белинского"},
	 * {"latitude":56.322007,"longitude":44.036883,"name":"Сенная площадь"},
	 * {"latitude":56.320683,"longitude":44.048127,"name":"донецкая улица"},
	 * {"latitude":56.319801,"longitude":44.054511,"name":"фруктовая улица"},
	 * {"latitude":56.318000,"longitude":44.062622,"name":"Учебный комбинат ЗАО Маяк"},
	 * {"latitude":56.315323,"longitude":44.069756,"name":"Медвежья долина"},
	 * {"latitude":56.310684,"longitude":44.074498,"name":"Улица Родионова"},
	 * {"latitude":56.309414,"longitude":44.076086,"name":"Больница имени Семашко"}]}]
	 * </pre>
	 * 
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

	/**
	 * Запроса рассписания маршрута
	 * 
	 * @param lang язак возвращаемого результата
	 * @param mnemocode мнемокод маршрута, например: A1, T40
	 * @param start время запроса рассписания 
	 * 
	 * @return рассписание запрошенного маршрута или сообщение об ошибке
	 * 
	 * Пример ошибки:
	 * <pre>
	 * {"message":"Рассписание маршрута не найдена.","code":"ROUTE002E","cause":null}
	 * </pre>
	 */
	@GET
	@Path("/schedule")
	@Produces( "application/json" )
	public Response findRoute(@QueryParam("lang") @DefaultValue("ru") String lang,
                              @QueryParam("mnemocode") String mnemocode,
                              @QueryParam("time") long time) {
		if (time == 0) time = System.currentTimeMillis();
    	try {
    		RouteTransportSchedule schedule = adapterRoute.findRoute(lang, mnemocode, time);
			if (schedule != null) return Response.ok(schedule).type(MediaType.APPLICATION_JSON_TYPE.withCharset(UTF_8)).build();
			else return RestExceptionMapper.newRestException(logger, ROUTE002E, lang);
		} catch (Throwable throwable) {
			// TODO: handle exception
			return RestExceptionMapper.newRestException(logger, throwable, ROUTE003E, lang);
		}
	}

}
