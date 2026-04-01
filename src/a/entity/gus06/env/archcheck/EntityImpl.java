package a.entity.gus06.env.archcheck;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, F, G {

	public String creationDate() {return "20150607";}
	
	public static final String sunArch = System.getProperty("sun.arch.data.model");
	public static final String PROPKEY_DEBUG = "debug.env.archcheck";
	
	
	private Map props;
	
	
	public EntityImpl() throws Exception
	{
		props = (Map) Outside.resource(this,"props");
	}
	
	
	
	private String arch()
	{
		if(props.containsKey(PROPKEY_DEBUG))
			return (String) props.get(PROPKEY_DEBUG);
		return sunArch;
	}
	
	
	public Object g() throws Exception
	{return arch();}
	
	
	public boolean f(Object obj) throws Exception
	{return obj.equals(arch());}
}
