package com.adreamzone.common.engine;

import java.util.logging.Logger;

import com.adreamzone.common.engine.EngineLog;

public class EngineClientLog extends EngineLog {

	public static String CLIENT_LOGGER_NAME = "DreamZone-Client";
	private static final EngineLog ENGINE_CLIENT_LOG = new EngineClientLog(com.adreamzone.common.engine.ClientEngine.class.getName(),true);
	public static final Logger CLIENT = ENGINE_CLIENT_LOG.getLogger();

	
	private EngineClientLog(String name, boolean displayOnConsole)
	{		
		super(name,displayOnConsole);
	}


}
