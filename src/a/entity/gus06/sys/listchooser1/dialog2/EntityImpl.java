package a.entity.gus06.sys.listchooser1.dialog2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, T, V {

	public String creationDate() {return "20160507";}
	
	public static final int GAP = 20;
	

	private Service okCancel;
	private Service okCancel0;
	private Service selector;
	private Service persister;
	private Service buildLabelTitle;
	
	private JComponent selectorComp;
	private Object annexe;
	private String search;
	private boolean showButtons = false;
	
	private JLabel labelTitle;
	private Object width;
	private Object height;
	private String persistKey;
	


	public EntityImpl() throws Exception
	{
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		okCancel0 = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel0");
		selector = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		persister = Outside.service(this,"gus06.app.persister1.comparator1.recent");
		buildLabelTitle = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		
		selectorComp = (JComponent) selector.i();
		labelTitle = (JLabel) buildLabelTitle.i();
		
		selector.addActionListener(this);
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(persistKey!=null) Collections.sort(list,persistComparator());
		selector.p(list);
		
		if(search!=null) selector.v("search",search);
		
		Service s = okCancel();
		
		s.v("width",width);
		s.v("height",height);
		
		boolean result = s.f(buildPanel());
		if(!result) return null;
		
		Object item = selector.g();
		if(persistKey!=null) persister.v(persistKey,item);
		return item;
	}
	
	
	
	
	
	private JPanel buildPanel() throws Exception
	{
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(selectorComp,BorderLayout.CENTER);
		if(annexe!=null) panel1.add(annexeComp(),BorderLayout.EAST);
		
		panel1.setBorder(BorderFactory.createEmptyBorder(GAP,0,0,0));
		panel1.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(labelTitle,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
		
		return panel;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("title"))
		{setTitle((String) obj);return;}

		if(key.equals("persistKey"))
		{persistKey = (String) obj;return;}
		
		if(key.equals("width"))
		{width = obj;return;}
		
		if(key.equals("height"))
		{height = obj;return;}
		
		if(key.equals("annexe"))
		{annexe = obj;return;}
		
		if(key.equals("search"))
		{search = (String) obj;return;}
		
		if(key.equals("showButtons"))
		{showButtons = toBool(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void setTitle(String title)
	{labelTitle.setText(title);}



	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("typed_enter()")) {typed_enter();return;}
		if(s.equals("selectionChanged()")) {selectionChanged();return;}
		if(s.equals("cleared()")) {cleared();return;}
	}
	
	
	private void typed_enter()
	{
		try{okCancel().v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"typed_enter()",e);}
	}

	private void cleared()
	{
		try{okCancel().v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cleared()",e);}
	}
	
	private void selectionChanged()
	{
		try
		{
			Object value = selector.g();
			if(annexe!=null) ((P) annexe).p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private JComponent annexeComp() throws Exception
	{return (JComponent) ((I) annexe).i();}
	
	private boolean toBool(Object obj)
	{return (""+obj).equals("true");}
	
	private Service okCancel()
	{return showButtons ? okCancel : okCancel0;}
	
	private Comparator persistComparator() throws Exception
	{return (Comparator) persister.r(persistKey);}
}
