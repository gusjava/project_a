package a.entity.gus06.sys.expression1.apply.op._show1_tree;

import a.framework.*;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161221";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.tree");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof TreeModel)	return new T1(obj);
		if(obj instanceof JTree)	return new T1(obj);
		if(obj instanceof Map)		return new T1(obj);
		if(obj instanceof List)		return new T1(obj);
		if(obj instanceof String[])	return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private String title;
		
		public E1(Object data, String title)
		{
			this.data = data;
			this.title = title;	
		}
		
		public void e() throws Exception
		{perform.v(title,data);}
	}
}
