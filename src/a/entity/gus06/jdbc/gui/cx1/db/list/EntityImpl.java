package a.entity.gus06.jdbc.gui.cx1.db.list;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.JTable;
import java.awt.Color;
import java.sql.Connection;

public class EntityImpl extends S1 implements Entity, I, P, G, V, ActionListener {

	public String creationDate() {return "20150622";}
	
	public static final String TITLE = "Databases";


	private Service tableHolder;
	private Service control;
	private Service updater;
	private Service updater2;
	private Service onKey;
	private Service timer;
	private Service paste2;
	private Service dnd;

	private JPanel panel;
	
	private JLabel titleLabel;
        private JLabel numberLabel;
        private JTable table;
	private JScrollPane scroll;
	
	private Object cxHolder;
	private Object dumpHolder;
	private List selected;
	
	
	public EntityImpl() throws Exception
	{
		tableHolder = Outside.service(this,"*gus06.jdbc.gui.cx1.db.list.table");
		control = Outside.service(this,"*gus06.jdbc.gui.cx1.db.list.control");
		updater = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.updater");
		updater2 = Outside.service(this,"*gus06.jdbc.gui.cx1.db.list.updater2");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		timer = Outside.service(this,"gus06.time.timer.ms500");
		paste2 = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.paste2");
		dnd = Outside.service(this,"gus06.awt.dnd");
		
		titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		numberLabel = new JLabel(" ");
		
		table = (JTable) tableHolder.i();
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		tableHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{changeSelection();}
		});
		
		control.v("selector",this);
    		
    		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.setBorder(BorderFactory.createRaisedBevelBorder());
		p_bottom.add(numberLabel,BorderLayout.CENTER);
		p_bottom.add((JComponent) control.i(),BorderLayout.EAST);

		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		
		E exeRefresh = (E) control.r("execute_refresh");
		E exeAdd = (E) control.r("execute_add");
		E exeRename = (E) control.r("execute_rename");
		E exeRemove = (E) control.r("execute_remove");
		E exeEmpty = (E) control.r("execute_empty");
		E exeBackup = (E) control.r("execute_backup");
		E exeRestore = (E) control.r("execute_restore");
		E exeDuplicate = (E) control.r("execute_duplicate");
		E exeDuplicateTS = (E) control.r("execute_duplicateTS");
		E exeCopy = (E) control.r("execute_copy");
		E exePaste1 = (E) control.r("execute_paste1");
		E exePaste2 = (E) control.r("execute_paste2");
		E exeAnalyze = (E) control.r("execute_analyze");
		E exeScript = (E) control.r("execute_script");
		
		onKey.p(new Object[]{table, "F1", exeAdd});
		onKey.p(new Object[]{table, "F2", exeRename});
		onKey.p(new Object[]{table, "F3", exeDuplicate});
		onKey.p(new Object[]{table, "ctrl F3", exeDuplicateTS});
		onKey.p(new Object[]{table, "F4", exeAnalyze});
		onKey.p(new Object[]{table, "F5", exeRefresh});
		onKey.p(new Object[]{table, "DEL", exeRemove});
		onKey.p(new Object[]{table, "shift DEL", exeEmpty});
		onKey.p(new Object[]{table, "ctrl S", exeBackup});
		onKey.p(new Object[]{table, "ctrl V", exePaste2});
		onKey.p(new Object[]{table, "ctrl shift S", exeRestore});
		onKey.p(new Object[]{table, "ctrl shift C", exeCopy});
		onKey.p(new Object[]{table, "ctrl shift V", exePaste1});
		onKey.p(new Object[]{table, "ctrl shift H", exeScript});
		
		P dndP = (P) this::onDnd;
		dnd.p(new Object[]{table, dndP, null});
		dnd.p(new Object[]{scroll, dndP, null});
		
		panel.addHierarchyListener(e -> {
			if(panel.isShowing()) reload();
			else reset();
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return selected;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{reset();return;}
		
		cxHolder = obj;
		control.p(cxHolder);
		reload();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("dumpHolder"))
		{
			dumpHolder = obj;
			control.v("dumpHolder", obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			if(cxHolder==null) return;
			updater2.p(new Object[]{cxHolder,tableHolder,numberLabel});
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	private void reload()
	{
		try
		{
			timer.addActionListener(this);
			updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	private void reset()
	{
		try
		{
			timer.removeActionListener(this);
			updater2.e();
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	

	
	private void changeSelection()
	{
		try
		{
			selected = (List) tableHolder.g();
			selectionChanged();
		}
		catch(Exception e)
		{Outside.err(this,"changeSelection()",e);}
	}
		
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private void onDnd(Object files) throws Exception
	{
		if(cxHolder==null) return;
		if(dumpHolder==null) return;
		Connection cx = (Connection) ((G) cxHolder).g();
		if(cx==null) return;
		
		paste2.p(new Object[]{cx, dumpHolder, files});
		((P) cxHolder).p("update");
	}
}