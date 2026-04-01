package a.entity.gus06.java.srcfile.extract.entity.creationdate;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150430";}

	private Service readFile;
	private Service srcToDate;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToDate = Outside.service(this,"gus06.java.srccode.extract.entity.creationdate");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) readFile.t(obj);
		return srcToDate.t(src);
	}
}
