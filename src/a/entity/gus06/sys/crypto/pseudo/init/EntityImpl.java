package a.entity.gus06.sys.crypto.pseudo.init;

import a.framework.*;
import java.util.Properties;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141014";}


	private Service getFile;
	private Service generator1;
	private Service writeFile;
	private Service loadPu;


	public EntityImpl() throws Exception
	{
		getFile = Outside.service(this,"gus06.sys.crypto.pseudo.getfile");
		generator1 = Outside.service(this,"gus06.sys.crypto.pseudo.generator1");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		loadPu = Outside.service(this,"gus06.sys.crypto.pseudo.find.prop_pu");
	}
	
	
	public Object g() throws Exception
	{
		File file = (File) getFile.g();
		if(file==null) return null;
		
		if(file.isFile()) return loadPu.g();
		return generate(file);
	}
	
	
	
	private Properties generate(File file) throws Exception
	{
		Properties[] p = (Properties[]) generator1.g();
		if(p==null) return null;
		
		Properties p_public = p[0];
		Properties p_private = p[1];
		
		writeFile.p(new Object[]{file,p_private});
		file.setReadOnly();
		
		return p_public;
	}
}
