package a.entity.gus06.swing.tabbedpane.build.closeable.buildtab;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, R, T {

	public String creationDate() {return "20141203";}


	private Service repaintLabel;
	
	public EntityImpl() throws Exception
	{repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");}


	public Object t(Object obj) throws Exception
	{return toComp(obj);}
	
	public Object r(String key) throws Exception
	{return toComp(key);}
	
	
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I)obj).i();
		if(obj instanceof String) return new TabLabel((String)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class TabLabel extends JLabel
	{
		public TabLabel(String display) throws Exception
		{
			super();
			repaintLabel.v(display,this);
		}
	}
}

