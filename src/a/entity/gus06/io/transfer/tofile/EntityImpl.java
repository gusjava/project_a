package a.entity.gus06.io.transfer.tofile;

import a.framework.*;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150812";}


	private Service tmpFile;
	private Service transfert;
	
	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
		transfert = Outside.service(this,"gus06.io.transfer");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return fromInputStream((InputStream) obj);
		if(obj instanceof Object[]) return fromArray((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File fromInputStream(InputStream is) throws Exception
	{
		File file = (File) tmpFile.g();
		
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
		return file;
	}
	
	private File fromArray(Object[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Invalid array length: "+array.length);
		
		InputStream is = (InputStream) array[0];
		String ext = (String) array[1];
		File file = (File) tmpFile.t(ext);
		
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
		return file;
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) o[0];
		File file = (File) o[1];
		
		FileOutputStream fos = new FileOutputStream(file);
		transfert.p(new Object[]{is,fos});
	}
}