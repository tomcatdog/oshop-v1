package me.xiaoy.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = -8801718407102159973L;

	private Logger logger = null;

	@SuppressWarnings("rawtypes")
	public DaoException(Class clazz, String message) {
		super(message);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(message);
	}
	
	@SuppressWarnings("rawtypes")
	public DaoException(Class clazz, Throwable throwable) {
		super(throwable);
		logger = LoggerFactory.getLogger(clazz);
		logger.error(throwable.getMessage());
	}
	
}
