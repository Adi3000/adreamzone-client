package applet;

import java.util.Scanner;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.adreamzone.common.engine.ClientEngine;
import com.adreamzone.common.engine.EngineClientLog;
import com.adreamzone.common.engine.EngineLog;
import com.adreamzone.common.server.connection.NettyClientChannelConnector;
import com.adreamzone.common.Order;
import com.adreamzone.common.OrderType;



public class Test {

	public static void main(String[] args) {
		EngineClientLog.CLIENT.info("Initiate anonymous connection");
		ClientEngine.test.initConnection();
		System.err.println("FIN");
	}
}
