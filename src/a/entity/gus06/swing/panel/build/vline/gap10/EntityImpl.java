package a.entity.gus06.swing.panel.build.vline.gap10;

import a.framework.*;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140806";}

	public static final int GAP = 10;
	
	
	
	public Object t(Object obj) throws Exception
	{return nnc((Object[]) obj);}
	
	
	
	private JPanel nnc(Object[] n) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		for(int i=0;i<n.length;i++)
		p = nc(toComp(n[n.length-1-i]),gap(p));
		return p;
	}
    
    
	private JPanel nc(JComponent n, JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(n,BorderLayout.NORTH);
		p.add(c,BorderLayout.CENTER);
		return p;
	}
    
    
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof String) return new JLabel((String) obj);
    	
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
    
    
	private JComponent gap(JComponent c)
	{
		c.setBorder(BorderFactory.createEmptyBorder(GAP,0,0,0));
		return c;
	}
}
