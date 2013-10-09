package me.xiaoy.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniqueException extends RuntimeException {

	private static final long serialVersionUID = -2951635573317382472L;
	
	private Logger logger = null;
	
	@SuppressWarnings("rawtypes")
	public UniqueException(Class clazz, String message) {
		super(message);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(message);
	}
	
	@SuppressWarnings("rawtypes")
	public UniqueException(Class clazz, Throwable throwable) {
		super(throwable);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(throwable.getMessage());
	}

}
