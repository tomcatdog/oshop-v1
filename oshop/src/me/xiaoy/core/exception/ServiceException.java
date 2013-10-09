package me.xiaoy.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception{

private static final long serialVersionUID = -71434260745841874L;
	
	private Logger logger = null;

	@SuppressWarnings("rawtypes")
	public ServiceException(Class clazz, String message) {
		super(message);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(message);
	}
	
	@SuppressWarnings("rawtypes")
	public ServiceException(Class clazz, Throwable throwable) {
		super(throwable);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(throwable.getMessage());
	}
	
}
