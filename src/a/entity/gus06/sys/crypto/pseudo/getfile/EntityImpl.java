package a.entity.gus06.sys.crypto.pseudo.getfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141014";}


	private Service findPseudo;
	private File storeDir;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		storeDir = (File) Outside.resource(this,"path#path.pseudodir");
	}
	
	public Object g() throws Exception
	{
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) return null;
		
		storeDir.mkdirs();
		return new File(storeDir,pseudo+".properties");
	}
}
