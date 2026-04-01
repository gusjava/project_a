package a.entity.gus06.sys.analyzejsf1.analyzer;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190308";}
	

	private Service scanJavaDir;
	private Service scanWebappDir;
	private Service scanResourcesDir;
	private Service buildRoots;
	private Service buildPages;
	private Service buildTools;
	private Service initMap;


	public EntityImpl() throws Exception
	{
		scanJavaDir = Outside.service(this,"gus06.sys.analyzejsf1.scan.javadir");
		scanWebappDir = Outside.service(this,"gus06.sys.analyzejsf1.scan.webappdir");
		scanResourcesDir = Outside.service(this,"gus06.sys.analyzejsf1.scan.resourcesdir");
		buildRoots = Outside.service(this,"gus06.sys.analyzejsf1.build.roots");
		buildPages = Outside.service(this,"gus06.sys.analyzejsf1.build.pages");
		buildTools = Outside.service(this,"gus06.sys.analyzejsf1.build.tools");
		initMap = Outside.service(this,"gus06.sys.analyzejsf1.initmap");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) initMap.t(obj);
		
		buildRoots.p(map);
		scanResourcesDir.p(map);
		scanJavaDir.p(map);
		scanWebappDir.p(map);
		buildPages.p(map);
		buildTools.p(map);
		
		return map;
	}
}
