package a.entity.gus06.sys.expression1.apply.op._to_p;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}


	private Service builder;
	private Service eToP;
	private Service printStreamToP;
	private Service collectionToP;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder1.p");
		eToP = Outside.service(this,"gus06.feature.wrap.e.p");
		printStreamToP = Outside.service(this,"gus06.convert.printstreamtop");
		collectionToP = Outside.service(this,"gus06.collection.build.adder");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof P) return value;
		
		if(value instanceof E) return eToP.t(value);
		if(value instanceof Collection) return collectionToP.t(value);
		if(value instanceof PrintStream) return printStreamToP.t(value);
		
		if(value instanceof String) return builder.t(new Object[]{value,opMap});
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
