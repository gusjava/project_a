package a.entity.gus.y.serversocket1.ccprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20221120";}
	
	
	public Object g() throws Exception
	{return new CcPrintStream();}
	
	
	private static final OutputStreamNull nullOutput = new OutputStreamNull();
	
	private static class OutputStreamNull extends OutputStream
	{
		public OutputStreamNull(){} 
		public void write(int b){}
	}
	
	private class CcPrintStream extends PrintStream implements V
	{
		private String offset;
		private P redirect;
		private StringBuffer sb;
		
		public CcPrintStream()
		{
			super(nullOutput);
			sb = new StringBuffer();
		}
		
		public void println(String s) {print2(s+"\n");}
		public void println(Object obj) {print2(obj+"\n");}
		public void println(boolean b) {print2(b+"\n");}
		public void println(char c) {print2(c+"\n");}
		public void println(char[] c) {print2(new String(c)+"\n");}
		public void println(double c) {print2(c+"\n");}
		public void println(float c) {print2(c+"\n");}
		public void println(int c) {print2(c+"\n");}
		public void println(long c) {print2(c+"\n");}
		
		public void print(String s) {print2(s);}
		public void print(Object obj) {print2(""+obj);}
		public void print(boolean b) {print2(""+b);}
		public void print(char c) {print2(""+c);}
		public void print(char[] c) {print2(new String(c));}
		public void print(double c) {print2(""+c);}
		public void print(float c) {print2(""+c);}
		public void print(int c) {print2(""+c);}
		public void print(long c) {print2(""+c);}
		
		private void print2(String s)
		{
			if(redirect==null) return;
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c=='\n')
				{
					String v = sb.toString();
					send(redirect,offset,v);
					sb.delete(0,sb.length());
				}
				else sb.append(c);
			}
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("redirect")) {redirect = (P) obj;return;}
			if(key.equals("offset")) {offset = (String) obj;return;}
			
			throw new Exception("Unknown key: "+key);
		}
	}
	
	private void send(P redirect, String offset, String s)
	{
		try{redirect.p(offset+s);}
		catch(Exception e)
		{Outside.err(this,"send(P,String,String)",e);}
	}
}