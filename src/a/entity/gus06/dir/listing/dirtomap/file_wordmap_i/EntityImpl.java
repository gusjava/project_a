package a.entity.gus06.dir.listing.dirtomap.file_wordmap_i;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210716";}


	private Service countTexts;
	private Service isTextFile;

	public EntityImpl() throws Exception
	{
		countTexts = Outside.service(this,"gus06.file.string.freqmap.counttexts_i");
		isTextFile = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String[] words = (String[]) o[1];
		
		Map map = new HashMap();
		handle(dir, map, words);
		return map;
	}
	
	
	private Object handle(File file, Map map, String[] words) throws Exception
	{
		Object value = computeValue(file,map,words);
		if(value!=null) map.put(file, value);
		return value;
	}
	
	
	
	private Object computeValue(File file, Map map, String[] words) throws Exception
	{
		if(!file.exists()) return null;
		if(file.isFile()) return computeValue_file(file, words);
		return computeValue_dir(file, map, words);
	}
	
	
	private Object computeValue_file(File file, String[] words) throws Exception
	{
		if(isTextFile.f(file))
			return (Object) countTexts.t(new Object[]{file,words});
		return null;
	}
	
	
	private Object computeValue_dir(File dir, Map map, String[] words) throws Exception
	{
		Map value = new HashMap();
		
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			Object value0 = handle(f, map, words);
			mergeValues(value, value0);
		}
		return value;
	}
	
	
	
	
	
	private void mergeValues(Object value, Object value0)
	{
		Map m = (Map) value;
		Map m0 = (Map) value0;
		
		Iterator it = m0.keySet().iterator();
		while(it.hasNext())
		{
			String word = (String) it.next();
			Integer n = (Integer) m0.get(word);
			increaseNumber(m, word, n.intValue());
		}
	}
	
	
	private void increaseNumber(Map map, String word, int count)
	{
		if(!map.containsKey(word))
		{
			map.put(word, Integer.valueOf(count));
			return;
		}
		Integer n0 = (Integer) map.get(word);
		map.put(word, Integer.valueOf(count+n0.intValue()));
	}
}