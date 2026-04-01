package a.entity.gus06.y.iconprovider1.loader.zip;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250727";}

	private Service load;

	public EntityImpl() throws Exception
	{load = Outside.service(this,"gus06.icon.loader.zip");}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return new Loader((File) o[0], (String) o[1]);}
	
	
	private class Loader implements T, R
	{
		private File file;
		private String root;
		
		public Loader(File file, String root)
		{
			this.file = file;
			this.root = root;
		}
	
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
		
		public Object r(String key) throws Exception
		{return load.t(new Object[]{file,root,key});}
	}
}
