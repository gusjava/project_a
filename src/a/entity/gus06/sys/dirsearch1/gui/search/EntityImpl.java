package a.entity.gus06.sys.dirsearch1.gui.search;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl implements Entity, ActionListener, I, V, E, Runnable {

	public String creationDate() {return "20200315";}


	private Service engine;
	private Service resultPanel;
	private Service detailPanel;
	private Service termsBar;
	private Service progress;
	private Service extrBuilder;
	private Service onKey;
	private Service textPersister;
	private Service fieldHolder;


	private JSplitPane split;
	private JTextField field;
	private JLabel labelMode;
	private JButton button;
	
	private T termsBuilder;
	private F fileFilter;
	private Object modeManager;
	private Object roots;
	private Set interrupt;
	private Thread t1;
	private Thread t2;
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"*gus06.sys.dirsearch1.engine");
		resultPanel = Outside.service(this,"*gus06.sys.dirsearch1.gui.search.resultpanel");
		detailPanel = Outside.service(this,"*gus06.sys.dirsearch1.gui.search.detailpanel");
		termsBar = Outside.service(this,"*gus06.sys.dirsearch1.gui.search.termsbar");
		progress = Outside.service(this,"*gus06.swing.progressbar.progress1a");
		extrBuilder = Outside.service(this,"gus06.sys.dirsearch1.fileextractor.builder1");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		textPersister = Outside.service(this,"gus06.swing.textcomp.persister.text.togp");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		
		interrupt = new HashSet();
		
		resultPanel.v("engine",engine);
		resultPanel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){selectionChanged();}
		});
		
		button = new JButton("Search");
		button.addActionListener(this);
		
		labelMode = new JLabel("  ");
		labelMode.setFont(labelMode.getFont().deriveFont(Font.BOLD));
		
		fieldHolder.v("onCleared",(E) this::startSearch1);
		field = (JTextField) fieldHolder.i();
		field.addActionListener(this);
		
		onKey.p(new Object[]{field,"F1",(E) ()->changeMode("F1")});
		onKey.p(new Object[]{field,"F2",(E) ()->changeMode("F2")});
		onKey.p(new Object[]{field,"F3",(E) ()->changeMode("F3")});
		onKey.p(new Object[]{field,"F4",(E) ()->changeMode("F4")});
		onKey.p(new Object[]{field,"F5",(E) ()->changeMode("F5")});
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(labelMode,BorderLayout.WEST);
		topPanel.add(field,BorderLayout.CENTER);
		topPanel.add(button,BorderLayout.EAST);
		topPanel.add((JComponent) termsBar.i(),BorderLayout.SOUTH);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(topPanel,BorderLayout.NORTH);
		panel.add((JComponent) resultPanel.i(),BorderLayout.CENTER);
		panel.add((JComponent) progress.i(),BorderLayout.SOUTH);
		
		split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setLeftComponent(panel);
		split.setRightComponent((JComponent) detailPanel.i());
		
		split.setDividerSize(3);
		split.setDividerLocation(400);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("roots")) {roots = obj;return;}
		if(key.equals("orientation")) {setOrientation((String) obj);return;}
		if(key.equals("widthMap")) {resultPanel.v("widthMap",obj);return;}
		if(key.equals("inputPersist")) {setInputPersist(obj);return;}
		if(key.equals("input")) {setInput((String) obj);return;}
		if(key.equals("modeManager")) {modeManager = obj;return;}
		if(key.equals("termsBuilder")) {termsBuilder = (T) obj;return;}
		if(key.equals("fileFilter")) {fileFilter = (F) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void setOrientation(String value) throws Exception
	{
		if(value==null) return;
		
		if(value.equals("h"))
			split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		else if(value.equals("v"))
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		else throw new Exception("Invalid orientation value: "+value);
	}
	
	
	
	private void setInputPersist(Object persist)
	{
		try
		{
			textPersister.p(new Object[]{field,persist});
		}
		catch(Exception e)
		{Outside.err(this,"setInputPersist(Object)",e);}
	}
	
	
	
	
	private void setInput(String input)
	{
		try
		{
			fieldHolder.p(input);
		}
		catch(Exception e)
		{Outside.err(this,"setInput(String)",e);}
	}

	
	
	private void changeMode(String mode)
	{
		try
		{
			if(modeManager==null) return;
			
			labelMode.setText(" "+mode+" ");
			((P)modeManager).p(mode);
		}
		catch(Exception e)
		{Outside.err(this,"changeMode(String)",e);}
	}
	
	
	

	public void actionPerformed(ActionEvent e)
	{startSearch1();}
	
	
	
	public void e() throws Exception
	{startSearch1();}
	
	
	
	private void startSearch1()
	{
		if(t1!=null && t1.isAlive()) return;
		
		Runnable r = this::startSearch2;
		t1 = new Thread(r,"THREAD1_"+getClass().getName());
		t1.start();
	}
	
	private void startSearch2()
	{
		button.setText("...");
		if(t2!=null && t2.isAlive()) interruptSearch();
		t2 = new Thread(this,"THREAD2_"+getClass().getName());
		t2.start();
	}
	
	
	public void run()
	{
		button.setText("Running");
		performSearch();
		button.setText("Search");
	}
	
	
	private void interruptSearch()
	{
		interrupt.add(1);
		try{t2.join(1000);}
		catch(InterruptedException e) {}
		interrupt.clear();
	}
	
	
	
	private void performSearch()
	{
		try
		{
			resultPanel.e();
			detailPanel.e();
			progress.v("reset",null);
			
			String input = field.getText();
			if(input.trim().equals(""))
			{
				termsBar.p(null);
				return;	
			}
			
			Object fileExtractor = extrBuilder.t(new Object[]{input,termsBuilder});
			
			List blockExtrList = (List) ((R)fileExtractor).r("blockExtrList");
			termsBar.p(blockExtrList);
			resultPanel.v("listExtr",blockExtrList);
			
			engine.v("interrupt",interrupt);
			engine.v("progress",progress);
			engine.v("roots",roots);
			engine.v("fileFilter",fileFilter);
			engine.v("fileExtractor",fileExtractor);
			engine.e();
		}
		catch(Exception e)
		{Outside.err(this,"performSearch()",e);}
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			Object selection = resultPanel.g();
			detailPanel.p(selection);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}

}