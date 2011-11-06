package com.adreamzone.common.server.connection;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.adreamzone.common.Order;
import com.adreamzone.common.engine.ClientEngine;
import com.adreamzone.common.engine.EngineClientLog;

public class NettyClientChannelServerHandler extends SimpleChannelUpstreamHandler {
	private static ClientEngine engine =  ClientEngine.test;
	//TODO Make a Thread for waiting queue stack and treatment in multiple phases
	// to wait for positive answer and changed variables (like authentication)

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		
		Order m = (Order)e.getMessage();
		//Execute treatment and wait for answer
		if(!m.isAsynchronous()){
			EngineClientLog.CLIENT.fine("["+e.getChannel().getId()+"]\tAbout to compute : " + m);
			Order response = engine.answerFor(m);
			EngineClientLog.CLIENT.fine("["+e.getChannel().getId()+"]\tResponse computed : "+ response);
			if(response != null){
				e.getChannel().write(response);
			}
		}
		//Treatment will be executed in background
		else{
			//TODO not implemented yet
			EngineClientLog.CLIENT.warning("Async message : " + m);
		}
		if(m.hasOrderSequence())
		{
			EngineClientLog.CLIENT.finer("Have to wake the thread "+ m.getOrderSequence());
			engine.notifyThread(m.getOrderSequence());
		}
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
	{
		EngineClientLog.CLIENT.severe("ServerHandler broken : " + e.getCause().getMessage());
		e.getCause().printStackTrace();
	}

}
