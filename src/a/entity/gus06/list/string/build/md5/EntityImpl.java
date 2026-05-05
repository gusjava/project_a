package a.entity.gus06.list.string.build.md5;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191222";}

	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList((Collection) obj);
		Collections.sort(list);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++) b.append(list.get(i)+"\n");
		
		return buildMd5.t(b.toString());
	}
}
