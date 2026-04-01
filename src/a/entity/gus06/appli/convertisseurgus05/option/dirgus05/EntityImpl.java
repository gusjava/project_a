package a.entity.gus06.appli.convertisseurgus05.option.dirgus05;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150525";}
	
	public static final String KEY = "dirgus05";
	
	private Service getFile;
	
	public EntityImpl() throws Exception
	{getFile = Outside.service(this,"gus06.sys.option.getfile");}
	
	public Object g() throws Exception
	{return getFile.r(KEY);}
}
