package a.entity.gus06.y.iconprovider1.loader.dir;

import a.framework.*;
import javax.swing.Icon;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250727";}

	private Service load;

	public EntityImpl() throws Exception
	{load = Outside.service(this,"gus06.icon.loader.dir");}
	
	public Object t(Object obj) throws Exception
	{return new Loader((File) obj);}
	
	
	private class Loader implements T, R
	{
		private File dir;
		
		public Loader(File dir)
		{this.dir = dir;}
	
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
		
		public Object r(String key) throws Exception
		{return load.t(new Object[]{dir,key});}
	}
}