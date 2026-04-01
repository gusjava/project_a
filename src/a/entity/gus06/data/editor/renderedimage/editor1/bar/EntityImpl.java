package a.entity.gus06.data.editor.renderedimage.editor1.bar;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, S {

	public String creationDate() {return "20191119";}

	
	private Service holder;
	
	private Service action_rotate90;
	private Service action_rotate180;
	private Service action_rotate270;
	
	private Service action_flip_vertical;
	private Service action_flip_horizontal;
	private Service action_flip_diagonal;
	private Service action_flip_antidiagonal;
	
	private Service action_extract_text;

	
	
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar.holder");
		
		action_rotate90 = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.rotate90");
		action_rotate180 = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.rotate180");
		action_rotate270 = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.rotate270");
		
		action_flip_vertical = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.flip.vertical");
		action_flip_horizontal = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.flip.horizontal");
		action_flip_diagonal = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.flip.diagonal");
		action_flip_antidiagonal = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.flip.antidiagonal");
		
		action_extract_text = Outside.service(this,"gus06.data.editor.renderedimage.editor1.action.extract.text");
		
		sep();
		add(action_rotate90);
		sep();
		add(action_rotate180);
		sep();
		add(action_rotate270);
		sep();
		add(action_flip_vertical);
		sep();
		add(action_flip_horizontal);
		sep();
		add(action_flip_diagonal);
		sep();
		add(action_flip_antidiagonal);
		sep();
		add(action_extract_text);
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