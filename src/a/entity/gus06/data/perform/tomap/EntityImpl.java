package a.entity.gus06.data.perform.tomap;

import a.framework.*;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.io.File;
import java.util.prefs.Preferences;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}


	private Service rToMap;
	private Service tToMap;
	private Service fromList;
	private Service fromSet;
	private Service fromArray;
	private Service fromString;
	private Service fromFile;
	private Service fromPrefs;
	
	
	public EntityImpl() throws Exception
	{
		rToMap = Outside.service(this,"gus06.convert.rtomap");
		tToMap = Outside.service(this,"gus06.convert.ttomap");
		fromList = Outside.service(this,"gus06.map.build.fromlist");
		fromSet = Outside.service(this,"gus06.map.build.fromset");
		fromArray = Outside.service(this,"gus06.map.build.fromarray");
		fromString = Outside.service(this,"gus06.convert.stringtomap");
		fromFile = Outside.service(this,"gus06.file.read.properties.generic");
		fromPrefs = Outside.service(this,"gus06.convert.preferencestomap");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Map) return obj;
		if(obj instanceof Set) return fromSet.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		if(obj instanceof Object[]) return fromArray.t(obj);
		if(obj instanceof R) return rToMap.t(obj);
		if(obj instanceof T) return tToMap.t(obj);
		if(obj instanceof String) return fromString.t(obj);
		if(obj instanceof File) return fromFile.t(obj);
		if(obj instanceof Preferences) return fromPrefs.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
