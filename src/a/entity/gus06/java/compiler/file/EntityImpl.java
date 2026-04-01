package a.entity.gus06.java.compiler.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140727";}

	private Service findEntityName;
	private Service isManager;
	private Service isFramework;
	
	private Service compileEntity;
	private Service compileManager;
	private Service compileFramework;
	

	public EntityImpl() throws Exception
	{
		findEntityName = Outside.service(this,"gus06.java.srcfile.extract.entity.name");
		isManager = Outside.service(this,"gus06.java.srcfile.extract.manager.ismanager");
		isFramework = Outside.service(this,"gus06.java.srcfile.extract.framework.isframework");
		
		compileEntity = Outside.service(this,"gus06.java.compiler.entity");
		compileManager = Outside.service(this,"gus06.java.compiler.manager");
		compileFramework = Outside.service(this,"gus06.java.compiler.framework");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
	
		String entityName = (String) findEntityName.t(file);
		if(entityName!=null) {compileEntity.p(entityName);return;}
		
		if(isManager.f(file)) {compileManager.e();return;}
		if(isFramework.f(file)) {compileFramework.e();return;}
	}
}