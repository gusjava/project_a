package a.entity.gus06.appli.entityaccess.remote.md5list.other;

import a.framework.*;
import java.util.Arrays;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150815";}


	private Service sender;
	private Service buildMd5List;
	
	public EntityImpl() throws Exception
	{
		sender = Outside.service(this,"gus06.appli.entityaccess.api.sender");
		buildMd5List = Outside.service(this,"gus06.appli.entityaccess.api.build.md5list");
	}
	
	
	
	public Object g() throws Exception
	{
		String result = (String) sender.t("md5:other");
		if(result.startsWith("error:")) return result;
		return buildMd5List.t(result);
	}
}
