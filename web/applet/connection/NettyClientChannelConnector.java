package applet.connection;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import common.adreamzone.client.EngineClientLog;

public class NettyClientChannelConnector {
	//TODO implements some config file to set connection properties 
	private static String HOSTNAME = "localhost";
	private int PORT = 7777;
	
	
	private ClientBootstrap bootstrap;
	private String host;
	private int port;
	
	public NettyClientChannelConnector() {
		// TODO Auto-generated constructor stub
		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		this.host = HOSTNAME;
		this.port = PORT;
	}
	
	public NettyClientChannelConnector(String hostname, int port) {
		// TODO Auto-generated constructor stub
		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		this.host = hostname;
		this.port = port;
	}
	
	public ChannelFuture init()
	{
		EngineClientLog.CLIENT.fine("Initializing connection on " + host + ":" + port);
		bootstrap.setPipelineFactory(new NettyClientChannelPipelineFactory());
		return bootstrap.connect(new InetSocketAddress( host, port));
	}
}
