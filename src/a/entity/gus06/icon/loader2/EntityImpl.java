package a.entity.gus06.icon.loader2;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, T, R, F {

	public String creationDate() {return "20200828";}
	
	
	private Service loader1;
	private Service loader2;

	public EntityImpl() throws Exception
	{
		loader1 = Outside.service(this,"gus06.icon.loader");
		loader2 = Outside.service(this,"gus06.icon.loader.dir");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return load((File) o[0], (String) o[1]);
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return check((File) o[0], (String) o[1]);
	}
	
	private Icon load(File dir, String key) throws Exception
	{
		if(dir==null) return (Icon) loader1.t(key);
		Icon icon1 = (Icon) loader2.t(new Object[]{dir,key});
		if(icon1!=null) return icon1;
		return (Icon) loader1.t(key);
	}
	
	private boolean check(File dir, String key) throws Exception
	{
		if(dir==null) return loader1.f(key);
		return loader1.f(key) || loader2.f(new Object[]{dir,key});
	}
	
	
	public Object r(String key) throws Exception
	{return loader1.r(key);}
}