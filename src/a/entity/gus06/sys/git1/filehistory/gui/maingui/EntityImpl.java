package a.entity.gus06.sys.git1.filehistory.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20201128";}


	private Service gitBuilder;
	private Service commitList;
	private Service commitDetail;
	private Service buildCurrent;
	
	private JPanel panel;
	private JSplitPane split;
	
	private File file;
	private Object git;
	private List commits;

	public EntityImpl() throws Exception
	{
		gitBuilder = Outside.service(this,"gus06.sys.git1.builder");
		commitList = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitlist");
		commitDetail = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitdetail");
		buildCurrent = Outside.service(this,"gus06.sys.git1.tool.commit.build.current");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(500);
		
		split.setLeftComponent((JComponent) commitList.i());
		split.setRightComponent((JComponent) commitDetail.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		
		commitList.addActionListener(this);
		commitDetail.v("selectionHandler",(P) obj->select((String) obj));
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile()) {reset();return;}
		
		git = gitBuilder.t(file);
		if(git==null) {reset();return;}
		
		commits = (List) ((R)git).r("commitsForFile1:"+file);
		commits.add(0,buildCurrent(file));
		
		commitList.p(commits);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select"))  {select((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private Map buildCurrent(File file) throws Exception
	{return (Map) buildCurrent.t(file);}
	
	
	
	private void reset() throws Exception
	{
		file = null;
		git = null;
		commits = null;
		
		commitList.p(null);
		commitDetail.p(null);
	}
	
	
	private void select(String selection) throws Exception
	{
		if(selection==null || selection.equals("")) return;
		String query = ">'"+selection;
		commitList.v("search",query);
	}
	


	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	private void selected()
	{
		try
		{
			if(commits==null) return;
			
			Object commit = commitList.g();
			List commitsF = (List) commitList.r("commitsF");
			
			if(commit==null) commitDetail.p(null);
			else commitDetail.p(new Object[]{commitsF,commit});
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}