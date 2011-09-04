package applet.engine;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.HashMap;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import applet.connection.NettyClientChannelConnector;
import applet.engine.common.ClientThread;
import engine.Engine;
import engine.EngineLog;
import engine.common.Order;
import engine.common.OrderType;
import engine.common.utils.optimizer.CommonValues;
import engine.security.Security;

public class ClientEngine extends Engine {
	private Channel chan;
	private boolean isConnected;
	private int uuidCodeSession;
	private HashMap<Integer,ClientThread> queue;
	public static final ClientEngine test = new ClientEngine();
	
	private ClientEngine()
	{
		super();
		this.chan = null;
		this.isConnected = false;
		this.uuidCodeSession = CommonValues.ERROR_OR_INFINITE;
		this.queue = new HashMap<Integer, ClientThread>();
	}
	
	//TODO Use a static variable with great secure and only one read possible for password
	//TODO AUse a init or setter for the password must use a unique key to encrypt it
	//TODO Use a setter or init for the User, the getter will be available as long as the process is running
	/**
	 * Init a connection and Auth process.
	 * @return
	 */
	public boolean  initConnection()
	{
		ChannelFuture futurechan = new NettyClientChannelConnector().init();
		Order connectionOrder = new Order(OrderType.CONNECT, this.hashCode());
		ClientThread process = new ClientThread(connectionOrder,this);
		connectionOrder.setOrderSequence(process.getThreadSequence());
		try {
			if(futurechan.await(10000))
			{
				this.chan = futurechan.getChannel();
				EngineLog.CLIENT.fine("Connection Successful, waiting for session...");
				this.chan.write(connectionOrder);
				process.start();
				queue.put(connectionOrder.getOrderSequence(),process);
				return true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Order answerFor(Order o){
		EngineLog.CLIENT.finer("Recieved order, compute answer for : " + o);		
		return super.answerFor(o);
	}

	public boolean receive(String readLine) {
		// TODO Auto-generated method stub
		EngineLog.CLIENT.finer("Recieved order: " +readLine);
		return true;
	}
	
	@Override
	protected Order createSession(int hashCode,SocketAddress server)
	{
		
		if(hashCode != Security.ERROR)
		{
			//TODO register hashcode in someplace for log. 
			uuidCodeSession = hashCode;
			Order.setSessionID(uuidCodeSession);
			EngineLog.CLIENT.info("Session UID is " + uuidCodeSession);
			this.isConnected = true;
			return Order.ACK;
		}
		else return Order.ERROR;
	}
	
	public boolean sendAuthInfo(String user, String password)
	{
		String[] authInfo = {user,password};
		EngineLog.CLIENT.fine("Will ask for authentication for " + authInfo[0]);
		chan.write(new Order(OrderType.UPDATE,new Order(OrderType.AUTH, authInfo)));
		return true;
	}

	@Override
	protected Order process(Order instruction) {
		// TODO Auto-generated method stub
		return Order.ERROR;
	}

	public void notifyThread(int orderSequence) {
		ClientThread thread = this.queue.get(orderSequence);
		if(thread != null)
		{
			synchronized (thread) {
				thread.notifyAll();
			}
		}
	}
	public int getUUIDCodeSession()
	{
		return this.uuidCodeSession;
	}
}
