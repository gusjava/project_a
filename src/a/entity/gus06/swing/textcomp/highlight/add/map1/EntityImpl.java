package a.entity.gus06.swing.textcomp.highlight.add.map1;

import a.framework.*;
import java.util.Map;
import java.util.List;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180222";}
	
	public static final String KEY_START = "start";
	public static final String KEY_END = "end";
	public static final String KEY_LENGTH = "length";
	public static final String KEY_PAINTER = "painter";


	private Service findPainter;
	private Service convert;
	private Service findInt2;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
		convert = Outside.service(this,"gus06.data.perform.coord.xytolen");
		findInt2 = Outside.service(this,"gus06.find.intarray.len2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Map map = (Map) o[1];
		
		int start = findStart(comp,map);
		int end = findEnd(comp,map);
		Highlighter.HighlightPainter painter = findPainter(map);
		
		Highlighter high = comp.getHighlighter();
                high.addHighlight(start,end,painter);
	}
	
	
	
	private int findStart(JTextComponent comp, Map map) throws Exception
	{
		Object startObj = get(map,KEY_START);
		if(startObj!=null) return buildPos(comp,startObj);
		
		Object endObj = get(map,KEY_END);
		if(endObj==null) throw new Exception("Start & end not found");
		
		Object lengthObj = get(map,KEY_LENGTH);
		if(lengthObj==null) throw new Exception("Start & length not found");
		
		int end = buildPos(comp,endObj);
		int length = toInt(lengthObj);
		
		return end-length;
	}
	
	
	
	private int findEnd(JTextComponent comp, Map map) throws Exception
	{
		Object endObj = get(map,KEY_END);
		if(endObj!=null) return buildPos(comp,endObj);
		
		Object startObj = get(map,KEY_START);
		if(startObj==null) throw new Exception("End & start not found");
		
		Object lengthObj = get(map,KEY_LENGTH);
		if(lengthObj==null) throw new Exception("End & length not found");
		
		int start = buildPos(comp,startObj);
		int length = toInt(lengthObj);
		
		return start+length;
	}
	
	
	private Highlighter.HighlightPainter findPainter(Map map) throws Exception
	{
		if(!map.containsKey(KEY_PAINTER)) throw new Exception("Painter not found inside map");
		Object obj = map.get(KEY_PAINTER);
		return (Highlighter.HighlightPainter) findPainter.t(obj);
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private int buildPos(JTextComponent comp, Object obj) throws Exception
	{
		if(obj instanceof Integer || obj instanceof String)
		{
			int n = toInt(obj);
			if(n>=0) return n;
			return comp.getText().length()+n;
		}
		if(obj instanceof int[])
		{
			Integer n = (Integer) convert.t(new Object[]{comp,obj});
			return n.intValue();
		}
		if(obj instanceof List)
		{
			int[] point = (int[]) findInt2.t(obj);
			Integer n = (Integer) convert.t(new Object[]{comp,point});
			return n.intValue();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int toInt(Object obj)
	{
		return Integer.parseInt(""+obj);
	}
}