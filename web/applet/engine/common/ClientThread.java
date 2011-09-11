package applet.engine.common;

import applet.engine.ClientEngine;
import com.adreamzone.common.engine.EngineLog;
import com.adreamzone.common.Order;
import com.adreamzone.common.utils.optimizer.CommonValues;
import common.adreamzone.client.EngineClientLog;

/**
 * Thread to treat some process which may ask for many tasks
 * Will  be needed when order can't be in one Server->Client->Server operation
 * @author adi
 *
 */
public class ClientThread extends Thread{
	
	//TODO build a Factory to limit thread creation for about 10 thread.
	//TODO Getting a new Thread must not ask for "new" operande (for optimisation)
	//TODO Make a similar approach (with threading) on Server Side, but must use the couple<User,OrderSequance>
	
	private Order order;
	private ClientEngine engine;
	private static int NEXT_THREAD_ID = 1;
	private int threadSequence;
	public ClientThread(Order o, ClientEngine e)
	{
		this.order = o;
		this.engine = e;
		this.threadSequence = NEXT_THREAD_ID;
		NEXT_THREAD_ID ++;
	}
	
	public void run(){
		switch(this.order.getOrderType())
		{
			case CONNECT : 
				this.startLoginProcess();
				break;
			default:
				EngineClientLog.CLIENT.warning("Nothing to thread for " + this.order);
				break;
		}
	}

	private synchronized void startLoginProcess() {
		// TODO Auto-generated method stub
		EngineClientLog.CLIENT.finer("Server contacted, will waiting on " + this.threadSequence);
		try{this.wait();}catch(InterruptedException e){ e.printStackTrace() ;}
		if(engine.getUUIDCodeSession() != CommonValues.ERROR_OR_INFINITE)
		{
			//TODO Use static method like ClientEngine.GetPassword() to get the password
			//TODO Use static method like ClientEngine.GetUser() to get the USer
			engine.sendAuthInfo("Test", "password");
		}
	}
	
	public int getThreadSequence()
	{
		return this.threadSequence;
	}
	

	/**
	 * Return if a thread have the same ThreadSequanceId
	 */
	public boolean equals(Object e2)
	{
		return ((ClientThread) e2).getThreadSequence() == this.threadSequence;
	}

}
