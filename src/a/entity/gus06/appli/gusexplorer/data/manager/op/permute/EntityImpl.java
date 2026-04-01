package a.entity.gus06.appli.gusexplorer.data.manager.op.permute;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151017";}

	public static final String ACTION = "permute";


	private Service extractPath;
	
	public EntityImpl() throws Exception
	{
		extractPath = Outside.service(this,"gus06.file.lnk.extract.path");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object data = o[1];
		
		return perform(list,(File[]) data);
	}
	
	
	private Map perform(List l, File[] f) throws Exception
	{
		if(f.length!=2) throw new Exception("Wrong data number: "+f.length);
		File f1 = file(f[0]);
		File f2 = file(f[1]);
		
		if(!found(l,f1)) return null;
		if(!found(l,f2)) return null;
		
		int index1 = index(l,f1);
		int index2 = index(l,f2);
		
		l.remove(index1);
		l.add(index2,f1);
		
		Map m = new HashMap();
		m.put("action",ACTION);
		m.put("index1",""+index1);
		m.put("index2",""+index2);
		m.put("file1",f1);
		m.put("file2",f2);
		
		return m;
	}
	
	private File file(File f) throws Exception
	{return (File) extractPath.t(f);}
	
	private int index(List l, File f)
	{return l.indexOf(f);}
	
	private boolean found(List l, File f)
	{return index(l,f)>=0;}
}
