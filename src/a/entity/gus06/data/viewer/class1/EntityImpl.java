package a.entity.gus06.data.viewer.class1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140804";}

	private Service form;
	private Service instanciator;
	private Service introspector;
	private Service srcPanel;
	private Service docPanel;

	private JTabbedPane tabbedPane;
	
	private Class data;
	
	
	
	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.data.viewer.class1.form");
		instanciator = Outside.service(this,"*gus06.data.viewer.class1.instanciator");
		introspector = Outside.service(this,"*gus06.data.viewer.class1.introspector");
		srcPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.src");
		docPanel = Outside.service(this,"*gus06.data.viewer.class1.panel.doc");
		
		tabbedPane = new JTabbedPane();
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (Class) obj;
		tabbedPane.removeAll();
		
		introspector.p(data);
		if(data!=null) addTab("Members",introspector);
		
		form.p(data);
		addTab("Infos",form);
	
		instanciator.p(data);
		if(data!=null) addTab("Instanciate",instanciator);
		
		srcPanel.p(data);
		if(srcPanel.g()!=null) addTab("Source code",srcPanel);
		
		docPanel.p(data);
		if(docPanel.g()!=null) addTab("Javadoc",docPanel);
	}
	
	
	private void addTab(String title, Service s) throws Exception
	{tabbedPane.addTab(title,(JComponent) s.i());}
}
