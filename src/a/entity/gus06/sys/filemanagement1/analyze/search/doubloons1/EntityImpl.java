package a.entity.gus06.sys.filemanagement1.analyze.search.doubloons1;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201007";}


	private Service buildFileMap;
	private Service perform1;
	private Service formatSize;
	
	

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		perform1 = Outside.service(this,"gus06.sys.filemanagement1.tool.doubloon.perform1");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.fr");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		Map fileMap = (Map) buildFileMap.t(engine);
		Map result = (Map) perform1.t(fileMap);
		
		Map doubloons = (Map) result.get("doubloons");
		int doubloonNb = (int) result.get("doubloonNb");
		long deletableNb = (long) result.get("deletableNb");
		long total = (long) result.get("total");
		
		List list1 = new ArrayList();
		Iterator it = doubloons.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Map m = (Map) doubloons.get(md5);
			Long lostSpace = (Long) m.get("lostSpace");
			list1.add(new Object[]{md5,lostSpace});
		}
		Collections.sort(list1,new Comparator(){
			public int compare(Object o1, Object o2)
			{
				Long space1 = (Long)((Object[]) o1)[1];
				Long space2 = (Long)((Object[]) o2)[1];
				return space2.compareTo(space1);
			}
		});
		
		p.println("Nb: "+doubloonNb);
		p.println("Deletable: "+deletableNb);
		p.println("Lost space: "+formatSize.t(total));
		p.println("______________");
		
		int nb = Math.min(doubloonNb,100);
		for(int i=0;i<nb;i++)
		{
			Object[] row = (Object[]) list1.get(i);
			String md5 = (String) row[0];
			Long lostSpace = (Long) row[1];
			
			Map m = (Map) doubloons.get(md5);
			
			long fileSize = (long) m.get("fileSize");
			int number = (int) m.get("number");
			Set set = (Set) m.get("places");
			
			p.print(md5);
			p.print(" : ");
			p.print(formatSize.t(lostSpace));
			p.print("\t\t[");
			p.print(number);
			p.print(" x ");
			p.print(formatSize.t(fileSize));
			p.println("]");
			
			it = set.iterator();
			while(it.hasNext())
			{
				String place = (String) it.next();
				p.println(place);
			}
			p.println("______________");
		}
	}
}
