package applet;

import java.util.Scanner;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import engine.EngineLog;
import engine.common.Order;
import engine.common.OrderType;

import applet.connection.NettyClientChannelConnector;
import applet.engine.ClientEngine;


public class Test {

	public static void main(String[] args) {
		EngineLog.CLIENT.info("Initiate anonymous connection");
		ClientEngine.test.initConnection();
		System.err.println("FIN");
	}
}
