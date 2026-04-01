package a.entity.gus06.sys.filemanagement1.tool.export.dirpreview.astree;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210228";}

	public static final String KEY_CHILDREN = "children";
	public static final String KEY_NAME = "name";
	public static final String KEY_TYPE = "type";


	private Service chooseTree;
	private Service findPreview1;
	private Service getExtension;

	public EntityImpl() throws Exception
	{
		chooseTree = Outside.service(this,"gus06.file.choose.save.file.ext.tree.en");
		findPreview1 = Outside.service(this,"gus06.sys.filemanagement1.tool.preview1.find.data");
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		
		if(selected==null) return;
		if(engine==null) return;
		
		File treeFile = (File) chooseTree.g();
		if(treeFile==null) return;
		
		PrintStream p = new PrintStream(treeFile);
		handle(p,0,selected);
		p.close();
	}
	
	
	
	
	private void handle(PrintStream p, int level, Map node) throws Exception
	{
		String type = (String) node.get(KEY_TYPE);
		String name = (String) node.get(KEY_NAME);
		String iconKey = findIconKey(type,name);
		
		p.println(offset(level)+iconKey+"#"+name);
		
		List children = (List) node.get(KEY_CHILDREN);
		if(children!=null)
		{
			int nb = children.size();
			for(int i=0;i<nb;i++)
			{
				Map child = (Map) children.get(i);
				handle(p,level+1,child);
			}
		}
	}
	
	
	private String findIconKey(String type, String name) throws Exception
	{
		if(type.equals("root")) return "UTIL_disk";
		if(type.equals("dir")) return "dir";
		return "FILE_"+getExtension.t(name);
	}
	
	
	
	private String offset(int level)
	{
		StringBuffer b = new StringBuffer("@");
		for(int i=0;i<level;i++) b.append("@");
		return b.toString();
	}
}