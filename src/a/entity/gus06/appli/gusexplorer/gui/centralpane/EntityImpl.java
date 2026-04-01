package a.entity.gus06.appli.gusexplorer.gui.centralpane;

import a.framework.*;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20200402";}


	private Service guiTabbedPane;
	private Service handleDnd;
	private Service shiftPanel;
	
	private JTabbedPane tabbedPane;
	private JComponent displayedComp;
	private E compResetter;
	
	
	public EntityImpl() throws Exception
	{
    		guiTabbedPane = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane");
		handleDnd = Outside.service(this,"gus06.appli.gusexplorer.gui.centralpane.dnd");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		
		tabbedPane = (JTabbedPane) guiTabbedPane.i();
		handleDnd.p(tabbedPane);
		
		shiftPanel.p(tabbedPane);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void e() throws Exception
	{
		if(compResetter!=null) compResetter.e();
		
		displayedComp = null;
		compResetter = null;
		shiftPanel.p(tabbedPane);
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(compResetter!=null) compResetter.e();
		
		displayedComp = (JComponent) o[0];
		compResetter = (E) o[1];
		
		shiftPanel.p(displayedComp);
	}
}
