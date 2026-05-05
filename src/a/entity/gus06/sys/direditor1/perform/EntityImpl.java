package a.entity.gus06.sys.direditor1.perform;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200531";}


	private Service move;
	private Service copy;
	private Service delete;
	private Service buildListing;
	private Service clean;
	private Service readFile;
	private Service writeFile;


	public EntityImpl() throws Exception
	{
		move = Outside.service(this,"gus06.file.op.move.replace");
		copy = Outside.service(this,"gus06.file.op.copy.replace");
		delete = Outside.service(this,"gus.x.file.op.delete");
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		clean = Outside.service(this,"gus06.dir.perform.clean");
		readFile = Outside.service(this,"gus06.file.read.raw");
		writeFile = Outside.service(this,"gus06.file.write.raw");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		List list = (List) o[1];
		
		List current = (List) buildListing.t(root);
		String rootPath = root.getAbsolutePath();
		
		Set inputs = new HashSet();
		Set outputs = new HashSet();
		
		for(int i=0;i<list.size();i++)
		{
			File[] op = (File[]) list.get(i);
			if(!isInside(rootPath,op[0])) throw new Exception("File not found inside root: "+op[0]);
			if(!isInside(rootPath,op[1])) throw new Exception("File not found inside root: "+op[1]);
			
			inputs.add(op[0]);
			outputs.add(op[1]);
		}
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<current.size();i++)
		{
			File f = (File) current.get(i);
			if(!inputs.contains(f))
			{
				delete.p(f);
				b.append(f+"\n");
				b.append("DELETED\n");
			}
		}
		
		Map loaded = new HashMap();
		Set done = new HashSet();
		
		for(int i=0;i<list.size();i++)
		{
			File[] op = (File[]) list.get(i);
			
			File input = op[0];
			File output = op[1];
			b.append(input+" -> "+output+"\n");
			
			if(loaded.containsKey(input))
			{
				if(output.isFile()) loaded.put(output,readFile.t(output));
				
				Object data = loaded.get(input);
				writeFile.p(new Object[]{output,data});
				done.add(output);
				b.append("WRITTEN\n");
			}
			else if(input.isFile())
			{
				if(output.equals(input))
				{
					//on ne fait rien
					done.add(output);
					b.append("IGNORE\n");
				}
				else
				{
					if(output.isFile()) loaded.put(output,readFile.t(output));
					
					if(!done.contains(input))
						move.p(new File[]{input,output});
					else copy.p(new File[]{input,output});
					done.add(output);
					b.append("MOVED\n");
				}
			}
		}
		
		clean.p(root);
		return b.toString();
	}
	
	
	private boolean isInside(String path, File f)
	{return f.getAbsolutePath().startsWith(path);}
}
