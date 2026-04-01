package a.entity.gus06.data.viewer.method;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140821";}


	private Service formPanel;
	private Service valuePanel;
	
	private Method data;
	
	private JTabbedPane tabbedPane;
	
	
	
	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.data.viewer.method.form");
		valuePanel = Outside.service(this,"*gus06.data.viewer.method.value");
				
		tabbedPane = new JTabbedPane();
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Method) obj;
		tabbedPane.removeAll();
		
		formPanel.p(data);
		if(data!=null) addTab("Method",formPanel);
		
		valuePanel.p(data);
		if(data!=null && isStatic() && hasNoArgs() && hasReturn()) addTab("Value",valuePanel);
	}
	
	
	private void addTab(String title, Service s) throws Exception
	{tabbedPane.addTab(title,(JComponent) s.i());}
	
	
	private boolean isStatic()
	{return Modifier.isStatic(data.getModifiers());}
	
	private boolean hasNoArgs()
	{return data.getParameterTypes().length==0;}
	
	private boolean hasReturn()
	{return !data.getReturnType().equals(Void.TYPE);}
}
