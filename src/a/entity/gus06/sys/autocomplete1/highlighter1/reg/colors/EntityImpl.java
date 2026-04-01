package a.entity.gus06.sys.autocomplete1.highlighter1.reg.colors;

import a.framework.*;
import javax.swing.text.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170226";}
	
	public static final String REGEX_HTML1 = "(#[0-9a-fA-F]{6})[^0-9a-fA-F]";
	public static final String REGEX_HTML2 = "(#[0-9a-fA-F]{3})[^0-9a-fA-F]";
	
	public static final String REGEX_JAVA1 = "Color\\.[A-Z_]+";
	public static final String REGEX_JAVA2 = "new Color\\([^\\)]+\\)";
	
	public static final Pattern P_REGEX_HTML1 = Pattern.compile(REGEX_HTML1);
	public static final Pattern P_REGEX_HTML2 = Pattern.compile(REGEX_HTML2);
	
	public static final Pattern P_REGEX_JAVA1 = Pattern.compile(REGEX_JAVA1,Pattern.DOTALL);
	public static final Pattern P_REGEX_JAVA2 = Pattern.compile(REGEX_JAVA2,Pattern.DOTALL);



	private Service buildPainter;
	private Service stringToColor;

	public EntityImpl() throws Exception
	{
		buildPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.builder1");
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor");
	}

	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Highlighter high = comp.getHighlighter();
		String text = comp.getText();
		
		List list = new ArrayList();
		
		Matcher m_html1 = P_REGEX_HTML1.matcher(text);
		while(m_html1.find()) highlight(list,high,m_html1,1);
		
		Matcher m_html2 = P_REGEX_HTML2.matcher(text);
		while(m_html2.find()) highlight(list,high,m_html2,1);
		
		Matcher m_java1 = P_REGEX_JAVA1.matcher(text);
		while(m_java1.find()) highlight(list,high,m_java1,0);
		
		Matcher m_java2 = P_REGEX_JAVA2.matcher(text);
		while(m_java2.find()) highlight(list,high,m_java2,0);
		
		return list;
	}
	
	
	
	
	
	private void highlight(List list, Highlighter high, Matcher m, int index) throws Exception
	{
		int start = m.start(index);
		int end = m.end(index);
		String group = m.group(index);
		
		Color color = (Color) stringToColor.t(group);
		addColor(list,high,color,start,end);
	}
	
	
	private void addColor(List list, Highlighter high, Color color, int start, int end) throws Exception
	{
		if(color==null) return;
		
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) buildPainter.t(color);
		high.addHighlight(start,end,painter);
		list.add(painter);
	}
	
}
