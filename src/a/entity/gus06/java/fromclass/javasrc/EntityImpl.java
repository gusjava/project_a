package a.entity.gus06.java.fromclass.javasrc;

import a.framework.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140804";}


	private Service asResource;
	private Service asSrcZip;
	
	public EntityImpl() throws Exception
	{
		asResource = Outside.service(this,"gus06.java.fromclass.javasrc.resource");
		asSrcZip = Outside.service(this,"gus06.java.fromclass.javasrc.srczip");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src1 = (String) asResource.t(obj);
		if(src1!=null) return src1;
		
		String src2 = (String) asSrcZip.t(obj);
		if(src2!=null) return src2;
		
		return null;
	}
}
