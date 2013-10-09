package me.xiaoy.core.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * 处理对象与json的转化，主要为了转化的个性化
 * 
 * @author 
 * 
 */
public class JsonUtil {

	private static final Gson defaultGson = regDefTypeAda(new GsonBuilder())

	.setDateFormat(DateFormat.LONG).setPrettyPrinting().create();

	private static final GsonBuilder regDefTypeAda(GsonBuilder gb) {
		gb.registerTypeAdapter(Date.class, new UtilDateSerializer());

		gb.registerTypeAdapter(Calendar.class, new UtilCalendarSerializer());

		gb.registerTypeAdapter(GregorianCalendar.class, new UtilCalendarSerializer());
		return gb;
	}
	
	public static final String toJson(Object bean) {
		return defaultGson.toJson(bean);
	}
	
	private static class UtilDateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

		public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}
		public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
			return new Date(element.getAsJsonPrimitive().getAsLong());
		}

	}
	private static class UtilCalendarSerializer implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
		public JsonElement serialize(Calendar cal, Type type, JsonSerializationContext context) {
			return new JsonPrimitive(Long.valueOf(cal.getTimeInMillis()));
		}
		public Calendar deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(element.getAsJsonPrimitive().getAsLong());
			return cal;
		}
	}

}

