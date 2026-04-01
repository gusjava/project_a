package a.entity.gus06.appli.entityanalyze.holder.base;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V, R, P, G, F {

	public String creationDate() {return "20150526";}
	
	
	public static final String KEY_BASEDIR = "basedir";


	private Service getFile;
	private Service builder;
	
	private G g_dir;
	private Object holder;
	
	
	
	public EntityImpl() throws Exception
	{
		getFile = Outside.service(this,"gus06.sys.option.getfile");
		builder = Outside.service(this,"gus06.sys.base1.builder");
		
		g_dir = new G() {
			public Object g() throws Exception
			{return initDir();}
		};
		holder = builder.t(g_dir);
	}
	
	
	private File initDir() throws Exception
	{
		File dir = (File) getFile.r(KEY_BASEDIR);
		if(!dir.exists()) dir.mkdirs();
		return dir;
	}
	
	
	
	public Object g() throws Exception
	{return ((G) holder).g();}
	
	public boolean f(Object obj) throws Exception
	{return ((F) holder).f(obj);}
	
	public Object r(String key) throws Exception
	{return ((R) holder).r(key);}
	
	public void v(String key, Object obj) throws Exception
	{((V) holder).v(key,obj);}
	
	public void p(Object obj) throws Exception
	{((P) holder).p(obj);}
}
