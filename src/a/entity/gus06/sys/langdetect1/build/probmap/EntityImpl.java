package a.entity.gus06.sys.langdetect1.build.probmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160719";}


	private Service inside;
	private Service langList;
	
	private Map map;


	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"gus06.app.inside.langdetect");
		langList = Outside.service(this,"gus06.sys.langdetect1.build.langlist");
	}
	
	
	public Object g() throws Exception
	{
		if(map==null) init();
		return map;
	}
	
	
	private void init() throws Exception
	{
		List list = (List) langList.g();
		int langNb = list.size();
		
		map = new HashMap();
		
		for(int i=0;i<langNb;i++)
		{
			String lang = (String) list.get(i);
			Map p = (Map) inside.t(lang);
			
			int n1 = int_((String) p.remove("1"));
            		int n2 = int_((String) p.remove("2"));
			int n3 = int_((String) p.remove("3"));
	            
			int[] n_words = new int[]{n1,n2,n3};
			
			for (Object o: p.keySet())
			{
				String word = (String) o;
				if(!map.containsKey(word))
					map.put(word, new double[langNb]);
	                
				int length = word.length();
				int wordOcc = int_((String) p.get(word));
				int totalOcc = n_words[length - 1];
	                
				double prob = (double) wordOcc / totalOcc;
				((double[]) map.get(word))[i] = prob;
			}
		}
	}
    
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
