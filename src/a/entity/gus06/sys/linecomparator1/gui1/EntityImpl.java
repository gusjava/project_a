package a.entity.gus06.sys.linecomparator1.gui1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import java.util.Set;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EntityImpl implements Entity, P, I, R, E, ItemListener {

	public String creationDate() {return "20210717";}

	public static final String TITLE1 = "Only Left";
	public static final String TITLE2 = "Both";
	public static final String TITLE3 = "Only Right";


	private Service perform;
	private Service readFile;
	private Service buildSet;
	private Service setToString;
	
	private Service viewer1;
	private Service viewer2;
	private Service viewer3;
	private Service titledPanel;

	private JPanel panel;
	private JPanel bottom;
	
	private JCheckBox checkTrim;
	private JCheckBox checkEmpty;
	private JCheckBox checkSensitive;
	private JCheckBox checkNormalize;
	
	private String text1;
	private String text2;
	
	private String[] lines1;
	private String[] lines2;
	
	private Set set1;
	private Set set2;
	

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.set.set2toset3");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		buildSet = Outside.service(this,"gus06.sys.linecomparator1.buildset");
		setToString = Outside.service(this,"gus06.tostring.set");
		
		viewer1 = Outside.service(this,"*gus06.sys.linecomparator1.linesviewer-1");
		viewer2 = Outside.service(this,"*gus06.sys.linecomparator1.linesviewer-2");
		viewer3 = Outside.service(this,"*gus06.sys.linecomparator1.linesviewer-3");
		titledPanel = Outside.service(this,"gus06.swing.comp.build.titledpanel");
		
		JPanel panelCenter = new JPanel(new GridLayout(1,3,5,5));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panelCenter.add((JComponent) titledPanel.t(new Object[]{viewer1,TITLE1}));
		panelCenter.add((JComponent) titledPanel.t(new Object[]{viewer2,TITLE2}));
		panelCenter.add((JComponent) titledPanel.t(new Object[]{viewer3,TITLE3}));
		
	 	checkTrim = new JCheckBox("Trim lines");
	 	checkEmpty = new JCheckBox("Ignore empty");
	 	checkSensitive = new JCheckBox("Case insensitive");
	 	checkNormalize = new JCheckBox("Normalized");
		
		bottom = new JPanel(new GridLayout(1,4,5,5));
		bottom.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bottom.add(checkTrim);
		bottom.add(checkEmpty);
		bottom.add(checkSensitive);
		bottom.add(checkNormalize);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelCenter,BorderLayout.CENTER);
		panel.add(bottom,BorderLayout.SOUTH);
		
		checkTrim.addItemListener(this);
		checkEmpty.addItemListener(this);
		checkSensitive.addItemListener(this);
		checkNormalize.addItemListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void e() throws Exception
	{reset();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("text1")) return text1;
		if(key.equals("text2")) return text2;
		
		if(key.equals("lines1")) return lines1;
		if(key.equals("lines2")) return lines2;
		
		if(key.equals("set1")) return set1;
		if(key.equals("set2")) return set2;
		
		if(key.equals("options")) return options();
		
		if(key.equals("keys"))
		return new String[]{"text1","text2","lines1","lines2","set1","set2","options"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		reset();
		
		text1 = toString(o[0]);
		text2 = toString(o[1]);
		
		lines1 = toLines(text1);
		lines2 = toLines(text2);
		
		perform();
	}
	
	
	
	private void reset() throws Exception
	{
		text1 = null;
		text2 = null;
		
		lines1 = null;
		lines2 = null;
		
		set1 = null;
		set2 = null;
		
		viewer1.p("");
		viewer1.p("");
		viewer1.p("");
	}
	
	
	public void itemStateChanged(ItemEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			String options = options();
			set1 = (Set) buildSet.t(new Object[]{lines1,options});
			set2 = (Set) buildSet.t(new Object[]{lines2,options});
			
			Set[] output = (Set[]) perform.t(new Set[]{set1,set2});
			
			String s1 = (String) setToString.t(output[0]);
			String s2 = (String) setToString.t(output[1]);
			String s3 = (String) setToString.t(output[2]);
			
			viewer1.p(s1);
			viewer2.p(s2);
			viewer3.p(s3);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	private String toString(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) readFile.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String[] toLines(String text)
	{
		if(text==null) return new String[]{};
		return text.split("\n",-1);
	}
	
	
	private JPanel titled(Object comp, String title) throws Exception
	{return (JPanel) titledPanel.t(new Object[]{comp,title});}
	
	
	private String options()
	{
		StringBuffer b = new StringBuffer();
		if(checkTrim.isSelected()) b.append("t"); 
		if(checkEmpty.isSelected()) b.append("e"); 
		if(checkSensitive.isSelected()) b.append("i"); 
		if(checkNormalize.isSelected()) b.append("n"); 
		return b.toString();
	}
}