package ru.requeststop;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import ru.funsys.util.ResourceBundleXML;


/**
 * Класс локализации сообщений приложения МП
 * 
 * @author Валерий Лиховских
 *
 */
public class Messages {

	private static ResourceBundle EN = ResourceBundleXML.getBundle("ru.requeststop.LocaleStrings", Locale.ENGLISH, new ResourceBundleXML.Control());
	private static ResourceBundle RU = ResourceBundleXML.getBundle("ru.requeststop.LocaleStrings", new Locale("ru", "RU"), new ResourceBundleXML.Control());

	private static final String SYS0001E = "SYS0001E"; // NOI18N
	
	/**
	 * Получить локализованное сообщение
	 * 
	 * @param key код сообщения
	 * @param lang код языка: "en", "ru"
	 * @param args аргументы подстановки значений в сообщение локализации 
	 *  
	 * @return локализованное сообщение 
	 */
	public static String getMessage(String key, String lang, Object... args) {
		String message;
		Object[] values = args;
		switch (lang) {
		case "ru":
			try {
				message = RU.getString(key);
			} catch (Exception e) {
				message = RU.getString(SYS0001E);
				values = new Object[] {key};
			}
			break;
		default:
			try {
				message = EN.getString(key);
			} catch (Exception e) {
				message = EN.getString(SYS0001E);
				values = new Object[] {key};
			}
			break;
		}
		if (values == null) return message;
		return MessageFormat.format(message, values);
	}
	
}
