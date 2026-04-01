package a.entity.gus06.file.ext.icon.t1;

import javax.swing.Icon;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}


	private Service iconProvider;
	private Service extToIconOs;
	
	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
		extToIconOs = Outside.service(this,"gus06.file.ext.icon.os");
	}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String ext = ((String) obj).toLowerCase();
		Icon icon = (Icon) iconProvider.t("FILE_"+ext);
		return icon!=null ? icon : extToIconOs.t(ext);
	}
}