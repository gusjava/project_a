package a.entity.gus06.data.editor.renderedimage.editor3.bar;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, S {

	public String creationDate() {return "20191119";}

	
	private Service holder;
	
	private Service action_color_blackwhite;
	private Service action_color_discretize1;
	private Service action_color_discretize2;
	private Service action_color_discretize3;
	private Service action_color_clamp;
	private Service action_color_blurv;
	private Service action_color_blurh;
	
	
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar.holder");
		
		action_color_blackwhite = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.blackwhite");
		action_color_discretize1 = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.discretize1");
		action_color_discretize2 = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.discretize2");
		action_color_discretize3 = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.discretize3");
		action_color_clamp = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.clamp");
		action_color_blurv = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.blurv");
		action_color_blurh = Outside.service(this,"gus06.data.editor.renderedimage.editor3.action.color.blurh");
		
		sep();
		add(action_color_blackwhite);
		sep();
		add(action_color_discretize1);
		sep();
		add(action_color_discretize2);
		sep();
		add(action_color_discretize3);
		sep();
		add(action_color_clamp);
		sep();
		add(action_color_blurv);
		sep();
		add(action_color_blurh);
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
