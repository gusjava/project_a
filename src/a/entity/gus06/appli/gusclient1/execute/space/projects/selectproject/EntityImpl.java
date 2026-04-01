package a.entity.gus06.appli.gusclient1.execute.space.projects.selectproject;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140830";}


	private Service listing;
	private Service manager;
	private Service choose;


	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.appli.gusclient1.project.listing");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		choose = Outside.service(this,"gus06.input.choose.dialog");
	}
	
	
	public void e() throws Exception
	{
		String newId = choose();
		if(newId==null) return;
		manager.p(newId);
	}
	
	
	private String choose() throws Exception
	{
		String[] names = (String[]) listing.g();
		if(names.length==0) return null;
		if(names.length==1) return names[0];
		
		String message = "Choose a project ID:";
		String title = "Project chooser";
		return (String) choose.t(new Object[]{message,title,names});
	}
}
