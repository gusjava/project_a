package a.entity.gus06.appli.gusexplorer.execute.tools.colors.capture;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20201213";}
	

	
	private Service before;
	private Service captureColor;
	private Service clipboard;
	private Service colorToString;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureColor = Outside.service(this,"gus06.sys.capturecolor1.capture");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		colorToString = Outside.service(this,"gus06.convert.colortostring.rgb");
	}
	
	public void e() throws Exception
	{
		Color color = findColor();
		if(color!=null)
		{
			String s = (String) colorToString.t(color);
			clipboard.p(s);
		}
	}
	
	private Color findColor() throws Exception
	{
		before.e();
		return (Color) captureColor.g();
	}
}