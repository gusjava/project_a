package a.entity.gus06.sys.expression1.builder2.h;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160412";}


	private Service doubleArrayToH;
	private Service intArrayToH;
	private Service numberToH;
	private Service tToH;
	private Service rToH;
	
	private Service builder1;
	private Service simpleFonction;


	public EntityImpl() throws Exception
	{
		doubleArrayToH = Outside.service(this,"gus06.convert.doublearraytoh");
		intArrayToH = Outside.service(this,"gus06.convert.intarraytoh");
		numberToH = Outside.service(this,"gus06.convert.numbertoh");
		tToH = Outside.service(this,"gus06.convert.ttoh");
		rToH = Outside.service(this,"gus06.convert.rtoh");
		
		builder1 = Outside.service(this,"gus06.sys.expression1.builder1.h");
		simpleFonction = Outside.service(this,"gus06.math.function.build.fromrule");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findH(data,opMap);
	}
	
	private H findH(Object data, Map opMap) throws Exception
	{
		if(data instanceof H)		return (H) data;
		if(data instanceof T)		return (H) tToH.t(data);
		if(data instanceof R)		return (H) rToH.t(data);
		if(data instanceof Number)	return (H) numberToH.t(data);
		if(data instanceof int[])	return (H) intArrayToH.t(data);
		if(data instanceof double[])	return (H) doubleArrayToH.t(data);
		
		if(data instanceof String)	return stringToH((String) data,opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private H stringToH(String data, Map opMap) throws Exception
	{
		H h = (H) simpleFonction.r(data);
		if(h!=null) return h;
		return (H) builder1.t(new Object[]{data,opMap});
	}
}
