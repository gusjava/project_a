package a.entity.gus06.file.image.perform.edition3;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191119";}


	private Service findAccess;
	private Service dialog;
	
	public EntityImpl() throws Exception
	{
		findAccess = Outside.service(this,"gus06.file.access.image.all");
		dialog = Outside.service(this,"gus06.file.image.perform.edition3.dialog");
	}


	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Object access = findAccess.t(file);
		
		Object img = ((G)access).g();
		img = dialog.t(img);
		
		if(img==null) return false;
		((P)access).p(img);
		return true;
	}
}
