package a.entity.gus06.sys.expression1.apply.op._as_s;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151111";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof S) return new S1((S) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class S1 implements S
	{
		private S s;
		public S1(S s){this.s = s;}
		
		public void addActionListener(ActionListener listener) throws Exception
		{s.addActionListener(listener);}
		
		public void removeActionListener(ActionListener listener) throws Exception
		{s.removeActionListener(listener);}
		
		public List listeners() throws Exception
		{return s.listeners();}
	}
}
