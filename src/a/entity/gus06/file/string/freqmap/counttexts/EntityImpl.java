package a.entity.gus06.file.string.freqmap.counttexts;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210630";}


	private Service readFile;
	private Service perform;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		perform = Outside.service(this,"gus06.data.string.freqmap.counttexts");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String[] words = (String[]) o[1];
		
		String text = (String) readFile.t(file);
		return perform.t(new Object[]{text,words});
	}
}
