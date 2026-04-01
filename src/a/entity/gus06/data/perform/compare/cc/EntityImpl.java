package a.entity.gus06.data.perform.compare.cc;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}


	private Service compare1;
	private Service compare2;

	public EntityImpl() throws Exception
	{
		compare1 = Outside.service(this,"gus06.data.compare.o1");
		compare2 = Outside.service(this,"gus06.data.compare.o2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List compareList = (List) o[0];
		List returnList = (List) o[1];
		
		if(compareList.size()!=2)
			throw new Exception("Invalid compareList size: "+compareList.size());
		
		Object a = compareList.get(0);
		Object b = compareList.get(1);
		
		if(returnList.size()==2)
		{
			return compare1(a,b) ? returnList.get(0) : returnList.get(1);
		}
		if(returnList.size()==3)
		{
			int r = compare2(a,b);
			switch(r) {
				case 1:return returnList.get(0);
				case 0:return returnList.get(1);
				case -1:return returnList.get(2);
			}
		}
		
		throw new Exception("Invalid returnList size: "+returnList.size());
	}
	
	
	private boolean compare1(Object a, Object b) throws Exception
	{return compare1.f(new Object[]{a,b});}
	
	
	private int compare2(Object a, Object b) throws Exception
	{return ((Integer) compare2.t(new Object[]{a,b})).intValue();}
}
