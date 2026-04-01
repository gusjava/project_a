package a.entity.gus06.sys.expression1.apply.op._mailtrans_smtp;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}


	private Service builder;
	private Service listToArray;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.mail.transport.builder.smtp");
		listToArray = Outside.service(this,"gus06.convert.listtostringarray.strict");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String[]) return builder.t(obj);
		if(obj instanceof Map) return builder.t(obj);
		if(obj instanceof String) return builder.t(stringToArray((String) obj));
		if(obj instanceof List) return builder.t(listToArray((List) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] stringToArray(String s)
	{return s.split("\\|");}
	
	private String[] listToArray(List list) throws Exception
	{return (String[]) listToArray.t(list);}
}
