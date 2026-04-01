package a.entity.gus06.sys.git1.gui.panel1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201126";}
	
	
	private Service listViewer;
	private Service splitCust;
	
	private JPanel panel;
	private JSplitPane split;
	
	private Object git;
	private List branchList;
	private Map countMap;


	public EntityImpl() throws Exception
	{
		listViewer = Outside.service(this,"*gus06.sys.countmap1.gui.maingui");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) listViewer.i());
//		split.setRightComponent(null);
		splitCust.p(split);
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		git = obj;
		if(git==null) {reset();return;}
		
		countMap = new HashMap();
		branchList = (List) ((R)git).r("branchsLocal");
		for(int i=0;i<branchList.size();i++)
		{
			String branchName = (String) branchList.get(i);
			List commits = (List) ((R)git).r("commitsForBranch:"+branchName);
			countMap.put(branchName,commits.size());
		}
		
		listViewer.p(countMap);
	}
	
	
	
	private void reset() throws Exception
	{
		listViewer.p(null);
	}
}