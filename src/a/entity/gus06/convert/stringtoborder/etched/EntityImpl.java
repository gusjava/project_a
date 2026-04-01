package a.entity.gus06.convert.stringtoborder.etched;

import a.framework.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}



	private Service stringToColor;

	public EntityImpl() throws Exception
	{stringToColor = Outside.service(this,"gus06.convert.stringtocolor");}


	public Object t(Object obj) throws Exception
	{return build((String) obj);}
	
	
	
	private Border build(String rule) throws Exception
	{
		if(rule==null) return BorderFactory.createEtchedBorder();
		String[] n = rule.split(" ");
		
		if(n.length==1) return BorderFactory.createEtchedBorder(int_(n[0]));
		if(n.length==2) return BorderFactory.createEtchedBorder(color(n[0]),color(n[1]));
		if(n.length==3) return BorderFactory.createEtchedBorder(int_(n[0]),color(n[1]),color(n[2]));
		
		throw new Exception("Invalid etched border rule: "+rule);
	}
	
	
	private Color color(String s) throws Exception
	{return (Color) stringToColor.t(s);}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
