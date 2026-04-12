package a.entity.gus06.java.srccode.adapttofile.changesrc;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20251219";}

	private Service valid;
	private Service inferPackage;
	private Service filetoClassName;
	private Service readFile;
	private Service writeFile;
	private Service replacePackage;
	private Service replaceClassName;

	public EntityImpl() throws Exception
	{
		valid = Outside.service(this,"gus06.java.srccode.isvalid");
		inferPackage = Outside.service(this,"gus06.java.srcdir.infer.package1");
		filetoClassName = Outside.service(this,"gus06.java.srcfile.filename.toclassname");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		writeFile = Outside.service(this,"gus06.file.write.string.cs.utf8");
		replacePackage = Outside.service(this,"gus06.java.srccode.replacepackage");
		replaceClassName = Outside.service(this,"gus06.java.srccode.replaceclassname");
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof File) return handleFile((File) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean handleFile(File file) throws Exception
	{
		return handle(file,file);
	}
	
	private boolean handleArray(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return handle((File) o[0], o[1]);
	}
	
	private boolean handle(File file, Object data) throws Exception
	{
		String targetPackageName = (String) inferPackage.t(file);
		String tarfiletoClassName = (String) filetoClassName.t(file);
		if(tarfiletoClassName==null) return false;
		
		String javaSrc = findSrc(data);
		if(!valid.f(javaSrc)) return false;
		
		javaSrc = (String) replacePackage.t(new Object[]{javaSrc, targetPackageName});
		javaSrc = (String) replaceClassName.t(new Object[]{javaSrc, tarfiletoClassName});
		writeFile.p(new Object[]{file,javaSrc});
		return true;
	}
	
	private String findSrc(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) readFile.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
