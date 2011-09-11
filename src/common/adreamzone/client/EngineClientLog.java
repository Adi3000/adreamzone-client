package common.adreamzone.client;

import java.util.logging.Logger;

import com.adreamzone.common.engine.EngineLog;

public class EngineClientLog extends EngineLog {

	public static String CLIENT_LOGGER_NAME = "DreamZone-Client";
	private static final EngineLog ENGINE_CLIENT_LOG = new EngineClientLog(applet.engine.ClientEngine.class.getName(),true);
	public static final Logger CLIENT = ENGINE_CLIENT_LOG.getLogger();

	
	private EngineClientLog(String name, boolean displayOnConsole)
	{		
		super(name,displayOnConsole);
	}


}
