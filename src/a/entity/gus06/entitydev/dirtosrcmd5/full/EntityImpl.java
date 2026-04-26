package a.entity.gus06.entitydev.dirtosrcmd5.full;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150304";}
	

	private Service dirToSrc;
	private Service srcToMd5;
	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		dirToSrc = Outside.service(this,"gus06.entitydev.dirtosrc.full");
		srcToMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) dirToSrc.t(obj);
		return srcToMd5.t(src);
	}
}
