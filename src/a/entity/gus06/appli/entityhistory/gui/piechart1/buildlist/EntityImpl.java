package a.entity.gus06.appli.entityhistory.gui.piechart1.buildlist;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201220";}

	public static final long DAY_TO_MILLI = 86400000L;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null || map.isEmpty()) return new ArrayList();
		int nb = map.size();
		
		List names = new ArrayList(map.keySet());
		Collections.sort(names);
		
		List dates = new ArrayList(map.values());
		Collections.sort(dates);
		
		String date0 = (String) dates.get(0);
		long time0 = dateToTime(date0);
		
		String date1 = (String) dates.get(dates.size()-1);
		long time1 = dateToTime(date1);
		
		long nbDaysMax = nbDays(time0,time1);
		
		List list = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			String name = (String) names.get(i);
			String date = (String) map.get(name);
			
			long time = dateToTime(date);
			long nbDays = nbDays(time0,time);
			
			double namePos = (double)i/(double)nb;
			double datePos = (double)nbDays/(double)nbDaysMax;
			
			Map m = new HashMap();
			
			m.put("name",name);
			m.put("date",date);
			m.put("nbDays",nbDays);
			m.put("namePos",namePos);
			m.put("datePos",datePos);
			
			list.add(m);
		}
		
		Collections.sort(list,new Comparator(){
			public int compare(Object o1, Object o2)
			{
				Map m1 = (Map) o1;
				Map m2 = (Map) o2;
				
				Long nb1 = (Long) m1.get("nbDays");
				Long nb2 = (Long) m2.get("nbDays");
				
				return nb1.compareTo(nb2);
			}
		});
		
		return list;
	}
	
	
	private long dateToTime(String date) throws Exception
	{return sdf.parse(date).getTime();}
	
	private long nbDays(long t1, long t2)
	{return (t2-t1)/DAY_TO_MILLI;}
}