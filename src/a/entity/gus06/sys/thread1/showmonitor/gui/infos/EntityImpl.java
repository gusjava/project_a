package a.entity.gus06.sys.thread1.showmonitor.gui.infos;

import a.framework.*;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20180124";}


	private Service form;
	private Service steToString;
	
	private JLabel labelName;
	private JLabel labelState;
	private JLabel labelId;
	private JLabel labelPriority;
	private JLabel labelGroup;
	private JLabel labelDaemon;
	private JLabel labelParent;
	private JLabel labelGusCall;

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		steToString = Outside.service(this,"gus06.tostring.stacktraceelement");
		
		labelName = new JLabel();
		labelState = new JLabel();
		labelId = new JLabel();
		labelPriority = new JLabel();
		labelGroup = new JLabel();
		labelDaemon = new JLabel();
		labelParent = new JLabel();
		labelGusCall = new JLabel();
		
		form.v("Name",labelName);
		form.v("State",labelState);
		form.v("Id",labelId);
		form.v("Priority",labelPriority);
		form.v("Group",labelGroup);
		form.v("Daemon",labelDaemon);
		
		form.v(" ",new JLabel(" "));
		form.v("Parent",labelParent);
		form.v("Gus call",labelGusCall);
		
	}
	
	
	public Object i() throws Exception
	{return form.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
		
		if(t==null)
		{
			labelName.setText("");
			labelState.setText("");
			labelId.setText("");
			labelPriority.setText("");
			labelGroup.setText("");
			labelDaemon.setText("");
			labelParent.setText("");
			labelGusCall.setText("");
			return;
		}
		
		String name = t.getName();
		String state = t.getState().name();
		String id = ""+t.getId();
		String priority = ""+t.getPriority();
		String group = group(t);
		String isDaemon = ""+t.isDaemon();
		
		labelName.setText(name);
		labelState.setText(state);
		labelId.setText(id);
		labelPriority.setText(priority);
		labelGroup.setText(group);
		labelDaemon.setText(isDaemon);
		
		if(t instanceof R)
		{
			R r = (R) t;
			
			Thread parent = (Thread) r.r("parent");
			StackTraceElement parentSte1 = (StackTraceElement) r.r("parentSte1");
			String parentSte1_ = (String) steToString.t(parentSte1);
			
			String parentName = parent.getName();
			String parentState = parent.getState().name();
			boolean advanced = parent instanceof R;
			
			StringBuffer b = new StringBuffer();
			b.append(parentName);
			b.append(" ");
			b.append(parentState);
			if(advanced) b.append(" *");
			
			labelParent.setText(b.toString());
			labelGusCall.setText(parentSte1_);
		}
		else
		{
			labelParent.setText("");
			labelGusCall.setText("");
		}
	}
	
	
	
	private String group(Thread t)
	{
		ThreadGroup g = t.getThreadGroup();
		return g==null?"":g.getName();
	}
}