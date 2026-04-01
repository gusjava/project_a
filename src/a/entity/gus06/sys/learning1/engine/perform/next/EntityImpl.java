package a.entity.gus06.sys.learning1.engine.perform.next;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250708";}
	
	public static final String KEY_CORPUS_SIZE = "corpus_size";
	public static final String KEY_WEIGHT_EMPTY = "weight_empty";
	public static final String KEY_WEIGHT_RECENT = "weight_recent";
	public static final String KEY_WEIGHT_UNCERTAIN = "weight_uncertain";
	public static final String KEY_WEIGHT_SURE = "weight_sure";
	public static final String KEY_WEIGHT_OVER = "weight_over";
	
//	public static final int DEFAULT_CORPUS_SIZE = 5;
//	public static final int DEFAULT_WEIGHT_EMPTY = 50;
//	public static final int DEFAULT_WEIGHT_RECENT = 5;
//	public static final int DEFAULT_WEIGHT_UNCERTAIN = 8;
//	public static final int DEFAULT_WEIGHT_SURE = 1;
//	public static final int DEFAULT_WEIGHT_OVER = 0;
	
	public static final String COL_STATUS = "status";
	
	public static final String STATUS_EMPTY = "EMPTY";
	public static final String STATUS_RECENT = "RECENT";
	public static final String STATUS_UNCERTAIN = "UNCERTAIN";
	public static final String STATUS_SURE = "SURE";
	public static final String STATUS_OVER = "OVER";


	private Service findAllQuestions;
	private Service insertQuestions;
	private Service buildQuestion;
	private Service randomSet;
	private Service randomList;

	public EntityImpl() throws Exception
	{
		findAllQuestions = Outside.service(this,"gus06.sys.learning1.engine.cx.findall.questions.map");
		insertQuestions = Outside.service(this,"gus06.sys.learning1.engine.cx.insert.questions");
		buildQuestion = Outside.service(this,"gus06.sys.learning1.engine.build.question");
		randomSet = Outside.service(this,"gus06.data.perform.random.set");
		randomList = Outside.service(this,"gus06.data.perform.random.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map config = (Map) o[0];
		Connection cx = (Connection) o[1];
		Set codes = (Set) o[2];
		
		int corpusSize = getInt(config, KEY_CORPUS_SIZE);
		int weightEmpty = getInt(config, KEY_WEIGHT_EMPTY);
		int weightRecent = getInt(config, KEY_WEIGHT_RECENT);
		int weightUncertain = getInt(config, KEY_WEIGHT_UNCERTAIN);
		int weightSure = getInt(config, KEY_WEIGHT_SURE);
		int weightOver = getInt(config, KEY_WEIGHT_OVER);
		
		Map data = (Map) findAllQuestions.t(cx);
		Set corpus = new HashSet(data.keySet());
		Set notUsed = new HashSet(codes);
		notUsed.removeAll(corpus);
		
		int activeSize = 0;
		Iterator it = data.keySet().iterator();
		while(it.hasNext())
		{
			String code = (String) it.next();
			Map row = (Map) data.get(code);
			String status = (String) row.get(COL_STATUS);
			
			if(status.equals(STATUS_EMPTY) || status.equals(STATUS_RECENT) || status.equals(STATUS_UNCERTAIN))
			activeSize++;
		}
		
		while(activeSize<corpusSize && notUsed.size()>0)
		{
			String newCode = (String) randomSet.t(notUsed);
			Map m = (Map) buildQuestion.t(newCode);
			insertQuestions.p(new Object[]{cx,m});
			
			data.put(newCode,m);
			notUsed.remove(newCode);
			corpus.add(newCode);
			activeSize++;
		}
		
		List weights = new ArrayList();
		it = data.keySet().iterator();
		while(it.hasNext())
		{
			String code = (String) it.next();
			Map row = (Map) data.get(code);
			String status = (String) row.get(COL_STATUS);
			
			if(status.equals(STATUS_EMPTY))
				fill(weights, code, weightEmpty);
			else if(status.equals(STATUS_RECENT))
				fill(weights, code, weightRecent);
			else if(status.equals(STATUS_UNCERTAIN))
				fill(weights, code, weightUncertain);
			else if(status.equals(STATUS_SURE))
				fill(weights, code, weightSure);
			else if(status.equals(STATUS_OVER))
				fill(weights, code, weightOver);
		}
		cx.close();
		return randomList.t(weights);
	}
	
	
	private int getInt(Map map, String key)
	{return Integer.parseInt(""+map.get(key));}
	
	private void fill(List weights, String code, int times)
	{for(int i=0;i<times;i++) weights.add(code);}
}