package a.entity.gus06.sys.learning1.engine.refresh.questions;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250712";}

	// RESULTS

	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_QUESTION = "question";
	public static final String COL_ANSWER_RIGHT = "answer_right";
	public static final String COL_ANSWER_USER = "answer_user";
	public static final String COL_SUCCESS = "success";
	
	// QUESTIONS
	
	public static final String COL_CODE = "code";
	public static final String COL_STATUS = "status";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_FIRST = "date_first";
	public static final String COL_DATE_LATEST = "date_latest";
	public static final String COL_NB_TOTAL = "nb_total";
	public static final String COL_NB_TOTAL_SUCCESS = "nb_total_success";
	public static final String COL_NB_TOTAL_FAIL = "nb_total_fail";
	public static final String COL_NB_LATEST_SUCCESS = "nb_latest_success";
	public static final String COL_NB_LATEST_FAIL = "nb_latest_fail";
	
	// STATUS
	
	public static final String STATUS_EMPTY = "EMPTY";
	public static final String STATUS_RECENT = "RECENT";
	public static final String STATUS_UNCERTAIN = "UNCERTAIN";
	public static final String STATUS_SURE = "SURE";
	public static final String STATUS_OVER = "OVER";

	// CONFIG

	public static final String KEY_RECENT_LIMIT = "recent_limit";
	public static final String KEY_SURE_THRESHOLD1 = "sure_threshold1";
	public static final String KEY_SURE_THRESHOLD2 = "sure_threshold2";
	public static final String KEY_OVER_THRESHOLD1 = "over_threshold1";
	public static final String KEY_OVER_THRESHOLD2 = "over_threshold2";
	


	private Service findResults;
	private Service updateQuestions;

	public EntityImpl() throws Exception
	{
		findResults = Outside.service(this,"gus06.sys.learning1.engine.cx.findall.results.list");
		updateQuestions = Outside.service(this,"gus06.sys.learning1.engine.cx.update.questions");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map config = (Map) o[0];
		Connection cx = (Connection) o[1];
		
		int recentLimit = getInt(config, KEY_RECENT_LIMIT);
		int sureThreshold1 = getInt(config, KEY_SURE_THRESHOLD1);
		double sureThreshold2 = getDouble(config, KEY_SURE_THRESHOLD2);
		int overThreshold1 = getInt(config, KEY_OVER_THRESHOLD1);
		double overThreshold2 = getDouble(config, KEY_OVER_THRESHOLD2);
		
		List results = (List) findResults.t(cx);
		Map map = new HashMap();
		
		for(int i=0;i<results.size();i++)
		{
			Map data = (Map) results.get(i);
			String question = (String) data.get(COL_QUESTION);
			Boolean success = toBoolean(data.get(COL_SUCCESS));
			append(map, question, success);
		}
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String question = (String) it.next();
			List detail = (List) map.get(question);
			int total = detail.size();
			
			int totalSuccess = 0;
			int totalFail = 0;
			int latestSuccess = 0;
			
			for(int i=0;i<total;i++)
			{
				Boolean success = (Boolean) detail.get(total-1-i);
				if(success) totalSuccess++;
				else totalFail++;
				if(success && totalFail==0) latestSuccess++;
			}
			
			double ratio = (double) totalFail/ (double) totalSuccess;
			
			Map row = new HashMap();
			row.put(COL_CODE, question);
			row.put(COL_NB_TOTAL, total);
			row.put(COL_NB_TOTAL_SUCCESS, totalSuccess);
			row.put(COL_NB_TOTAL_FAIL, totalFail);
			row.put(COL_NB_LATEST_SUCCESS, latestSuccess);
			
			if(total < recentLimit)
			{
				row.put(COL_STATUS, STATUS_RECENT);
			}
			else if(latestSuccess > overThreshold1 && ratio < overThreshold2)
			{
				row.put(COL_STATUS, STATUS_OVER);
			}
			else if(latestSuccess > sureThreshold1 && ratio < sureThreshold2)
			{
				row.put(COL_STATUS, STATUS_SURE);
			}
			else row.put(COL_STATUS, STATUS_UNCERTAIN);
			
			updateQuestions.p(new Object[]{cx, row});
		}
	}
	
	
	private void append(Map map, String key, Object value)
	{
		if(!map.containsKey(key)) map.put(key,new ArrayList());
		List l = (List) map.get(key);
		l.add(value);
	}
	
	private int getInt(Map map, String key)
	{return Integer.parseInt(""+map.get(key));}
	
	private double getDouble(Map map, String key)
	{return Double.parseDouble(""+map.get(key));}
	
	private Boolean toBoolean(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Boolean) return (Boolean) obj;
		if(obj instanceof Integer) return ((Integer) obj).intValue()==1;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

}