package a.entity.gus06.sys.clipboard1.writecontent.todir.javasrc;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20251223";}


	private Service inferSrcDirPackage;
	private Service extractClassName;
	private Service writeFile;
	private Service writeJavaFile;
	private Service replacePackage;

	public EntityImpl() throws Exception
	{
		inferSrcDirPackage = Outside.service(this,"gus06.java.srcdir.infer.package1");
		extractClassName = Outside.service(this,"gus06.java.srccode.extract.classname");
		writeFile = Outside.service(this,"gus06.file.write.string.cs.utf8");
		writeJavaFile = Outside.service(this,"gus06.java.srcdir.generate.fromsrc.utf8");
		replacePackage = Outside.service(this,"gus06.java.srccode.replacepackage");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String src = (String) o[1];
		
		String packageName = (String) inferSrcDirPackage.t(dir);
		if(packageName==null)
		{
			//pas de repertoire source
			
			String className = (String) extractClassName.t(src);
			File javaFile = new File(dir, className+".java");
			writeFile.p(new Object[]{javaFile, src});
		}
		else if(packageName.equals(""))
		{
			//a la racine du repertoire source
			
			writeJavaFile.p(new Object[]{src,dir});
		}
		else
		{
			//dans un package precis du repertoire source
			
			src = (String) replacePackage.t(new Object[]{src,packageName});
			String className = (String) extractClassName.t(src);
			File javaFile = new File(dir, className+".java");
			writeFile.p(new Object[]{javaFile, src});
		}
	}
}
