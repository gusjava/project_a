package a.entity.gus06.sys.expression1.apply.op._jpanel_titled;

import a.framework.*;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.util.List;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Map;
import java.awt.image.RenderedImage;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231130";}


	private Service find;
	private Service titled;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.jpanel");
		titled = Outside.service(this,"gus06.swing.comp.build.titledpanel");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return new T1(find(obj));
		if(obj instanceof Image) return new T1(find(obj));
		if(obj instanceof RenderedImage) return new T1(find(obj));
		if(obj instanceof URL) return new T1(find(obj));
		if(obj instanceof Map) return new T1(find(obj));
		
		if(obj instanceof JComponent) return new T1((JComponent) obj);
		if(obj instanceof I) return new T1((JComponent) ((I)obj).i());
		if(obj instanceof Object[][]) return new T1(find(obj));
		if(obj instanceof Object[]) return new T1(find(obj));
		if(obj instanceof List) return new T1(find(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private JComponent find(Object obj) throws Exception
	{return (JComponent) find.t(obj);}
	
	private class T1 implements T
	{
		JComponent comp;
		public T1(JComponent comp) {this.comp = comp;}
		
		public Object t(Object obj) throws Exception
		{return titled.t(new Object[]{comp,obj});}
	}
}