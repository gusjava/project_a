package a.entity.gus06.sys.autocomplete1.highlighter1.lib.map;

import a.framework.*;
import javax.swing.text.*;
import java.util.Map;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160603";}

	public static final String PAINTER_KEY = "f1";


	private Service getPainter;
	private Service getMap;
	
	private DefaultHighlighter.DefaultHighlightPainter painter;


	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		getMap = Outside.service(this,"gus06.sys.clipboard1.g.map");
		
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Map map = (Map) getMap.g();
		
		if(map==null || map.isEmpty()) return;
		
		Highlighter high = comp.getHighlighter();
		String text = comp.getText();
		
		Iterator it = buildSet(map).iterator();
		while(it.hasNext())
		handle(high,text,(String) it.next());
	}
	
	
	
	private Set buildSet(Map map)
	{
		Set set = new HashSet();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			set.add(key);
			set.add(value);
		}
		return set;
	}
	
	
	
	private void handle(Highlighter high, String text, String exp) throws Exception
	{
		Pattern p = Pattern.compile(Pattern.quote(exp),Pattern.DOTALL);
                Matcher m = p.matcher(text);
                
                while(m.find())
                {
                    int start = m.start();
                    int end = m.end();
                    high.addHighlight(start,end,painter);
                }
	}
}
