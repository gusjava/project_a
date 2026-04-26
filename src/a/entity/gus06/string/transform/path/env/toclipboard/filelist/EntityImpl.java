package a.entity.gus06.string.transform.path.env.toclipboard.filelist;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180413";}


	private Service accessClipboard;

	public EntityImpl() throws Exception
	{
		accessClipboard = Outside.service(this,"gus.y.clipboard1.files");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		filesToClipboard(s);
		return s;
	}
	
	
	
	private void filesToClipboard(String s) throws Exception
	{
		List list = new ArrayList();
		
		String[] n = s.split("\n",-1);
		for(int i=0;i<n.length;i++)
		{
			String line = n[i].trim();
			if(!line.equals(""))
			{
				File file = new File(line);
				if(file.isFile()) list.add(file);
			}
		}
		
		accessClipboard.p(list);
	}
}
