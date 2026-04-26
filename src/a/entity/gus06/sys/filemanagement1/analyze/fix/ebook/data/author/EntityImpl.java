package a.entity.gus06.sys.filemanagement1.analyze.fix.ebook.data.author;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.StringJoiner;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220911";}


	private Service retrieveMd5Set;
	private Service findMapping;
	private Service readProp;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.retrieve.md5set");
		findMapping = Outside.service(this,"gus06.data.authorname.list.mapping");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];

		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected ebooks: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		Map md5ToAuthors = new HashMap();
		List authorList = new ArrayList();
		
		Iterator it = md5Set.iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Map prop1 = (Map) ((R) engine).r("prop1:"+md5);
			if(prop1.containsKey("ebook.author"))
			{
				String[] authorNames = ((String) prop1.get("ebook.author")).split(";");
				md5ToAuthors.put(md5, authorNames);
				for(String authorName : authorNames)
				{
					if(!authorList.contains(authorName))
					authorList.add(authorName);
				}
			}
		}
		
		p.println("authorNb: "+authorList.size());
		
		Map authorMapping = (Map) findMapping.t(authorList);
		
		it = md5Set.iterator();
		int fixedNb = 0;
		int md5WithoutAuthor = 0;
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			if(md5ToAuthors.containsKey(md5))
			{
				String[] authorNames = (String[]) md5ToAuthors.get(md5);
				
				StringJoiner authorNames1Sj = new StringJoiner(";");
				for(int i=0;i<authorNames.length;i++)
				authorNames1Sj.add((String) authorMapping.get(authorNames[i]));
				String authorNames1 = authorNames1Sj.toString();
				
				
				File infoFile = (File) ((R) engine).r("infoFile:"+md5);
				Map infoMap = (Map) readProp.t(infoFile);
				
				if(infoMap==null) infoMap = new HashMap();
				
				if(!infoMap.containsKey("ebook.author"))
				{
					infoMap.put("ebook.author",authorNames1);
					infoMap.put("ebook.author.fixed","true");
					writeProp.p(new Object[]{infoFile,infoMap});
					fixedNb++;
				}
				else
				{
					infoMap.put("ebook.author.fix",authorNames1);
					writeProp.p(new Object[]{infoFile,infoMap});
				}
			}
			else md5WithoutAuthor++;
		}
		
		p.println("fixedNb: "+fixedNb);
		p.println("md5WithoutAuthor: "+md5WithoutAuthor);
		p.println("______________");
	}
}