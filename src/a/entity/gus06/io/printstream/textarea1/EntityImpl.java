package a.entity.gus06.io.printstream.textarea1;

import a.framework.*;
import javax.swing.JTextArea;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160417";}
	
	
	private Service thM;
	
	public EntityImpl() throws Exception
	{thM = Outside.service(this,"gus.x.thread.wrapper1");}
	
	
	public Object t(Object obj) throws Exception
	{return new PrintStreamJTextArea((JTextArea) obj);}
	
	
	private class PrintStreamJTextArea extends PrintStream implements Runnable
	{
		private ArrayBlockingQueue queue;
		private JTextArea gui;
		private Thread t;
		private boolean closed = false;
		
		public PrintStreamJTextArea(JTextArea gui) throws Exception
		{
			super(nullOutput);
			this.gui = gui;
			queue = new ArrayBlockingQueue(100);
			
			t = (Thread) thM.t(this);
			t.start();
		}
		
		public void close()
		{
			closed = true;
			super.close();
			
			if(t.isAlive() && queue.isEmpty()) 
			t.interrupt();
		}
		
		
		public void run()
		{
			while(!closed || !queue.isEmpty())
			{
				String data = next();
				if(data!=null) handleData(data);
			}
			gui.setForeground(Color.GRAY);
		}
		
		private void handleData(final String data)
		{
			try
			{
				SwingUtilities.invokeAndWait(new Runnable(){
					public void run()
					{
						gui.append(data);
						gui.setCaretPosition(gui.getDocument().getLength());
					}
				});
			}
			catch(InterruptedException e){}
			catch(InvocationTargetException e){}
		}
		
		
		
		private void put(String data)
		{
			try{queue.put(data);}
			catch (InterruptedException e){}
		}
		
		private String next()
		{
			try{return (String) queue.take();}
			catch(InterruptedException e) {}
			return null;
		}
		
		
		public void println()			{println("");}
		public void println(char[] val)		{println(new String(val));}
		public void println(boolean val)	{println(""+val);}
		public void println(char val)		{println(""+val);}
		public void println(double val)		{println(""+val);}
		public void println(float val)		{println(""+val);}
		public void println(int val)		{println(""+val);}
		public void println(long val)		{println(""+val);}
		public void println(Object val)		{println(""+val);}
		public void println(String m)		{put(m+"\n");}
		
		public void print(char[] val)		{print(new String(val));}
		public void print(boolean val)		{print(""+val);}
		public void print(char val)		{print(""+val);}
		public void print(double val)		{print(""+val);}
		public void print(float val)		{print(""+val);}
		public void print(int val)		{print(""+val);}
		public void print(long val)		{print(""+val);}
		public void print(Object val)		{print(""+val);}
		public void print(String m)		{put(m);}
	}
	
	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
}