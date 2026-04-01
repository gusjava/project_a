package a.entity.gus06.sys.webserver1.web2.site.pseudo.retrieve.publickey;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141016";}


	private Service selectPseudo;
	private Service mapToKey;

	

	public EntityImpl() throws Exception
	{
		selectPseudo = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.jdbc.select.pseudo");
		mapToKey = Outside.service(this,"gus06.sys.crypto.pseudo.build.publickey");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map m = (Map) selectPseudo.t(obj);
		return mapToKey.t(m);
	}
}
