package a.entity.gus06.java.compiler.vx;

import a.framework.*;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20200229";}

	public static final String DEFAULT_TYPE = "3";

	private Service compiler1;
	private Service compiler2;
	private Service compiler3;


	public EntityImpl() throws Exception
	{
		compiler1 = Outside.service(this,"gus06.java.compiler.v1");
		compiler2 = Outside.service(this,"gus06.java.compiler.v2");
		compiler3 = Outside.service(this,"gus06.java.compiler.v3");
	}
	
	
	public Object g() throws Exception
	{return find(DEFAULT_TYPE);}
	
	
	public Object r(String key) throws Exception
	{return find(key);}



	private Service find(String type) throws Exception
	{
		if(type.equals("1")) return compiler1;
		if(type.equals("2")) return compiler2;
		if(type.equals("3")) return compiler3;
		throw new Exception("Unknown compiler type: "+type);
	}
}
