package a.entity.gus06.file.editor.main.recorder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl extends S1 implements Entity, R, P, G {

	public String creationDate() {return "20170224";}


	private Map map;
	
	public EntityImpl() throws Exception
	{map = new HashMap();}
	
	
	
	public synchronized void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object editor = o[0];
		File file = (File) o[1];
		
		if(file==null) map.remove(editor);
		else map.put(editor,file.getAbsolutePath());
		
		editorRecorded();
	}
	
	
	public synchronized Object r(String key) throws Exception
	{
		Set set = new HashSet();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object editor = it.next();
			String path = (String) map.get(editor);
			if(path.equals(key)) set.add(editor);
		}
		return set;
	}
	
	
	public synchronized Object g() throws Exception
	{
		return map;
	}
	
	
	private void editorRecorded()
	{send(this,"editorRecorded()");}
}
