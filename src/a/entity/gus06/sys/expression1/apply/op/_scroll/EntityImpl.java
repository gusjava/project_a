package a.entity.gus06.sys.expression1.apply.op._scroll;

import a.framework.*;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191116";}


	private Service performBar;
	private Service performPane;
	private Service toIntArray;
	
	public EntityImpl() throws Exception
	{
		performBar = Outside.service(this,"gus.x.swing.scrollbar.scroll");
		performPane = Outside.service(this,"gus06.swing.scrollpane.scroll");
		toIntArray = Outside.service(this,"gus06.find.intarray.len2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JScrollBar) return new T1a((JScrollBar) obj);
		if(obj instanceof JScrollPane) return new T1b((JScrollPane) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1a implements T
	{
		private JScrollBar bar;
		
		public T1a(JScrollBar bar)
		{this.bar = bar;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof String) return new E1a(bar,toInt(obj));
			if(obj instanceof Integer) return new E1a(bar,toInt(obj));
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	private class E1a implements E
	{
		private JScrollBar bar;
		private Integer n;
		
		public E1a(JScrollBar bar, Integer n)
		{
			this.bar = bar;
			this.n = n;
		}
		
		public void e() throws Exception
		{performBar.p(new Object[]{bar,n});}
	}
	
	
	
	
	
	
	private class T1b implements T
	{
		private JScrollPane pane;
		
		public T1b(JScrollPane pane)
		{this.pane = pane;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof int[]) return new E1b(pane,(int[]) toIntArray.t(obj));
			if(obj instanceof List) return new E1b(pane,(int[]) toIntArray.t(obj));
			if(obj instanceof String) return new T2b(pane,toInt(obj));
			if(obj instanceof Integer) return new T2b(pane,toInt(obj));
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	private class T2b implements T
	{
		private JScrollPane pane;
		private Integer n;
		
		public T2b(JScrollPane pane, Integer n)
		{
			this.pane = pane;
			this.n = n;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1b(pane,new int[]{n,toInt(obj)});}
	}
	
	
	private class E1b implements E
	{
		private JScrollPane pane;
		private int[] n;
		
		public E1b(JScrollPane pane, int[] n)
		{
			this.pane = pane;
			this.n = n;
		}
		
		public void e() throws Exception
		{performPane.p(new Object[]{pane,n});}
	}
	
	
	
	
	
	private Integer toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return (Integer) obj;
		if(obj instanceof String) return Integer.valueOf((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
