package a.entity.gus06.sys.filetool.ext.symfony1.holder.entities;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import javax.swing.JPanel;
import java.io.File;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20150329";}


	private Service findRoot;
	
	private Map map;
	private File root;
	private File dir1;
	private File dir2;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private String get(String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		dir1 = findDir1();
		dir2 = findDir2();
	}
	
	
	private File findDir1() throws Exception
	{
		File[] d = root.listFiles();
		if(d.length==1) return d[0];
		if(d.length==0) return null;
		
		String name = get("bundle.parent");
		if(name==null) return null;
		return new File(root,name);
	}
	
	
	private File findDir2()
	{
		if(dir1==null) return null;
		File[] d = dir1.listFiles();
		if(d==null || d.length==0) return null;
		
		if(d.length==1) return d[0];
		
		String name = get("bundle.name");
		if(name==null) return null;
		return new File(dir1,name);
	}
}
