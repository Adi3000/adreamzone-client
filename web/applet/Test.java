package applet;

import java.util.Scanner;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.adreamzone.common.engine.EngineLog;
import com.adreamzone.common.Order;
import com.adreamzone.common.OrderType;
import common.adreamzone.client.EngineClientLog;

import applet.connection.NettyClientChannelConnector;
import applet.engine.ClientEngine;


public class Test {

	public static void main(String[] args) {
		EngineClientLog.CLIENT.info("Initiate anonymous connection");
		ClientEngine.test.initConnection();
		System.err.println("FIN");
	}
}
