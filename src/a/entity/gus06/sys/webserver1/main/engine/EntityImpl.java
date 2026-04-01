package a.entity.gus06.sys.webserver1.main.engine;

import a.framework.*;
import java.nio.channels.*;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.util.*;
import java.io.PrintStream;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, P, G, R, E, S, Runnable {

	public String creationDate() {return "20140927";}

	public static final int BUFFSIZE = 8192;


	private Service receiver;
	private Service channelBuilder;
	
	private Thread t;
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private ByteBuffer readBuffer;
	private Map pending;
	
	
	
	public EntityImpl() throws Exception
	{
		receiver = Outside.service(this,"gus06.sys.webserver1.main.engine.receiver");
		channelBuilder = Outside.service(this,"gus06.sys.webserver1.main.engine.channelbuilder");
		
		pending = new HashMap();
	}
	
	
	
	public void e() throws Exception
	{
		receiver.e();
		
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public Object g() throws Exception
	{return receiver.g();}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		SocketChannel socketChannel = (SocketChannel) o[0];
		byte[] data = (byte[]) o[1];
		
		synchronized(pending)
		{enqueueByteBuffer(pending,socketChannel,data);}
		selector.wakeup();
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("serverChannel")) return serverChannel;
		if(key.equals("keys")) return new String[]{"serverChannel"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	
	
	
	
	public void run()
	{
		if(initServer())
		while(true) {perform();sleep1();}
	}
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(Exception e){Outside.err(this,"sleep1()",e);}
	}

	
	private boolean initServer()
	{
		try
		{
			readBuffer = ByteBuffer.allocate(BUFFSIZE);
			selector = Selector.open();
			
			serverChannel = (ServerSocketChannel) channelBuilder.g();
			serverChannel.register(selector,SelectionKey.OP_ACCEPT);
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"initServer()",e);
			return false;
		}
	}

	
	
	private void perform()
	{
		try
		{
			registerPending();
			selector.select();
			Iterator it = selector.selectedKeys().iterator();
			while(it.hasNext())
			{
				handleKey((SelectionKey) it.next());
				it.remove();
			}
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	

	private void handleKey(SelectionKey key) throws Exception
	{
		if(!key.isValid()) return;

		if(key.isAcceptable()) acceptKey(key);
		else if(key.isReadable()) readKey(key);
		else if(key.isWritable()) writeKey(key);
	}



	private void acceptKey(SelectionKey key) throws Exception
	{
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector,SelectionKey.OP_READ);
	}
	
	
	
	private void readKey(SelectionKey key) throws Exception
	{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		readBuffer.clear();

		int nbRead;
		try{nbRead = socketChannel.read(readBuffer);}
		catch(Exception e)
		{
			key.cancel();
			socketChannel.close();
			return;
		}
		if(nbRead == -1)
		{
			key.cancel();
			socketChannel.close();
			return;
		}
		
		byte[] data = new byte[nbRead];
		System.arraycopy(readBuffer.array(),0,data,0,nbRead);
		receiver.p(new Object[]{socketChannel,data});
	}
	
	
	
	
	
	private void writeKey(SelectionKey key) throws Exception
	{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		
		synchronized(pending)
		{
			if(!pending.containsKey(socketChannel))
			{key.interestOps(SelectionKey.OP_READ);return;}
			
			List queue = (List) pending.get(socketChannel);
			while(!queue.isEmpty())
			{
				ByteBuffer buf = (ByteBuffer) queue.get(0);
				socketChannel.write(buf);
				if(buf.remaining()>0) break;
				queue.remove(0);
			}
			if(queue.isEmpty())
			{
				pending.remove(socketChannel);
				
				// CHOOSE ONE, DEPENDING ON HTTP 1.0 OR 1.1
				key.interestOps(SelectionKey.OP_READ); // FOR HTTP 1.1
				socketChannel.close(); // FOR HTTP 1.0
			}
		}
	}
	
	
	
	private void registerPending() throws Exception
	{
		synchronized(pending)
		{
			Iterator it = pending.keySet().iterator();
			while(it.hasNext())
			{
				SocketChannel socketChannel = (SocketChannel) it.next();
				SelectionKey key = socketChannel.keyFor(selector);
				
				if(key==null) it.remove();
				else key.interestOps(SelectionKey.OP_WRITE);
			}
		}
	}
	
	
	
	private void enqueueByteBuffer(Map map, Object key, byte[] data)
	{
		if(!map.containsKey(key)) map.put(key,new ArrayList());
		List queue = (List) map.get(key);
		queue.add(ByteBuffer.wrap(data));
	}
	
	
	
	public void addActionListener(ActionListener listener) throws Exception
	{receiver.addActionListener(listener);}
	
	public void removeActionListener(ActionListener listener) throws Exception
	{receiver.removeActionListener(listener);}
	
	public List listeners() throws Exception
	{return receiver.listeners();}
}
