package a.entity.gus06.icon.loader.zip;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;
import java.io.InputStream;
import javax.swing.ImageIcon;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20250727";}
	
	
	private Service buildZipFile;
	private Service isToByteArray;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToByteArray = Outside.service(this,"gus06.io.transfer.tobytearray");
	}
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		return load((File) o[0], (String) o[1], (String) o[2]);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		return hasKey((File) o[0], (String) o[1], (String) o[2]);
	}
	
	
	private Icon load(File file, String root, String key) throws Exception
	{
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		
		ZipEntry zipEntryGif = zipFile.getEntry(root+"/"+key+".gif");
		if(zipEntryGif!=null) return readIcon(zipFile, zipEntryGif);
		
		ZipEntry zipEntryPng = zipFile.getEntry(root+"/"+key+".png");
		if(zipEntryPng!=null) return readIcon(zipFile, zipEntryPng);
		
		zipFile.close();
		return null;
	}
	
	private boolean hasKey(File file, String root, String key) throws Exception
	{
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		
		ZipEntry zipEntryGif = zipFile.getEntry(root+"/"+key+".gif");
		if(zipEntryGif!=null) {zipFile.close();return true;}
		
		ZipEntry zipEntryPng = zipFile.getEntry(root+"/"+key+".png");
		if(zipEntryPng!=null) {zipFile.close();return true;}
		
		zipFile.close();
		return false;
	}
	
	
	private Icon readIcon(ZipFile zipFile, ZipEntry zipEntry) throws Exception
	{
		InputStream is = zipFile.getInputStream(zipEntry);
		byte[] data = (byte[]) isToByteArray.t(is);
		Icon icon = new ImageIcon(data);
		is.close();
		zipFile.close();
		return icon;
	}
}