package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel1;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, I, P, V, ActionListener {

	public String creationDate() {return "20201108";}


	private Service resultPanel;
	private Service detailPanel;
	
	private JSplitPane split;
	
	private Object engine;
	private List resultList;
	

	public EntityImpl() throws Exception
	{
		resultPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel");
		detailPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(600);
		
		split.setLeftComponent((JComponent) resultPanel.i());
		split.setRightComponent((JComponent) detailPanel.i());
		
		resultPanel.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		resultList = (List) o[1];
		
		resultPanel.p(resultList);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{resultPanel.v(key,obj);}
	
	
	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{
		try
		{
			if(engine==null || resultList==null) {resetDetails();return;}
			
			Map selected = (Map) resultPanel.g();
			detailPanel.p(new Object[]{engine,selected});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private void reset() throws Exception
	{
		engine = null;
		resultList = null;
		
		resultPanel.p(null);
		detailPanel.p(null);
	}
	
	
	private void resetDetails() throws Exception
	{detailPanel.p(null);}

}