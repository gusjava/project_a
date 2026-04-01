package a.entity.gus06.appli.gusclient1.project.change;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141028";}


	private Service listing;
	private Service manager;


	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.appli.gusclient1.project.listing");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		int m = Integer.parseInt((String) obj);
		changeProject(m);
	}
	
	
	
	private void changeProject(int m) throws Exception
	{
		String[] names = (String[]) listing.g();
		if(names==null || names.length==0) return;
		
		int newIndex = newIndex(names,m);
		manager.p(names[newIndex]);
	}
	
	
	
	private int newIndex(String[] names, int m) throws Exception
	{
		String name = (String) manager.g();
		if(name==null) return 0;
		
		int index = indexOf(names,name);
		if(index==-1) return 0;
		
		int number = names.length;
		index += m;
		if(index<0) index += number;
		else if(index>number-1) index -= number;
		return index;
	}
	
	
	
	private int indexOf(String[] names, String name)
	{
		for(int i=0;i<names.length;i++)
		if(names[i].equals(name)) return i;
		return -1;	
	}
}
