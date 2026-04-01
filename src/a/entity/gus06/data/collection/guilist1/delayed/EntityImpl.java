package a.entity.gus06.data.collection.guilist1.delayed;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, R, S {

	public String creationDate() {return "20170507";}


	private Service gui;
	private Service buildSupport;
	
	private S support;


	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.data.collection.guilist1");
		buildSupport = Outside.service(this,"gus06.support.build.delaysupport");
		support = (S) buildSupport.t(gui);
	}
	
	
	
	public Object g() throws Exception
	{return gui.g();}
	
	public Object r(String key) throws Exception
	{return gui.r(key);}
	
	public Object i() throws Exception
	{return gui.i();}
	
	public void p(Object obj) throws Exception
	{gui.p(obj);}



	public void addActionListener(ActionListener l) throws Exception
	{support.addActionListener(l);}

	public void removeActionListener(ActionListener l) throws Exception
	{support.removeActionListener(l);}
	
	public List listeners() throws Exception
	{return support.listeners();}
}
