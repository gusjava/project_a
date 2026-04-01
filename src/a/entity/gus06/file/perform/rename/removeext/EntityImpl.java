package a.entity.gus06.file.perform.rename.removeext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20150629";}


	private Service changeExt;


	public EntityImpl() throws Exception
	{changeExt = Outside.service(this,"gus06.file.perform.rename.changeext");}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	public Object t(Object obj) throws Exception
	{return changeExt.t(new Object[]{obj,null});}
}
