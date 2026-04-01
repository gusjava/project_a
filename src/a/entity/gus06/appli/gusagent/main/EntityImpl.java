package a.entity.gus06.appli.gusagent.main;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20150626";}

	public static final String KEY_AGENTLAPSE = "app.agentlapse";
	public static final String DEFAULT_AGENTLAPSE = "180000"; // 3min



	private Service timerVariable;
	private Service perform;


	public EntityImpl() throws Exception
	{
		timerVariable = Outside.service(this,"*gus06.time.timer.variable");
		perform = Outside.service(this,"gus06.appli.gusagent.perform");
		
		timerVariable.v("propKey",KEY_AGENTLAPSE);
		timerVariable.v("defaultLapse",DEFAULT_AGENTLAPSE);
		timerVariable.addActionListener(this);
		
		perform();
		timerVariable.e();
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try{perform.e();}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}
