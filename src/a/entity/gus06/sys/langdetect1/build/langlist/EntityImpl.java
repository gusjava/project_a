package a.entity.gus06.sys.langdetect1.build.langlist;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160719";}

	public static final String NAME = "langdetect";
	public static final String PROPKEY = "langdetect1";

	private Service findKeys;
	private Service getProp;
	
	private List list;


	public EntityImpl() throws Exception
	{
		findKeys = Outside.service(this,"gus06.app.jarfile.listing.resources.findkeys.forname");
		getProp = Outside.service(this,"gus06.app.prop.get");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	private void init() throws Exception
	{
		String prop = (String) getProp.r(PROPKEY);
		if(prop!=null) list = Arrays.asList(prop.split(";"));
		else list = (List) findKeys.t(NAME);
	}
}
