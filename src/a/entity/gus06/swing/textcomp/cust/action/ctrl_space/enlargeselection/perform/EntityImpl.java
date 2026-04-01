package a.entity.gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140816";}

	public static final String SELECTION_EMPTY = "EMPTY";
	public static final String SELECTION_WORD1 = "WORD1";
	public static final String SELECTION_WORD2 = "WORD2";
	public static final String SELECTION_WORD3 = "WORD3";
	public static final String SELECTION_WORD4 = "WORD4";
	public static final String SELECTION_LINE = "LINE";
	public static final String SELECTION_BLOC = "BLOC";
	public static final String SELECTION_ALL = "ALL";


	private Service findLevel;
	
	private Service perform_p0;
	private Service perform_p1;
	private Service perform_p2;
	private Service perform_p3;
	private Service perform_p4;
	private Service perform_q1;
	private Service perform_pp;
	


	public EntityImpl() throws Exception
	{
		findLevel = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.findlevel");
		
		perform_p0 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p0");
		perform_p1 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p1");
		perform_p2 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p2");
		perform_p3 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p3");
		perform_p4 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.p4");
		perform_q1 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q1");
		perform_pp = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.pp");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String level = (String) findLevel.t(comp);
		
		if(level.equals(SELECTION_EMPTY))	{perform_p0.p(comp);return;}
		if(level.equals(SELECTION_WORD1))	{perform_p1.p(comp);return;}
		if(level.equals(SELECTION_WORD2))	{perform_p2.p(comp);return;}
		if(level.equals(SELECTION_WORD3))	{perform_p3.p(comp);return;}
		if(level.equals(SELECTION_WORD4))	{perform_p4.p(comp);return;}
		if(level.equals(SELECTION_LINE))	{perform_q1.p(comp);return;}
		if(level.equals(SELECTION_BLOC))	{perform_q1.p(comp);return;}
		if(level.equals(SELECTION_ALL))		{perform_pp.p(comp);return;}
	}
}