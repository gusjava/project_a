package a.entity.gus06.file.class1.jdkversion;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140807";}


	private Service classVersion;
	private Service mapping;

	public EntityImpl() throws Exception
	{
		classVersion = Outside.service(this,"gus.x.file.class1.classversion");
		mapping = Outside.service(this,"gus06.java.jdk.versionmapping");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] v = (int[]) classVersion.t(obj);
		int majorVersion = v[0];
    	
		return mapping.t(""+majorVersion);
	}
}
