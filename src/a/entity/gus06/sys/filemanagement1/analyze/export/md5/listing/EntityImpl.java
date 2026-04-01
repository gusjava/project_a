package a.entity.gus06.sys.filemanagement1.analyze.export.md5.listing;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210201";}

	public static final String FAILED_MD5 = "###";

	private Service chooseFile;
	private Service buildFileMap;
	private Service walkThrough;

	public EntityImpl() throws Exception
	{
		chooseFile = Outside.service(this,"gus06.file.choose.save.file.ext.txt.en");
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		walkThrough = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.walkthrough");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		File outputFile = (File) chooseFile.g();
		if(outputFile==null) return;
		
		p.println("starting md5 export");
		p.println("output file: "+outputFile);
		
		PrintStream out = new PrintStream(outputFile);
		
		Handler handler = new Handler(out);
		Map fileMap = (Map) buildFileMap.t(engine);
		walkThrough.p(new Object[]{fileMap,handler});
		
		out.close();
		p.println("export complete");
	}
	
	
	
	private class Handler implements P
	{
		private Set found;
		private PrintStream out;
		
		public Handler(PrintStream out)
		{
			found = new HashSet();
			found.add(FAILED_MD5);
			this.out = out;
		}
		
		public void p(Object obj) throws Exception
		{
			String[] row = (String[]) obj;
			String md5 = row[5];
			
			if(!found.contains(md5))
			{
				out.println(md5);
				found.add(md5);
			}
		}
	}
}
