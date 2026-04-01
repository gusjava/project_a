package a.entity.gus06.sys.expression1.apply.op._read_csv_withsemicolon;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}


	private Service readFile;
	private Service builder;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.csv1");
		builder = Outside.service(this,"gus06.file.convert.csv1.parser");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return readFile.t(obj);
		if(obj instanceof String) return builder.t(obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
