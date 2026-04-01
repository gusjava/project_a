package a.entity.gus06.map.string.editor2.fieldbuilder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230215";}


	private Service fieldString;

	public EntityImpl() throws Exception
	{
		fieldString = Outside.service(this,"gus06.map.string.editor2.fieldbuilder.string");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String type = o[0];
		String key = o[1];
		
		if(type.equals("string")) return fieldString.t(key);
		throw new Exception("Unsupported field's type: "+type);
	}
}
