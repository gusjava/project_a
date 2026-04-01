package a.entity.gus06.string.count.substrings_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}


	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String str = (String) normalize.t(o[0]);
		String findStr = (String) normalize.t(o[1]);
		
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1)
		{
			lastIndex = str.indexOf(findStr,lastIndex);
			if(lastIndex != -1)
			{
				count ++;
				lastIndex += findStr.length();
			}
		}

		return Integer.valueOf(count);
	}
}