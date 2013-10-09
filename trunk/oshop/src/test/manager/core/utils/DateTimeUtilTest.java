package test.manager.core.utils;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.junit.Test;

import org.apache.commons.logging.LogFactory;

public class DateTimeUtilTest {
	
	private static final Log log = LogFactory.getLog(DateTimeUtilTest.class);
	
	@Test
	public void testTimeMinus() {
		Calendar c = Calendar.getInstance();
		log.debug("c.getTimeInMillis()="+c.getTimeInMillis());
		log.debug("c.getTime().getTime()="+c.getTime().getTime());
		
		c.add(Calendar.HOUR_OF_DAY, 1);
		log.debug("c.getTimeInMillis()="+c.getTimeInMillis());
		log.debug("c.getTime().getTime()="+c.getTime().getTime());
	}

}
