package a.entity.gus06.sys.expression1.apply.op._ebook_isbn;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251123";}


	private Service readIsbn;
	
	public EntityImpl() throws Exception
	{
		readIsbn = Outside.service(this,"gus06.file.info.ebook.isbn");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File)
		{
			File file = (File) obj;
			return file.isFile()?readIsbn.t(file):null;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
