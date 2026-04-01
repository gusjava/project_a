package a.entity.gus06.appli.textcomparator.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150305";}

	public static final String KEY_C_LENGTH = "common.length";
	

	private Service panelAreas;
	private Service panelInfos;
	private Service analyzer;


	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		panelAreas = Outside.service(this,"*gus06.appli.textcomparator.gui.panel.areas");
		panelInfos = Outside.service(this,"*gus06.appli.textcomparator.gui.panel.infos");
		analyzer = Outside.service(this,"gus06.appli.textcomparator.analyzer");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) panelAreas.i(),BorderLayout.CENTER);
		panel.add((JComponent) panelInfos.i(),BorderLayout.SOUTH);
		
		panelAreas.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{analyze();}
	
	
	
	private void analyze()
	{
		try
		{
			Object texts = panelAreas.g();
			
			Map infos = (Map) analyzer.t(texts);
			String lc = (String) infos.get(KEY_C_LENGTH);
			
			panelInfos.p(infos);
			panelAreas.v("common",lc);
		}
		catch(Exception e)
		{Outside.err(this,"analyze()",e);}
	}

}
