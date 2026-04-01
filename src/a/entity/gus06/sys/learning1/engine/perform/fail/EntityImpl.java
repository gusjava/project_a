package a.entity.gus06.sys.learning1.engine.perform.fail;

import a.framework.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250708";}
	
	public static final String COL_DATE = "date";
	public static final String COL_QUESTION = "question";
	public static final String COL_ANSWER_RIGHT = "answer_right";
	public static final String COL_ANSWER_USER = "answer_user";
	public static final String COL_SUCCESS = "success";


	private Service insertResults;
	private Service refreshQuestions;

	public EntityImpl() throws Exception
	{
		insertResults = Outside.service(this,"gus06.sys.learning1.engine.cx.insert.results");
		refreshQuestions = Outside.service(this,"gus06.sys.learning1.engine.refresh.questions");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Map config = (Map) o[0];
		Connection cx = (Connection) o[1];
		String question = (String) o[2];
		String answerRight = (String) o[3];
		String answerUser = (String) o[4];
		
		Map data = new HashMap();
		data.put(COL_DATE, new Date());
		data.put(COL_QUESTION, question);
		data.put(COL_ANSWER_RIGHT, answerRight);
		data.put(COL_ANSWER_USER, answerUser);
		data.put(COL_SUCCESS, false);
		
		insertResults.p(new Object[]{cx, data});
		refreshQuestions.p(new Object[]{config,cx});
		cx.close();
	}
}