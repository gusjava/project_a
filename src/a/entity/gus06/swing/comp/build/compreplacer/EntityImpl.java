package a.entity.gus06.swing.comp.build.compreplacer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200402";}


	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Component is null");
		if(!(obj instanceof JComponent)) throw new Exception("Invalid data type: "+obj.getClass().getName());
		
		JComponent comp = (JComponent) obj;
		Container parent = comp.getParent();
		
		if(parent==null) return new E(){public void e() throws Exception {}};
		
		if(parent instanceof JPanel)
		{
			JPanel p = (JPanel)parent;
			LayoutManager layout = p.getLayout();
			
			if(layout instanceof BorderLayout)
				return new Holder_PanelBorderLayout(p,comp);
			return new Holder_PanelGridLayout(p,comp);
		}
		if(parent instanceof JTabbedPane)
		{
			JTabbedPane p = (JTabbedPane)parent;
			return new Holder_TabbedPane(p,comp);
		}
		if(parent instanceof JSplitPane)
		{
			JSplitPane p = (JSplitPane)parent;
			return new Holder_SplitPane(p);
		}
		if(parent instanceof JViewport)
		{
			JViewport p = (JViewport)parent;
			return new Holder_Viewport(p,comp);
		}
		if(parent instanceof JLayeredPane)
		{
			JLayeredPane p = (JLayeredPane)parent;
			return new Holder_LayeredPane(p,comp);
		}
		throw new Exception("Parent type not supported: "+parent.getClass().getName());
	}
}
