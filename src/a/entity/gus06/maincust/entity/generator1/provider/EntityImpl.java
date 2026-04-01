package a.entity.gus06.maincust.entity.generator1.provider;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140810";}


	private Service classFinder;
	private Service mapWatcher;

	public EntityImpl() throws Exception
	{
		classFinder = Outside.service(this,"classfinder");
		mapWatcher = Outside.service(this,"gus06.maincust.entity.generator1.mapwatcher");
	}
	
	
	private Class find(String name) throws Exception
	{return (Class) classFinder.t(name);}
	
	
	private String changedClass()
	{
		try{return (String) mapWatcher.g();}
		catch(Exception e) {Outside.err(this,"changedClass()",e);}
		return "";
	}
	
	private Object build(String name)
	{
		try{return find(name).newInstance();}
		catch(Exception e) {Outside.err(this,"build(String)",e);}
		return null;
	}

	
	
	public Object t(Object obj) throws Exception
	{return new Provider((String) obj);}
	
	
	
	
	private class Provider extends S1 implements G, ActionListener
	{
		private String name;
		private Object obj;
	
		public Provider(String name) throws Exception
		{
			this.name = name;
			this.obj = build(name);
			mapWatcher.addActionListener(this);
		}
		
		public Object g() throws Exception
		{return obj;}
		
		public void actionPerformed(ActionEvent e)
		{if(name.equals(changedClass())) rebuild();}
		
		private void rebuild()
		{obj = build(name);changed();}
		
		private void changed()
		{send(this,"changed()");}
	}
}
