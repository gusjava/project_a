package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.manager;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, G, R, V {

	public String creationDate() {return "20160828";}


	private Service dirToSet;
	
	private Service buildT;
	private Service buildE;
	private Service buildF;
	private Service buildG;
	private Service buildP;
	
	private File dir;
	private Map data;
	
	

	public EntityImpl() throws Exception
	{
		dirToSet = Outside.service(this,"gus06.dir.children.dirtoset.name0");
		
		buildT = Outside.service(this,"gus06.sys.script1.build2.t");
		buildE = Outside.service(this,"gus06.sys.script1.build2.e");
		buildF = Outside.service(this,"gus06.sys.script1.build2.f");
		buildG = Outside.service(this,"gus06.sys.script1.build2.g");
		buildP = Outside.service(this,"gus06.sys.script1.build2.p");
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		dir = new File(dir0,"scripts_h");
		dir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{
		return dirToSet.t(dir);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dir")) return dir;
		if(key.equals("keys")) return new String[]{"dir"};
		
		if(key.startsWith("file:")) return file(key.substring(5));
		if(key.startsWith("t:")) return buildT(key.substring(2));
		if(key.startsWith("e:")) return buildE(key.substring(2));
		if(key.startsWith("f:")) return buildF(key.substring(2));
		if(key.startsWith("g:")) return buildG(key.substring(2));
		if(key.startsWith("p:")) return buildP(key.substring(2));
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("data")) {data = (Map) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private File file(String key) throws Exception
	{
		File f = new File(dir,key+".gus");
		return f.isFile() && f.length()>0 ? f : null;
	}
	
	
	
	private T buildT(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (T) buildT.t(new Object[]{f,data});
	}
	
	private E buildE(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (E) buildE.t(new Object[]{f,data});
	}
	
	private F buildF(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (F) buildF.t(new Object[]{f,data});
	}
	
	private G buildG(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (G) buildG.t(new Object[]{f,data});
	}
	
	private P buildP(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (P) buildP.t(new Object[]{f,data});
	}
}