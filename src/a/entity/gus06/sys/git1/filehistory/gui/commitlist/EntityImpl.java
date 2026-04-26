package a.entity.gus06.sys.git1.filehistory.gui.commitlist;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JToolBar;
import javax.swing.Action;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, R, V {

	public String creationDate() {return "20201129";}
	
	public static final String DISPLAY_NORM1 = "GIT_srcN#Normalized";
	public static final String DISPLAY_NORM2 = "GIT_srcN2#Not normalized";
	public static final String DISPLAY_RESTORE = "GIT_src_restore#Restore";


	private Service fieldGui;
	private Service tableGui;
	private Service filterCommit;
	private Service linker;
	private Service handleCommitsSrc;
	private Service handleCommitsSrcN;
	private Service buildToolbar;
	private Service buildAction;
	private Service paintAction;
	private Service restore;
	private Service confirm;
	
	private JLabel label;
	private JPanel panel;
	private JTable table;
	private JComponent field;
	
	private Action actionShiftN;
	private Action actionRestore;
	
	private boolean normalized = true;
	
	private List commits;
	private List commitsF;
	
	private Map selection;
	


	public EntityImpl() throws Exception
	{
		fieldGui = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		tableGui = Outside.service(this,"*gus06.sys.git1.filehistory.gui.commitlist.table");
		filterCommit = Outside.service(this,"gus06.sys.git1.tool.commit.build.filter");
		linker = Outside.service(this,"gus.x.swing.table.textfield.linker");
		handleCommitsSrc = Outside.service(this,"gus06.sys.git1.filehistory.handle.commits.src");
		handleCommitsSrcN = Outside.service(this,"gus06.sys.git1.filehistory.handle.commits.srcn");
		buildToolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		paintAction = Outside.service(this,"gus06.swing.action.cust2.display");
		restore = Outside.service(this,"gus06.sys.git1.tool.commit.restore");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		
		actionShiftN = (Action) buildAction.t(new Object[]{DISPLAY_NORM1,(E)this::shiftN});
		actionRestore = (Action) buildAction.t(new Object[]{DISPLAY_RESTORE,(E)this::restore});
		
		actionRestore.setEnabled(false);
		
		table = (JTable) tableGui.i();
		field = (JComponent) fieldGui.i();
		label = new JLabel(" ");
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		JToolBar bar = (JToolBar) buildToolbar.i();
		bar.add(actionRestore);
		bar.add(actionShiftN);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(label,BorderLayout.CENTER);
		bottomPanel.add(bar,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
		fieldGui.addActionListener(this);
		linker.p(new Object[]{table,field});
		
		tableGui.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selectionChanged();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{return tableGui.g();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("commitsF")) return commitsF;
		if(key.equals("commits")) return commits;
		if(key.equals("keys")) return new String[]{"commitsF","commits"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		commits = (List) obj;
		refresh();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("search")) {search((String) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			String input = (String) fieldGui.g();
			updateWithQuery(input);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	private void updateWithQuery(String query) throws Exception
	{
		if(commits==null){reset();return;}
		
		F filter = (F) filterCommit.t(query);
		if(filter!=null)
		{
			commitsF = new ArrayList();
			for(int i=0;i<commits.size();i++)
			{
				Map m = (Map) commits.get(i);
				if(filter.f(m))
				{
					Map m1 = new HashMap(m);
					m1.put("query",query);
					commitsF.add(m1);
				}
			}
		}
		else commitsF = new ArrayList(commits);
		
		if(normalized) handleCommitsSrcN.p(commitsF);
		else handleCommitsSrc.p(commitsF);
		
		updateLabel();
		tableGui.p(commitsF);
	}


	
	
	private void reset() throws Exception
	{
		commits = null;
		commitsF = null;
		updateLabel();
		tableGui.p(null);
	}

	
	
	private void updateLabel()
	{
		if(commits==null) 
			label.setText(numberDisplay());
		else if(commitsF==null || commitsF.size()==commits.size()) 
			label.setText(" Number: "+commits.size());
		else label.setText(" Number: "+commitsF.size()+"/"+commits.size());
	}
	
	private String numberDisplay()
	{
		if(commits==null) return " ";
		int size = commits.size();
		if(commitsF==null) return " Number: "+size;
		int sizeF = commitsF.size();
		if(size==sizeF) return " Number: "+size;
		return " Number: "+sizeF+"/"+size;
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			selection = (Map) tableGui.g();
			actionRestore.setEnabled(selectionRestorable());
			
			selected();
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private boolean selectionRestorable()
	{
		if(selection==null) return false;
		if(!selection.containsKey("time")) return false;
		if(selection.get("time")==null) return false;
		return true;
	}
	
	
	
	private void shiftN()
	{
		try
		{
			if(normalized)
			{
				normalized = false;
				paintAction.v(DISPLAY_NORM2,actionShiftN);
				handleCommitsSrc.p(commitsF);
			}
			else
			{
				normalized = true;
				paintAction.v(DISPLAY_NORM1,actionShiftN);
				handleCommitsSrcN.p(commitsF);
			}
			
			table.repaint();
			selected();
		}
		catch(Exception e)
		{Outside.err(this,"shiftN()",e);}
	}
	
	
	
	private void restore()
	{
		try
		{
			if(!selectionRestorable()) return;
			boolean ok = confirm.f("Confirm file restoration");
			if(!ok) return;
			
			restore.p(new Object[]{commits,selection});
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"restore()",e);}
	}
	
	
	
	private void search(String query) throws Exception
	{
		fieldGui.p("");
		updateWithQuery(query);
	}
	
	
	private void selected()
	{send(this,"selected()");}

}