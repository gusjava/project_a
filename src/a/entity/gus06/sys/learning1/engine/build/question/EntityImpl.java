package a.entity.gus06.sys.learning1.engine.build.question;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250709";}

	public static final String COL_CODE = "code";
	public static final String COL_STATUS = "status";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_NB_TOTAL = "nb_total";
	public static final String COL_NB_TOTAL_SUCCESS = "nb_total_success";
	public static final String COL_NB_TOTAL_FAIL = "nb_total_fail";
	public static final String COL_NB_LATEST_SUCCESS = "nb_latest_success";
	
	public static final String STATUS_EMPTY = "EMPTY";
	
	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		if(code==null) throw new Exception("Null code not allowed for question building");
		
		Map map = new HashMap();
		map.put(COL_CODE, code);
		map.put(COL_STATUS, STATUS_EMPTY);
		map.put(COL_DATE_CREATED, new Date());
		map.put(COL_NB_TOTAL, 0);
		map.put(COL_NB_TOTAL_SUCCESS, 0);
		map.put(COL_NB_TOTAL_FAIL, 0);
		map.put(COL_NB_LATEST_SUCCESS, 0);
		
		return map;
	}
}