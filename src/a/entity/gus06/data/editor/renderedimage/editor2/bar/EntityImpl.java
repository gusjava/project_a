package a.entity.gus06.data.editor.renderedimage.editor2.bar;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, S {

	public String creationDate() {return "20191119";}

	
	private Service holder;
	
	private Service action_color_brighten;
	private Service action_color_darken;
	private Service action_color_grayscale;
	private Service action_color_invert;
	private Service action_color_invert_centerband;
	private Service action_color_invert_firstband;
	private Service action_color_invert_lastband;

	
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar.holder");
		
		action_color_brighten = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.brighten");
		action_color_darken = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.darken");
		action_color_grayscale = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.grayscale");
		action_color_invert = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.invert");
		action_color_invert_centerband = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.invert.centerband");
		action_color_invert_firstband = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.invert.firstband");
		action_color_invert_lastband = Outside.service(this,"gus06.data.editor.renderedimage.editor2.action.color.invert.lastband");
		
		sep();
		add(action_color_brighten);
		sep();
		add(action_color_darken);
		sep();
		add(action_color_grayscale);
		sep();
		add(action_color_invert);
		sep();
		add(action_color_invert_centerband);
		sep();
		add(action_color_invert_firstband);
		sep();
		add(action_color_invert_lastband);
		sep();
	}
	
	
	private void sep() throws Exception
	{holder.v("sep",null);}
	
	private void add(Service s) throws Exception
	{holder.v("action",s);}
	
	
	
	public Object g() throws Exception
	{return holder.g();}
	
	public Object i() throws Exception
	{return holder.i();}
	
	public void p(Object obj) throws Exception
	{holder.p(obj);}
	
	public void addActionListener(ActionListener al) throws Exception
	{holder.addActionListener(al);}
	
	public void removeActionListener(ActionListener al) throws Exception
	{holder.removeActionListener(al);}
	
	public List listeners() throws Exception
	{return holder.listeners();}
}
