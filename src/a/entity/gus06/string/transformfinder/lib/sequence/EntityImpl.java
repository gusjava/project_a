package a.entity.gus06.string.transformfinder.lib.sequence;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150927";}
	
	public static final String OFFSET = "seq_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("count",Outside.service(this,"gus06.string.transform.sequence.count"));
		put("count_distinct",Outside.service(this,"gus06.string.transform.sequence.count.distinct"));
		put("count_empty",Outside.service(this,"gus06.string.transform.sequence.count.empty"));
		put("count_notempty",Outside.service(this,"gus06.string.transform.sequence.count.notempty"));
		
		put("each_distinct",Outside.service(this,"gus06.string.transform.sequence.each.distinct"));
		put("each_same",Outside.service(this,"gus06.string.transform.sequence.each.same"));
		put("each_samelength",Outside.service(this,"gus06.string.transform.sequence.each.samelength"));
		
		put("dup_all",Outside.service(this,"gus06.string.transform.sequence.duplicate.all"));
		put("dup_first",Outside.service(this,"gus06.string.transform.sequence.duplicate.first"));
		put("dup_last",Outside.service(this,"gus06.string.transform.sequence.duplicate.last"));
		
		put("invert",Outside.service(this,"gus06.string.transform.sequence.order.invert"));
		put("permute",Outside.service(this,"gus06.string.transform.sequence.order.permute"));
		put("permute_inv",Outside.service(this,"gus06.string.transform.sequence.order.permute.inv"));
		put("shuffle",Outside.service(this,"gus06.string.transform.sequence.order.shuffle"));
		put("sort",Outside.service(this,"gus06.string.transform.sequence.order.sort"));
		put("sort_i",Outside.service(this,"gus06.string.transform.sequence.order.sort_i"));
		put("sortinv",Outside.service(this,"gus06.string.transform.sequence.order.sortinv"));
		put("sortinv_i",Outside.service(this,"gus06.string.transform.sequence.order.sortinv_i"));
		put("sortinv_n",Outside.service(this,"gus06.string.transform.sequence.order.sortinv_n"));
		put("sortlength",Outside.service(this,"gus06.string.transform.sequence.order.sortlength"));
		put("sortnum",Outside.service(this,"gus06.string.transform.sequence.order.sortnum"));
		
		put("rm_doubloon",Outside.service(this,"gus06.string.transform.sequence.remove.doubloon"));
		put("rm_empty",Outside.service(this,"gus06.string.transform.sequence.remove.empty"));
		put("rm_first",Outside.service(this,"gus06.string.transform.sequence.remove.first"));
		put("rm_last",Outside.service(this,"gus06.string.transform.sequence.remove.last"));
		
		put("double_max",Outside.service(this,"gus06.string.transform.sequence.double1.max"));
		put("double_min",Outside.service(this,"gus06.string.transform.sequence.double1.min"));
		put("double_sum",Outside.service(this,"gus06.string.transform.sequence.double1.sum"));
		put("double_avg",Outside.service(this,"gus06.string.transform.sequence.double1.avg"));
		put("double_variance",Outside.service(this,"gus06.string.transform.sequence.double1.variance"));
		
		put("int_max",Outside.service(this,"gus06.string.transform.sequence.integer.max"));
		put("int_min",Outside.service(this,"gus06.string.transform.sequence.integer.min"));
		put("int_sum",Outside.service(this,"gus06.string.transform.sequence.integer.sum"));
		put("int_avg",Outside.service(this,"gus06.string.transform.sequence.integer.avg"));
		put("int_variance",Outside.service(this,"gus06.string.transform.sequence.integer.variance"));
		
		put("length_max",Outside.service(this,"gus06.string.transform.sequence.length.max"));
		put("length_min",Outside.service(this,"gus06.string.transform.sequence.length.min"));
		put("length_sum",Outside.service(this,"gus06.string.transform.sequence.length.sum"));
		put("length_avg",Outside.service(this,"gus06.string.transform.sequence.length.avg"));
		put("length_variance",Outside.service(this,"gus06.string.transform.sequence.length.variance"));
		put("length_toseq",Outside.service(this,"gus06.string.transform.sequence.length.tosequence"));
		
		put("trim",Outside.service(this,"gus06.string.transform.sequence.trim"));
		put("trimstart",Outside.service(this,"gus06.string.transform.sequence.trim.start"));
		put("trimend",Outside.service(this,"gus06.string.transform.sequence.trim.end"));
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(map.containsKey(s)) return map.get(s);
		return null;
	}
	
	public Object g() throws Exception
	{return map;}
}
