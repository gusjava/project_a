package a.entity.gus06.find.jpanel;

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

	public String creationDate() {return "20191124";}


	private Service colorToPanel;
	private Service imageToPanel;
	private Service urlToPanel;
	private Service mapToPanel;
	
	public EntityImpl() throws Exception
	{
		colorToPanel = Outside.service(this,"gus06.convert.colortojpanel");
		imageToPanel = Outside.service(this,"gus06.convert.imagetojpanel");
		urlToPanel = Outside.service(this,"gus06.convert.urltojpanel");
		mapToPanel = Outside.service(this,"gus06.swing.panel.build.frommap");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Color) return colorToPanel.t(obj);
		if(obj instanceof Image) return imageToPanel.t(obj);
		if(obj instanceof RenderedImage) return imageToPanel.t(obj);
		if(obj instanceof URL) return urlToPanel.t(obj);
		if(obj instanceof Map) return mapToPanel.t(obj);
		
		if(obj instanceof JComponent) return compToJPanel((JComponent) obj);
		if(obj instanceof I) return compToJPanel((JComponent) ((I)obj).i());
		if(obj instanceof Object[][]) return array2ToJPanel((Object[][]) obj);
		if(obj instanceof Object[]) return arrayToJPanel((Object[]) obj);
		if(obj instanceof List) return listToJPanel((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JPanel compToJPanel(JComponent comp)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(comp,BorderLayout.CENTER);
		return p;
	}
	
	
	private JPanel arrayToJPanel(Object[] array)
	{
		int nb = array.length;
		JPanel p = new JPanel(new GridLayout(1,nb));
		for(int i=0;i<nb;i++)
		{
			JComponent comp = (JComponent) array[i];
			p.add(comp);
		}
		return p;
	}
	
	
	private JPanel array2ToJPanel(Object[][] array2)
	{
		int nb1 = array2.length;
		int nb2 = nb1>0 ? array2[0].length : 0;
		
		JPanel p = new JPanel(new GridLayout(nb1,nb2));
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			JComponent comp = (JComponent) array2[i][j];
			p.add(comp);
		}
		return p;
	}
	
	
	private JPanel listToJPanel(List list)
	{
		int nb = list.size();
		JPanel p = new JPanel(new GridLayout(1,nb));
		for(int i=0;i<nb;i++)
		{
			JComponent comp = (JComponent) list.get(i);
			p.add(comp);
		}
		return p;
	}
}