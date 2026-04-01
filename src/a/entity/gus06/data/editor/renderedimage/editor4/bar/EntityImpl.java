package a.entity.gus06.data.editor.renderedimage.editor4.bar;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, S {

	public String creationDate() {return "20191119";}

	
	private Service holder;
	
	private Service action_hue_blue;
	private Service action_hue_cyan;
	private Service action_hue_green;
	private Service action_hue_magenta;
	private Service action_hue_orange;
	private Service action_hue_red;
	private Service action_hue_yellow;
	
	
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar.holder");
		
		action_hue_blue = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.blue");
		action_hue_cyan = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.cyan");
		action_hue_green = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.green");
		action_hue_magenta = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.magenta");
		action_hue_orange = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.orange");
		action_hue_red = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.red");
		action_hue_yellow = Outside.service(this,"gus06.data.editor.renderedimage.editor4.action.hue.yellow");
		
		sep();
		add(action_hue_blue);
		sep();
		add(action_hue_cyan);
		sep();
		add(action_hue_green);
		sep();
		add(action_hue_magenta);
		sep();
		add(action_hue_orange);
		sep();
		add(action_hue_red);
		sep();
		add(action_hue_yellow);
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
