package a.entity.gus06.app.persister1.comparator1.recent;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, R, V {

	public String creationDate() {return "20160510";}
	
	public static final int LIMIT_RECENT = 4;


	private Service persistList;
	private Service listToComparator;


	public EntityImpl() throws Exception
	{
		persistList = Outside.service(this,"gus06.app.persister1.data.list");
		listToComparator = Outside.service(this,"gus06.convert.listtocomparator");
	}
	
	
	public Object r(String key) throws Exception
	{
		List l = findList(key);
		return listToComparator.t(l);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		List l = findList(key);
		l.remove(obj);
		l.add(obj);
		persistList.v(key,l);
	}
	
	private List findList(String key) throws Exception
	{
		List l = (List) persistList.r(key);
		if(l==null) return new ArrayList();
		return l.subList(0, Math.min(l.size(),LIMIT_RECENT));
	}
}
