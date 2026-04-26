package a.entity.gus06.sys.expression1.apply.op._clipboard_x_image;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210103";}

	public static final String T = "constant";
	public static final long SLEEP = 500;


	private Service perform;
	private Service compare;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.x.clipboard.image");
		compare = Outside.service(this,"gus06.clipboard.data.compare");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		Object data1 = perform.g();
		while(true)
		{
			sleep_();
			Object data2 = perform.g();
			if(!compare.f(new Object[]{data1,data2})) return data2;
		}
	}
	
	
	
	private void sleep_()
	{
		try{Thread.sleep(SLEEP);}
		catch(Exception e)
		{Outside.err(this,"sleep_()",e);}
	}
}