package a.entity.gus06.sys.autocomplete1.editor1.color;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import javax.swing.JColorChooser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160521";}
	
	public static final String REGEX_HTML1 = "#[0-9a-fA-F]{6}";
	public static final String REGEX_HTML2 = "#[0-9a-fA-F]{3}";
	
	public static final String REGEX_JAVA1 = "Color\\.[A-Z_]+";
	public static final String REGEX_JAVA2 = "new Color\\([^\\)]+\\)";
	
	public static final String REGEX_SEQUENCE = "([^0-9]*)([0-9]+)([^0-9]+)([0-9]+)([^0-9]+)([0-9]+)([^0-9]*)";

	public static final Pattern P_INT = Pattern.compile("[0-9]+");
	
	

	private Service stringToColor;
	private Service colorToHtml;
	private Service colorToJava;

	
	public EntityImpl() throws Exception
	{
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor");
		colorToHtml = Outside.service(this,"gus06.convert.colortostring.html");
		colorToJava = Outside.service(this,"gus06.convert.colortostring.javacode");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text0 = comp.getSelectedText();
		if(text0==null) return;
		Color c = chooseColor(text0);
		if(c==null) return;
		
		replace(comp, text0, c);
	}
	
	
	private T findFormatter(String text)
	{
		if(text.matches(REGEX_HTML1)) return colorToHtml;
		if(text.matches(REGEX_HTML2)) return colorToHtml;
		if(text.matches(REGEX_JAVA1)) return colorToJava;
		if(text.matches(REGEX_JAVA2)) return colorToJava;
		if(text.matches(REGEX_SEQUENCE)) return tSeq(text);
		
		return colorToJava;
	}
	
	
	private void replace(JTextComponent comp, String text0, Color c) throws Exception
	{
		T formatter = findFormatter(text0);
		String text1 = (String) formatter.t(c);
		
		int start = comp.getSelectionStart();
		comp.replaceSelection(text1);
		comp.select(start,start+text1.length());
	}
	
	
	private Color chooseColor(String s) throws Exception
	{
		Color color = (Color) stringToColor.t(s);
		return JColorChooser.showDialog(null,"Choose color",color);
	}
	
	
	private T tSeq(final String text0)
	{
		return new T() {
			public Object t(Object obj) throws Exception
			{
				Color c = (Color) obj;
				int[] rgb = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
				
				int i=0;
				StringBuffer b = new StringBuffer();
				Matcher m = P_INT.matcher(text0);
				while(m.find())
				{
					m.appendReplacement(b,""+rgb[i]);
					i++;
				}
				m.appendTail(b);
				return b.toString();
			}
		};
	}
}
