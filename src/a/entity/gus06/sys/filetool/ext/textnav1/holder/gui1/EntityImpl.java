package a.entity.gus06.sys.filetool.ext.textnav1.holder.gui1;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.JTree;
import java.io.File;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.Icon;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, V, I, P, R, Runnable {

	public String creationDate() {return "20220424";}

	public static final long DELAY = 800;

	private Service textEditor;
	private Service fileLabel;
	private Service listGui;
	private Service autoSaver;
	private Service xyToLen;
	private Service textChanged;
	
	private JTextComponent textComp;
	private JSplitPane split;
	
	private File file;
	private Object data;
	private Thread t;
	
	private T trans;
	private Icon icon;
	


	public EntityImpl() throws Exception
	{
		textEditor = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		fileLabel = Outside.service(this,"*gus06.swing.label.hold.file");
		listGui = Outside.service(this,"*gus06.sys.filetool.ext.textnav1.holder.list");
		autoSaver = Outside.service(this,"*gus06.file.editor.holder.text.autosaver");
		xyToLen = Outside.service(this,"gus.x.string.coord.xytolen");
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		
		JPanel panelRight = new JPanel(new BorderLayout());
		panelRight.add((JComponent) fileLabel.i(), BorderLayout.NORTH);
		panelRight.add((JComponent) textEditor.i(), BorderLayout.CENTER);
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);
		
		split.setLeftComponent((JComponent) listGui.i());
		split.setRightComponent(panelRight);
		
		textComp = (JTextComponent) textEditor.r("comp");
		
		listGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {selected();}
		});
		
		S textCompHolder = (S) textChanged.t(textComp);
		((V) textCompHolder).v("delay",DELAY);
		textCompHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {refreshList();}
		});
		
		autoSaver.v("comp", textComp);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		boolean loaded = autoSaver.f(file);
		
		fileLabel.p(file);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("trans"))
		{
			trans = (T) obj;
			return;
		}
		if(key.equals("icon"))
		{
			icon = (Icon) obj;
			listGui.v("icon",icon);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return textComp;
		if(key.equals("file")) return file;
		if(key.equals("keys")) return new String[]{"comp","file"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void selected()
	{
		try
		{
			Object[] row = (Object[]) listGui.g();
			if(row==null) return;
			
			String text = textComp.getText();
			Integer lineIndex = (Integer) row[1];
			int[] xy = new int[]{lineIndex,0};
			
			final Integer pos = (Integer) xyToLen.t(new Object[]{text,xy});
			
			textComp.setCaretPosition(text.length());
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {textComp.setCaretPosition(pos);}
			});
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}


	private void refreshList()
	{
		try
		{
			if(trans==null) return;
			if(t!=null && t.isAlive()) return;
			
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"refreshList()",e);}
	}


	public void run()
	{
		try
		{
			data = trans.t(textComp.getText());
			listGui.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}