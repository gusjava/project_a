package a.entity.gus06.sys.parser3.demo;

import a.framework.*;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151027";}


	private Service step1;
	private Service step2;
	private Service step3;
	private Service step4;
	private Service evaluate;

	private JTabbedPane tab;

	public EntityImpl() throws Exception
	{
		step1 = Outside.service(this,"gus06.sys.parser3.prepare.step1.demo");
		step2 = Outside.service(this,"gus06.sys.parser3.prepare.step2.demo");
		step3 = Outside.service(this,"gus06.sys.parser3.prepare.step3.demo");
		step4 = Outside.service(this,"gus06.sys.parser3.prepare.step4.demo");
		evaluate = Outside.service(this,"gus06.sys.parser3.evaluate.demo");
		
		tab = new JTabbedPane();
		tab.addTab("Evaluate",(JComponent) evaluate.i());
		tab.addTab("Step1",(JComponent) step1.i());
		tab.addTab("Step2",(JComponent) step2.i());
		tab.addTab("Step3",(JComponent) step3.i());
		tab.addTab("Step4",(JComponent) step4.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
}
