package a.entity.gus06.swing.panel.build.vline;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[]) return nnc((Object[]) obj);
		if(obj instanceof List) return nnc((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}


	
	private JPanel nnc(Object[] n) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		for(int i=0;i<n.length;i++)
		p = nc(toComp(n[n.length-1-i]),p);
		return p;
	}
	
	private JPanel nnc(List l) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		for(int i=0;i<l.size();i++)
		p = nc(toComp(l.get(l.size()-1-i)),p);
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
}