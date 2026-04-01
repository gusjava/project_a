package a.entity.gus06.sys.expression1.apply.op._wait_mouse_pos;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}


	private Service mousePosition;
	
	public EntityImpl() throws Exception
	{mousePosition = Outside.service(this,"gus06.mouse.position");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new E1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private int[] infos;
		
		public E1(String s)
		{infos = parseInfos(s);}
		
		public void e() throws Exception
		{
			while(!compare_(mousePosition(),infos))
			{sleep_20();}
		}
	}
	
	
	private void sleep_20()
	{
		try{Thread.sleep(20);}
		catch(Exception e)
		{Outside.err(this,"sleep_20()",e);}
	}
	
	
	private int[] mousePosition() throws Exception
	{return (int[]) mousePosition.g();}
	
	
	private boolean compare_(int[] a, int[] b)
	{
		if(a[0]!=-1 && b[0]!=-1 && a[0]!=b[0]) return false;
		if(a[1]!=-1 && b[1]!=-1 && a[0]!=b[1]) return false;
		return true;
	}
	
	private int[] parseInfos(String s)
	{
		String[] n = s.split(":");
		return new int[]{toInt(n[0]),toInt(n[1])};
	}
	
	private int toInt(String s)
	{
		if(s.equals("?")) return -1;
		return Integer.parseInt(s);
	}
}
