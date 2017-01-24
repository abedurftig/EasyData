package org.easydata.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EasyLogger {

	private static final Logger EASY_LOGGER = LogManager.getLogger("EasyLogger");
	
	public static Logger getEasyLogger() {
		return EASY_LOGGER;
	}
	
}
