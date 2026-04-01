package a.entity.gus06.file.icon.t1;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150324";}


	private Service iconProvider;
	private Service getExtension;
	private Service extToIconOs;
	
	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
		extToIconOs = Outside.service(this,"gus06.file.ext.icon.os");
	}


	public Object t(Object obj) throws Exception
	{return findIcon((File) obj);}
	
	
	
	private Icon findIcon(File f) throws Exception
	{
		if(f==null) return null;
		if(!f.exists()) return findIconForNExists(f);
		if(f.isDirectory()) return findOsIcon(f);
		
		Icon icon = findIconFromExt(f);
		return icon!=null ? icon : findOsIcon(f);
	}
	
	
	private Icon findIconFromExt(File f) throws Exception
	{
		String ext = (String) getExtension.t(f);
		return (Icon) iconProvider.t("FILE_"+ext);
	}
	
	
	private Icon findIconForNExists(File f) throws Exception
	{		
		String ext = (String) getExtension.t(f);
		Icon icon = (Icon) iconProvider.t("FILE_"+ext);
		return icon!=null ? icon : findOsIcon(ext);
	}
	
	
	private Icon findOsIcon(File f)
	{return FileSystemView.getFileSystemView().getSystemIcon(f);}
	
	
	private Icon findOsIcon(String ext) throws Exception
	{return (Icon) extToIconOs.t(ext);}
}