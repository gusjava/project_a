package a.entity.gus06.swing.textcomp.highlight.search.map1;

import a.framework.*;
import java.util.Map;
import java.util.List;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190406";}
	
	public static final String KEY_OPTIONS = "options";
	public static final String KEY_SEARCH = "search";
	public static final String KEY_PAINTER = "painter";


	private Service findPainter;
	private Service normalize;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Map map = (Map) o[1];
		
		Highlighter.HighlightPainter painter = findPainter(map);
		Highlighter high = comp.getHighlighter();
		
		String search = get1(map,KEY_SEARCH);
		String options = get(map,KEY_OPTIONS,"");
		String text = comp.getText();
		
		if(options.equals("n"))
		{
			search = (String) normalize.t(search);
			text = (String) normalize.t(text);
		}
		else if(options.equals("i"))
		{
			search = search.toLowerCase();
			text = text.toLowerCase();
		}
                
		Pattern p = Pattern.compile(Pattern.quote(search),Pattern.DOTALL);
                Matcher m = p.matcher(text);
                
                while(m.find())
                {
                    int start = m.start();
                    int end = m.end();
                    high.addHighlight(start,end,painter);
                }
	}
	
	
	
	private Highlighter.HighlightPainter findPainter(Map map) throws Exception
	{
		if(!map.containsKey(KEY_PAINTER)) throw new Exception("Painter not found inside map");
		Object obj = map.get(KEY_PAINTER);
		return (Highlighter.HighlightPainter) findPainter.t(obj);
	}
	
	
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}