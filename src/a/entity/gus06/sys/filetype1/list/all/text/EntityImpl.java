package a.entity.gus06.sys.filetype1.list.all.text;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20151017";}


	private Service isText;


	public EntityImpl() throws Exception
	{
		isText = Outside.service(this,"gus06.sys.filetype1.is.text");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		List files = (List) obj;
		
		for(int i=0;i<files.size();i++)
		{
			File f = (File) files.get(i);
			if(!isText.f(f)) return false;
		}
		return true;
	}
}
