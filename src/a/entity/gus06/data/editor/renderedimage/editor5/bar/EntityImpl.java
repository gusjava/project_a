package a.entity.gus06.data.editor.renderedimage.editor5.bar;

import a.framework.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, S {

	public String creationDate() {return "20191120";}

	
	private Service holder;
	
	private Service action_kernel_convolve;
	private Service action_kernel_errdiff;
	private Service action_kernel_gradmag;
	
	
	
	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar.holder");
		
		action_kernel_convolve = Outside.service(this,"gus06.data.editor.renderedimage.editor5.action.kernel.convolve");
		action_kernel_errdiff = Outside.service(this,"gus06.data.editor.renderedimage.editor5.action.kernel.errdiff");
		action_kernel_gradmag = Outside.service(this,"gus06.data.editor.renderedimage.editor5.action.kernel.gradmag");
		
		sep();
		add(action_kernel_convolve);
		sep();
		add(action_kernel_errdiff);
		sep();
		add(action_kernel_gradmag);
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
