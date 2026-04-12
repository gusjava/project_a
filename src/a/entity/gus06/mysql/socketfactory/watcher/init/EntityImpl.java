package a.entity.gus06.mysql.socketfactory.watcher.init;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170207";}

	public static final int DEFAULT_PROGRESS_SPACE = 5000;
	public static final long UPDATE_DELAY = 100;
	public static final long TIMEOUT = 30000;
	
	private Service wrapper;
	private Timer timer;
	
	public EntityImpl() throws Exception
	{
		wrapper = Outside.service(this,"gus06.mysql.socketfactory.wrapper");
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	public Object g() throws Exception
	{
		Watcher watcher = new Watcher();
		wrapper.p(new T[]{watcher.t_input,watcher.t_output});
		return watcher;
	}
	
	private class Watcher extends S1 implements R, V
	{
		private int progress_space = DEFAULT_PROGRESS_SPACE;
		private boolean progress_activated = true;

		private long start = 0;
		
		private volatile long r_t_1 = 0;
		private volatile long r_n_1 = 0;
		private volatile double r_v_0 = 0;
		private volatile double r_v_1 = 0;
		
		private volatile long w_t_1 = 0;
		private volatile long w_n_1 = 0;
		private volatile double w_v_0 = 0;
		private volatile double w_v_1 = 0;
		
		private volatile boolean readInfo_changed = false;
		private volatile boolean writeInfo_changed = false;
		
		private TimerTask task;
		private T_input t_input;
		private T_output t_output;
		
		private volatile long query_size = -1;
		private volatile long query_offset = -1;
		private volatile long query_start = -1;
		
		private volatile BufferedImage query_preview;
		
		
		
		public Watcher()
		{
			start = System.currentTimeMillis();
			task = new TimerTask() {public void run() {check();}};
			timer.schedule(task,new Date(),UPDATE_DELAY);
			
			t_input = new T_input(this);
			t_output = new T_output(this);
		}
		
		public synchronized void v(String key, Object obj) throws Exception
		{
			if(key.equals("query_size"))
			{
				initQuery(((Long) obj).longValue());
				return;
			}
			if(key.equals("query_preview"))
			{
				query_preview = (BufferedImage) obj;
				return;
			}
			throw new Exception("Unknown key: "+key);
		}
		
		
		public synchronized Object r(String key) throws Exception
		{
			if(key.equals("t_input")) return t_input;
			if(key.equals("t_output")) return t_output;
			if(key.equals("start")) return Long.valueOf(start);
			
			if(key.equals("query_size")) return Long.valueOf(query_size);
			if(key.equals("query_offset")) return Long.valueOf(query_offset);
			if(key.equals("query_start")) return Long.valueOf(query_start);
			if(key.equals("query_preview")) return query_preview;
			if(key.equals("query_info")) return queryInfo();
			
			if(key.equals("r_t_1")) return Long.valueOf(r_t_1);
			if(key.equals("r_n_1")) return Long.valueOf(r_n_1);
			if(key.equals("r_v_0")) return Double.valueOf(r_v_0);
			if(key.equals("r_v_1")) return Double.valueOf(r_v_1);
			
			if(key.equals("w_t_1")) return Long.valueOf(w_t_1);
			if(key.equals("w_n_1")) return Long.valueOf(w_n_1);
			if(key.equals("w_v_0")) return Double.valueOf(w_v_0);
			if(key.equals("w_v_1")) return Double.valueOf(w_v_1);
			
			if(key.equals("keys")) return new String[]{
				"t_input","t_output","start",
				"query_size","query_offset","query_start","query_info","query_preview",
				"r_t_1","r_n_1","r_v_0","r_v_1",
				"w_t_1","w_n_1","w_v_0","w_v_1"
			};
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void initQuery(long v)
		{
			query_size = v;
			query_offset = w_n_1;
			query_start = System.currentTimeMillis();
			
			queryInitialized();
		}
		
		private long[] queryInfo()
		{
			if(query_size==-1) return null;
			long query_done = w_n_1-query_offset;
			return new long[]{query_size,query_done,query_start};
		}
		
		private synchronized void updateReadInfo(int nb, long t1, long t2)
		{
			if(t2==t1) t2++;
			
			long r_t_0 = t2-t1;
			int r_n_0 = nb;
			
			r_v_0 = (double)r_n_0/(double)r_t_0;
			
			r_t_1 += r_t_0;
			r_n_1 += r_n_0;
			r_v_1 = (double)r_n_1/(double)r_t_1;
			
			readInfo_changed = true;
		}
		
		private synchronized void updateWriteInfo(int nb, long t1, long t2)
		{
			if(t2==t1) t2++;
			
			long w_t_0 = t2-t1;
			int w_n_0 = nb;
			
			w_v_0 = (double)w_n_0/(double)w_t_0;
			
			w_t_1 += w_t_0;
			w_n_1 += w_n_0;
			w_v_1 = (double)w_n_1/(double)w_t_1;
			
			writeInfo_changed = true;
		}
		
		private void check()
		{
			if(readInfo_changed)
			{
				readInfo_changed = false;
				readInfoUpdated();
			}
			if(writeInfo_changed)
			{
				writeInfo_changed = false;
				writeInfoUpdated();
			}
		}
		
		private void readInfoUpdated()
		{send(this,"readInfoUpdated()");}
		
		private void writeInfoUpdated()
		{send(this,"writeInfoUpdated()");}
		
		private void queryInitialized()
		{send(this,"queryInitialized()");}
	}
	
	
	private class T_input implements T
	{
		private Watcher watcher;
		public T_input(Watcher watcher)
		{this.watcher = watcher;}
		
		public Object t(Object obj) throws Exception
		{return new InputStream0((InputStream) obj,watcher);}
	}
	
	
	private class T_output implements T
	{
		private Watcher watcher;
		public T_output(Watcher watcher)
		{this.watcher = watcher;}
		
		public Object t(Object obj) throws Exception
		{return new OutputStream0((OutputStream) obj,watcher);}
	}
	
	
	
	private class InputStream0 extends InputStream
	{
		private InputStream is;
		private Watcher watcher;
		
		public InputStream0(InputStream is, Watcher watcher)
		{
			this.is = is;
			this.watcher = watcher;
		}
		
		public int read() throws IOException
		{return is.read();}
		
		public int read(byte[] b) throws IOException
		{return read(b,0,b.length);}

		public int read(byte[] b, int off, int len) throws IOException
		{
			if(watcher.progress_activated)
				return read_p(b,off,len);
			else return is.read(b,off,len);
		}

		public int read_p(byte[] b, int off, int len) throws IOException
		{
			int left = len;
			int read = 0;
			int pos = off;
			
			long t1 = System.currentTimeMillis();
			long t2 = System.currentTimeMillis();
			
			while(left>0)
			{
				int k = Math.min(left,watcher.progress_space);
				
				t1 = t2;
				
//				ThreadTimeOut tto = new ThreadTimeOut(is,TIMEOUT);

				int r = is.read(b,pos,k);
				
//				tto.interrupt();
				
				t2 = System.currentTimeMillis();
				
				if(r==-1) return read;
				
				read += r;
				left -= r;
				pos += r;
				
				watcher.updateReadInfo(r,t1,t2);
			}
			return read;
		}
	}
	
	private class OutputStream0 extends OutputStream
	{
		private OutputStream os;
		private Watcher watcher;
		
		public OutputStream0(OutputStream os, Watcher watcher)
		{
			this.os = os;
			this.watcher = watcher;
		}

		public void write(int b) throws IOException
		{os.write(b);}
		
		public void write(byte[] b) throws IOException
		{write(b,0,b.length);}
		
		public void write(byte[] b, int off, int len) throws IOException
		{
			if(watcher.progress_activated)
				write_p(b,off,len);
			else os.write(b,off,len);
		}
		
		private void write_p(byte[] b, int off, int len) throws IOException
		{
			int left = len;
			int pos = off;
			
			long t1 = System.currentTimeMillis();
			long t2 = System.currentTimeMillis();
			
			while(left>0)
			{
				int k = Math.min(left,watcher.progress_space);
				
				t1 = t2;
				os.write(b,pos,k);
				t2 = System.currentTimeMillis();
				
				left -= k;
				pos += k;
				
				watcher.updateWriteInfo(k,t1,t2);
			}
		}
	}
	
	private class ThreadTimeOut extends Thread
	{	
		private InputStream is;
		private long timeout;
		
		public ThreadTimeOut(InputStream is, long timeout)
		{
			this.is = is;
			this.timeout = timeout;
			start();
		}
		
		public void run()
		{
			try{Thread.sleep(timeout);}
			catch(InterruptedException e){return;}
			
			try{is.close();}
			catch(IOException e) {}
		}
	}
}
