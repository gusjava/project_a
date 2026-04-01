package a.entity.gus06.sys.filetool.ext.library1.holder;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.HashSet;
import java.io.File;
import javax.swing.JList;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160914";}
	

	private Service toolbar;
	private Service listHolder;
	private Service refreshSplit;
	private Service performAdd;
	private Service performEdit;
	private Service performDuplicate;
	private Service performDelete;
	private Service performClear;
	private Service performClean;
	private Service performUp;
	private Service performDown;
	private Service performFirst;
	private Service performLast;
	private Service performPaste1;
	private Service performPaste2;
	private Service performPaste3;
	
	private Service detailsHolder;
	private Service custSplit;
	private Service onKey;
	private Service executeFile;
	private Service clipboardFiles;
	private Service clipboardString;
	private Service showInFrame;
	private Service runTask;
	private Service runPreviousTask;
	private Service displayInfos1;
	private Service displayInfos2;
	private Service pending;
	private Service listToString;
	private Service autoScroll;
	


	private JLabel labelNb;
	
	private JSplitPane split;
	private Map map;
	


	public EntityImpl() throws Exception
	{
		toolbar = Outside.service(this,"*gus06.sys.filetool.ext.library1.gui.toolbar");
		listHolder = Outside.service(this,"*gus06.sys.filetool.ext.library1.gui.list");
		refreshSplit = Outside.service(this,"gus06.sys.filetool.ext.library1.refresh.split");
		performAdd = Outside.service(this,"*gus06.sys.filetool.ext.library1.perform.add");
		performEdit = Outside.service(this,"*gus06.sys.filetool.ext.library1.perform.edit");
		performDuplicate = Outside.service(this,"*gus06.sys.filetool.ext.library1.perform.duplicate");
		performDelete = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.delete");
		performClear = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.clear");
		performClean = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.clean");
		performUp = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.up");
		performDown = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.down");
		performFirst = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.first");
		performLast = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.last");
		performPaste1 = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste1");
		performPaste2 = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste2");
		performPaste3 = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste3");
		
		detailsHolder = Outside.service(this,"*gus06.sys.filetool.ext.library1.gui.details");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		executeFile = Outside.service(this,"gus06.file.execute.generic");
		clipboardFiles = Outside.service(this,"gus06.clipboard.access.listfiles");
		clipboardString = Outside.service(this,"gus06.clipboard.access.string");
		showInFrame = Outside.service(this,"gus06.file.editor.show.inframe2");
		runTask = Outside.service(this,"gus06.sys.runtask1.input.path");
		runPreviousTask = Outside.service(this,"gus06.sys.runtask1.input.path.previous");
		displayInfos1 = Outside.service(this,"gus06.dirfile.perform.display.infos1");
		displayInfos2 = Outside.service(this,"gus06.dirfile.perform.display.infos2");
		pending = Outside.service(this,"gus06.app.execute.dev.pending.en");
		listToString = Outside.service(this,"gus06.tostring.list.join.n");
		autoScroll = Outside.service(this,"gus06.swing.scroll.autoposition1");
		
		JComponent list = (JComponent) listHolder.i();
		JComponent editorComp = (JComponent) detailsHolder.i();
		JComponent barComp = (JComponent) toolbar.i();
		
		JScrollPane scroll = new JScrollPane(list);
		autoScroll.p(scroll);
		
		labelNb = new JLabel(" ");
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(barComp,BorderLayout.CENTER);
		panelBottom.add(labelNb,BorderLayout.EAST);
		
		JPanel panelLeft = new JPanel(new BorderLayout());
		panelLeft.add(scroll,BorderLayout.CENTER);
		panelLeft.add(panelBottom,BorderLayout.SOUTH);
		
		split = new JSplitPane();
		split.setRightComponent(editorComp);
		split.setLeftComponent(panelLeft);
		custSplit.p(split);
		
		
		listHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selectionChanged();}
		});
		
		toolbar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				
				if(s.equals("add()"))			add();
				else if(s.equals("edit()"))		edit();
				else if(s.equals("delete()"))		delete();
				else if(s.equals("clear()"))		clear();
				else if(s.equals("up()"))		up();
				else if(s.equals("down()"))		down();
				else if(s.equals("copy()"))		copy1();
				else if(s.equals("copyAll()"))		copyAll();
			}
		});
		
		onKey.p(new Object[]{list,"space",		(E) this::execute});
		onKey.p(new Object[]{list,"del",		(E) this::delete});
		onKey.p(new Object[]{list,"ctrl del",		(E) this::clear});
		onKey.p(new Object[]{list,"shift del",		(E) this::clean});
		onKey.p(new Object[]{list,"F1",			(E) this::add});
		onKey.p(new Object[]{list,"F2",			(E) this::edit});
		onKey.p(new Object[]{list,"F3",			(E) this::duplicate});
		onKey.p(new Object[]{list,"F4",			(E) this::displayInfos1});
		onKey.p(new Object[]{list,"F7",			(E) this::runTask});
		
		onKey.p(new Object[]{list,"ctrl v",		(E) this::paste1});
		onKey.p(new Object[]{list,"ctrl x",		(E) this::cut1});
		onKey.p(new Object[]{list,"ctrl c",		(E) this::copy1});
		onKey.p(new Object[]{list,"ctrl F4",		(E) this::displayInfos2});
		
		onKey.p(new Object[]{list,"ctrl shift v",	(E) this::paste2});
		onKey.p(new Object[]{list,"ctrl shift c",	(E) this::copy2});
		onKey.p(new Object[]{list,"ctrl shift x",	(E) this::cut2});
		onKey.p(new Object[]{list,"ctrl shift space",	(E) this::showInFrame});
		
		onKey.p(new Object[]{list,"ctrl up",		(E) this::up});
		onKey.p(new Object[]{list,"ctrl down",		(E) this::down});
		onKey.p(new Object[]{list,"ctrl left",		(E) this::first});
		onKey.p(new Object[]{list,"ctrl right",		(E) this::last});
		
		onKey.p(new Object[]{list,"alt F7",		(E) this::runPreviousTask});
		onKey.p(new Object[]{list,"ctrl alt v",		(E) this::paste3});
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		listHolder.p(map);
		reload();
	}
	
	
	
	private void reload()
	{	
		try
		{
			listHolder.e();
			List files = (List) listHolder.r("selectedFiles");
			toolbar.p(files);
			refreshSplit.p(new Object[]{split,map});
			labelNb.setText(displayNumber());
		}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	
	
	private String displayNumber() throws Exception
	{
		List files = (List) listHolder.r("files");
		if(files==null || files.isEmpty()) return " ";
		return files.size()+" ";
	}
	
	
	private void selectionChanged()
	{
		try
		{
			List files = (List) listHolder.r("selectedFiles");
			detailsHolder.p(files);
			toolbar.p(files);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	
	
	private void add()
	{
		try
		{
			if(map==null) return;
			boolean done = performAdd.f(map);
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}


	private void edit()
	{
		try
		{
			if(map==null) return;
			String key0 = (String) listHolder.r("selectedKey");
			boolean done = performEdit.f(new Object[]{map,key0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"edit()",e);}
	}
	
	
	private void duplicate()
	{
		try
		{
			if(map==null) return;
			String key0 = (String) listHolder.r("selectedKey");
			boolean done = performDuplicate.f(new Object[]{map,key0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}
	
	
	private void clear()
	{
		try
		{
			if(map==null) return;
			boolean done = performClear.f(map);
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"clear()",e);}
	}
	
	
	private void clean()
	{
		try
		{
			if(map==null) return;
			boolean done = performClean.f(map);
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"clean()",e);}
	}
	
	
	private void up()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performUp.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"up()",e);}
	}
	
	
	private void down()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performDown.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"down()",e);}
	}
	
	
	private void first()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performFirst.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"first()",e);}
	}
	
	
	private void last()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performLast.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"last()",e);}
	}
	
	
	private void execute()
	{
		try
		{
			List files = (List) listHolder.r("selectedFiles");
			if(files!=null) for(int i=0;i<files.size();i++)
			executeFile.p(files.get(i));
		}
		catch(Exception e)
		{Outside.err(this,"execute()",e);}
	}
	
	
	private void runTask()
	{
		try
		{
			File file = (File) listHolder.g();
			if(file!=null) runTask.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"runTask()",e);}
	}
	
	
	private void runPreviousTask()
	{
		try
		{
			File file = (File) listHolder.g();
			if(file!=null) runPreviousTask.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"runPreviousTask()",e);}
	}
	
	
	private void displayInfos1()
	{
		try
		{
			File file = (File) listHolder.g();
			if(file!=null) displayInfos1.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"displayInfos1()",e);}
	}
	
	private void displayInfos2()
	{
		try
		{
			File file = (File) listHolder.g();
			if(file!=null) displayInfos2.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"displayInfos2()",e);}
	}
	
	
	private void delete()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performDelete.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"delete()",e);}
	}
	
	
	
	private void copy1()
	{
		try
		{
			List files = (List) listHolder.r("selectedFiles");
			if(files!=null) clipboardFiles.p(files);
		}
		catch(Exception e)
		{Outside.err(this,"copy1()",e);}
	}
	
	private void copy2()
	{
		try
		{
			List items = (List) listHolder.r("selectedItems");
			if(items!=null) clipboardString.p(listToString.t(items));
		}
		catch(Exception e)
		{Outside.err(this,"copy2()",e);}
	}
	
	
	
	
	
	
	private void cut1()
	{
		try
		{
			if(map==null) return;
			List files = (List) listHolder.r("selectedFiles");
			List keys0 = (List) listHolder.r("selectedKeys");
			
			if(files==null || keys0==null) return;
			
			clipboardFiles.p(files);
			boolean done = performDelete.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"cut()",e);}
	}
	
	private void cut2()
	{
		try
		{
			if(map==null) return;
			List items = (List) listHolder.r("selectedItems");
			List keys0 = (List) listHolder.r("selectedKeys");
			
			if(items==null || keys0==null) return;
			
			clipboardString.p(listToString.t(items));
			boolean done = performDelete.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"cut2()",e);}
	}
	
	
	
	
	
	private void paste1()
	{
		try
		{
			if(map==null) return;
			boolean done = performPaste1.f(map);
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"paste1()",e);}
	}
	
	private void paste2()
	{
		try
		{
			if(map==null) return;
			List keys0 = (List) listHolder.r("selectedKeys");
			boolean done = performPaste2.f(new Object[]{map,keys0});
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"paste2()",e);}
	}
	
	private void paste3()
	{
		try
		{
			if(map==null) return;
			boolean done = performPaste3.f(map);
			if(done) reload();
		}
		catch(Exception e)
		{Outside.err(this,"paste3()",e);}
	}
	
	
	
	
	
	
	
	private void copyAll()
	{
		try
		{
			List files = (List) listHolder.r("files");
			if(files!=null) clipboardFiles.p(files);
		}
		catch(Exception e)
		{Outside.err(this,"copyAll()",e);}
	}
	
	
	private void showInFrame()
	{
		try
		{
			List files = (List) listHolder.r("selectedFiles");
			if(files!=null) showInFrame.p(files);
		}
		catch(Exception e)
		{Outside.err(this,"showInFrame()",e);}
	}
}