package a.entity.gus06.appli.gusexplorer.data.manager.op.reload;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151017";}

	public static final String ACTION = "reload";


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
		
		return perform(list,(File) data);
	}
	
	
	private Map perform(List l, File f) throws Exception
	{
		if(!found(l,f)) return null;
		int index = index(l,f);
		
		Map m = new HashMap();
		m.put("action",ACTION);
		m.put("index",""+index);
		m.put("file",f);
		
		return m;
	}
	
	private File file(File f) throws Exception
	{return (File) extractPath.t(f);}
	
	private int index(List l, File f)
	{return l.indexOf(f);}
	
	private boolean found(List l, File f)
	{return index(l,f)>=0;}
}
