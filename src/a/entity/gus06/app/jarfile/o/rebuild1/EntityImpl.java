package a.entity.gus06.app.jarfile.o.rebuild1;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140802";}

	private Service fp;
	private Service rebuild;
	private String sequence;

	public EntityImpl() throws Exception
	{
		fp = Outside.service(this,"fileprovider");
		rebuild = Outside.service(this,"gus06.app.jarfile.o.rebuild");
		sequence = (String) Outside.resource(this,"property#sequence.dev.appjar");
	}
	
	
	public void e() throws Exception
	{
		rebuild.p(buildDirs());
	}
	
	
	
	private File[] buildDirs() throws Exception
	{
		if(sequence==null || sequence.equals(""))
			throw new Exception("Bin path sequence undefined");
		
		String[] n = sequence.split(";");
		File[] dirs = new File[n.length];
		for(int i=0;i<n.length;i++) dirs[i] = dir(n[i]);

		return dirs;
	}
	
	
	
	private File dir(String id) throws Exception
	{
		File dir = (File) fp.r(id);
		if(dir==null || !dir.isDirectory())
			throw new Exception("Undefined directory for id="+id);
		return dir;
	}
}
