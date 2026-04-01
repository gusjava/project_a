package a.entity.gus06.swing.textarea.buildpanel1;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Insets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150425";}


	private Service custComp;
	private Service buildScroll;
	private Service buildHighSup;
	private Service buildHighCount;
	private Service buildHighScroll;


	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.swing.textarea.buildpanel1.custcomp");
		buildScroll = Outside.service(this,"gus06.swing.textarea.buildscrollpane.linenb");
		buildHighSup = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.support");
		buildHighCount = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.countbar");
		buildHighScroll = Outside.service(this,"gus06.swing.textcomp.highlight.sys1.scrollpaint");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextArea comp = (JTextArea) obj;
		comp.setMargin(new Insets(0,3,0,3));
		
		S1 highSup = (S1) buildHighSup.t(comp);
		
		//JScrollPane scroll = (JScrollPane) buildScroll.t(comp);
		JScrollPane scroll = new JScrollPane(comp);
		JComponent countBar = (JComponent) buildHighCount.t(comp);
		
		custComp.p(comp);
		
		Object highScroll = buildHighScroll.t(scroll);
		highSup.addActionListener((ActionListener) countBar);
		highSup.addActionListener((ActionListener) highScroll);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(countBar,BorderLayout.SOUTH);
		
		return panel;
	}
}
