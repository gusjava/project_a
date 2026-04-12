package a.entity.gus06.socket.wrapper.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170206";}

	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		Socket socket = (Socket) t[0];
		T t_input = (T) t[1];
		T t_output = (T) t[2];
		
		return new Socket0(socket,t_input,t_output);
	}
	
	private Object build(T t, Object input)
	{
		try
		{
			if(t==null) return input;
			return t.t(input);
		}
		catch(Exception e)
		{Outside.err(this,"build(T,Object)",e);}
		return input;
	}
	

	private class Socket0 extends Socket
	{
		private Socket socket;
		private T t_input;
		private T t_output;
		
		public Socket0(Socket socket, T t_input, T t_output)
		{
			this.socket = socket;
			this.t_input = t_input;
			this.t_output = t_output;
		}
		
		public OutputStream getOutputStream() throws IOException
		{
			OutputStream os = socket.getOutputStream();
			return (OutputStream) build(t_output,os);
		}
		
		public InputStream getInputStream() throws IOException
		{
			InputStream is = socket.getInputStream();
			return (InputStream) build(t_input,is);
		}
		
		public void bind(SocketAddress bindpoint) throws IOException
		{socket.bind(bindpoint);}
		
		public void close() throws IOException
		{socket.close();}
		
		public void connect(SocketAddress endPoint) throws IOException
		{socket.connect(endPoint);}
		
		public void connect(SocketAddress endPoint, int timeout) throws IOException
		{socket.connect(endPoint,timeout);}
		
		public SocketChannel getChannel()
		{return socket.getChannel();}
		
		public InetAddress getInetAddress()
		{return socket.getInetAddress();}
		
		public boolean getKeepAlive() throws SocketException
		{return socket.getKeepAlive();}
		
		public InetAddress getLocalAddress()
		{return socket.getLocalAddress();}
		
		public int getLocalPort()
		{return socket.getLocalPort();}
		
		public SocketAddress getLocalSocketAddress()
		{return socket.getLocalSocketAddress();}
		
		public boolean getOOBInline() throws SocketException
		{return socket.getOOBInline();}
		
		public int getPort()
		{return socket.getPort();}
		
		public int getReceiveBufferSize() throws SocketException
		{return socket.getReceiveBufferSize();}
		
		public SocketAddress getRemoteSocketAddress()
		{return socket.getRemoteSocketAddress();}
		
		public boolean getReuseAddress() throws SocketException
		{return socket.getReuseAddress();}
		
		public int getSendBufferSize() throws SocketException
		{return socket.getSendBufferSize();}
		
		public int getSoLinger() throws SocketException
		{return socket.getSoLinger();}
		
		public int getSoTimeout() throws SocketException
		{return socket.getSoTimeout();}
		
		public boolean getTcpNoDelay() throws SocketException
		{return socket.getTcpNoDelay();}
		
		public int getTrafficClass() throws SocketException
		{return socket.getTrafficClass();}
		
		public boolean isBound()
		{return socket.isBound();}
		
		public boolean isClosed()
		{return socket.isClosed();}
		
		public boolean isConnected()
		{return socket.isClosed();}
		
		public boolean isInputShutdown()
		{return socket.isInputShutdown();}
		
		public boolean isOutputShutdown()
		{return socket.isOutputShutdown();}
		
		public void sendUrgentData(int data) throws IOException
		{socket.sendUrgentData(data);}
		
		public void setKeepAlive(boolean on) throws SocketException
		{socket.setKeepAlive(on);}
		
		public void setOOBInline(boolean on) throws SocketException
		{socket.setOOBInline(on);}
		
		public void setPerformancePreferences(int connectionTime, int latency, int bandwidth)
		{socket.setPerformancePreferences(connectionTime,latency,bandwidth);}
		
		public void setReceiveBufferSize(int size) throws SocketException
		{socket.setReceiveBufferSize(size);}
		
		public void setReuseAddress(boolean on) throws SocketException
		{socket.setReuseAddress(on);}
		
		public void setSendBufferSize(int size) throws SocketException
		{socket.setSendBufferSize(size);}
		
		public void setSoLinger(boolean on, int linger) throws SocketException
		{socket.setSoLinger(on,linger);}
		
		public void setSoTimeout(int timeout) throws SocketException
		{socket.setSoTimeout(timeout);}
		
		public void setTcpNoDelay(boolean on) throws SocketException
		{socket.setTcpNoDelay(on);}
		
		public void setTrafficClass(int tc) throws SocketException
		{socket.setTrafficClass(tc);}
		
		public void shutdownInput() throws IOException
		{socket.shutdownInput();}
		
		public void shutdownOutput() throws IOException
		{socket.shutdownOutput();}
		
		public String toString()
		{return socket.toString();}
	}
}
