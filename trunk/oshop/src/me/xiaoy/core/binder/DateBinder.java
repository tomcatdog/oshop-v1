package me.xiaoy.core.binder;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class DateBinder implements WebBindingInitializer {
	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest webRequest) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));

		binder.registerCustomEditor(Date.class, new DateTimeEditor());  
	}

}
