package a.entity.gus06.sys.filemanagement1.analyze.search.emptyfiles;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201009";}


	private Service buildFileMap;
	private Service walkThrough;

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		Handler handler = new Handler(p,(R) engine);
		
		Map fileMap = (Map) buildFileMap.t(engine);
		walkThrough.p(new Object[]{fileMap,handler});
		
		p.println("______________");
		p.println("File nb: "+handler.foundNb);
	}
	
	
	private class Handler implements P
	{
		private int foundNb = 0;
		private PrintStream p;
		private R engine;
		
		public Handler(PrintStream p, R engine)
		{
			this.p = p;
			this.engine = engine;
		}
		
		public void p(Object obj) throws Exception
		{
			String[] row = (String[]) obj;
			
			String rootName = row[0];
			String location = row[1];
			String fileName = row[2];
			String size = row[3];
			String md5 = row[5];
			
			if(size.equals("0"))
			{
				p.println(md5+"\t"+rootName+"@"+location+"\\"+fileName+" ("+size+")");
				foundNb++;
			}
		}
	}
}
