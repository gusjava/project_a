package a.entity.gus06.find.p;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}


	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof P) return obj;
		
		if(obj instanceof PrintStream)
			return new P1((PrintStream) obj);
		if(obj instanceof File)
			return new P1(new PrintStream((File) obj,"UTF-8"));
		if(obj instanceof OutputStream)
			return new P1(new PrintStream((OutputStream) obj,true,"UTF-8"));
		if(obj instanceof StringBuffer)
			return new P2((StringBuffer) obj);
		if(obj instanceof StringBuilder)
			return new P3((StringBuilder) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class P1 implements P, G, E
	{
		private PrintStream p;
		public P1(PrintStream p) {this.p = p;}
		
		public void p(Object obj) throws Exception
		{p.print(obj);}
		
		public Object g() throws Exception
		{return p;}
		
		public void e() throws Exception
		{p.close();}
	}
	
	
	private class P2 implements P, G
	{
		private StringBuffer b;
		public P2(StringBuffer b) {this.b = b;}
		
		public void p(Object obj) throws Exception
		{b.append(obj);}
		
		public Object g() throws Exception
		{return b;}
	}
	
	
	private class P3 implements P, G
	{
		private StringBuilder b;
		public P3(StringBuilder b) {this.b = b;}
		
		public void p(Object obj) throws Exception
		{b.append(obj);}
		
		public Object g() throws Exception
		{return b;}
	}
}
