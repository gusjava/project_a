package a.entity.gus06.app.library.gui.displaygui;

import a.framework.*;
import javax.swing.JTabbedPane;
import java.lang.reflect.Field;
import javax.swing.JComponent;
import java.util.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150607";}


	private Service dataViewer1;
	private Service dataViewer2;
	private Service dataViewer3;
	
	private JTabbedPane tab;


	public EntityImpl() throws Exception
	{
		dataViewer1 = Outside.service(this,"*gus06.data.viewer.list-1");
		dataViewer2 = Outside.service(this,"*gus06.data.viewer.list-2");
		dataViewer3 = Outside.service(this,"*gus06.data.viewer.list-3");
		
		//DEPRECATED FROM JAVA11 !!!!
		
//		dataViewer1.p(getLib("loadedLibraryNames"));
//		dataViewer2.p(getLib("systemNativeLibraries"));
//		dataViewer3.p(getLib1("nativeLibraries"));
		
		tab = new JTabbedPane();
		tab.addTab("Loaded lib names",(JComponent) dataViewer1.i());
		tab.addTab("System native libs",(JComponent) dataViewer2.i());
		tab.addTab("Native libs",(JComponent) dataViewer3.i());
	}
	
	
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	
	
	private List getLib(String method) throws Exception
	{
		final Field field = ClassLoader.class.getDeclaredField(method);
		field.setAccessible(true);
		return toList(field.get(null));
	}
	
	private List getLib1(String method) throws Exception
	{
		ClassLoader cl = getClass().getClassLoader();
		final Field field = ClassLoader.class.getDeclaredField(method);
		field.setAccessible(true);
		return toList(field.get(cl));
	}
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set) return new ArrayList((Set) obj);
		if(obj instanceof Map) return new ArrayList(((Map) obj).keySet());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
