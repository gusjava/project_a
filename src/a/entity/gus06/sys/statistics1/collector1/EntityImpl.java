package a.entity.gus06.sys.statistics1.collector1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, P, R, E {

	public String creationDate() {return "20170601";}


	private Service categorize;
	private Service findSymbols;
	private Service freqmap;

	private List list_data;
	private List list_length;
	private List list_number;
	private Set set_symbols;
	
	private int nb_null;
	private int nb_empty;
	private int nb_number;
	private int nb_true;
	private int nb_false;
	
	private double data_sum;
	private double data_min;
	private double data_max;
	
	private long length_sum;
	private int length_min;
	private int length_max;
	
	private Map freq;
	
	

	public EntityImpl() throws Exception
	{
		categorize = Outside.service(this,"gus06.string.transform.categorize.code2");
		findSymbols = Outside.service(this,"gus06.string.extract.symbol.a");
		freqmap = Outside.service(this,"gus06.map.freqmap.append");
		
		list_data = new ArrayList();
		list_length = new ArrayList();
		list_number = new ArrayList();
		set_symbols = new HashSet();
		
		nb_null = 0;
		nb_empty = 0;
		nb_number = 0;
		nb_true = 0;
		nb_false = 0;
		
		data_sum = 0;
		data_min = Integer.MAX_VALUE;
		data_max = Integer.MIN_VALUE;
		
		length_sum = 0;
		length_min = Integer.MAX_VALUE;
		length_max = Integer.MIN_VALUE;
		
		freq = new HashMap();
	}
	
	
	
	public void e() throws Exception
	{
		list_data.clear();
		list_length.clear();
		list_number.clear();
		set_symbols.clear();
		
		nb_null = 0;
		nb_empty = 0;
		nb_number = 0;
		nb_true = 0;
		nb_false = 0;
		
		data_sum = 0;
		data_min = Integer.MAX_VALUE;
		data_max = Integer.MIN_VALUE;
		
		length_sum = 0;
		length_min = Integer.MAX_VALUE;
		length_max = Integer.MIN_VALUE;
		
		freq.clear();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String data = (String) obj;
		
		int code = Integer.parseInt((String) categorize.t(data));
		int length = data!=null ? data.length() : -1;
		
		//0 : null;
		//1 : ""
		//2 : 0
		//3 : n>0
		//4 : double
		//5 : true
		//6 : false
		//7 : alphanum1
		//8 : text line
		//9 : text multiline
		
		boolean isNull = code==0;
		boolean isEmpty = code==1;
		boolean isNumber = code==2 || code==3 || code==4;
		boolean isTrue = code==5;
		boolean isFalse = code==6;
		
		if(isNull) nb_null++;
		if(isEmpty) nb_empty++;
		if(isNumber) nb_number++;
		if(isTrue) nb_true++;
		if(isFalse) nb_false++;
		
		if(!isNull)
		{
			length_sum+=length;
			list_length.add(Integer.valueOf(length));
			if(length_min>length) length_min = length;
			if(length_max<length) length_max = length;
			
			list_data.add(data);
			freqmap.p(new Object[]{freq,data});
		}
		
		if(isNumber)
		{
			double value = Double.parseDouble(data);
			list_number.add(Double.valueOf(value));
			data_sum+=value;
			if(data_min>value) data_min = value;
			if(data_max<value) data_max = value;
		}
		
		if(!isNull && !isNumber && !isTrue && !isFalse)
		{
			List l = (List) findSymbols.t(data);
			set_symbols.addAll(l);
		}
		
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list_data")) return list_data;
		if(key.equals("list_length")) return list_length;
		if(key.equals("list_number")) return list_number;
		if(key.equals("set_symbols")) return set_symbols;
		if(key.equals("freq")) return freq;
		
		if(key.equals("nb_null")) return Integer.valueOf(nb_null);
		if(key.equals("nb_empty")) return Integer.valueOf(nb_empty);
		if(key.equals("nb_number")) return Integer.valueOf(nb_number);
		if(key.equals("nb_true")) return Integer.valueOf(nb_true);
		if(key.equals("nb_false")) return Integer.valueOf(nb_false);
		
		if(key.equals("data_sum")) return Double.valueOf(data_sum);
		if(key.equals("data_min")) return Double.valueOf(data_min);
		if(key.equals("data_max")) return Double.valueOf(data_max);
		
		if(key.equals("length_sum")) return Long.valueOf(length_sum);
		if(key.equals("length_min")) return Integer.valueOf(length_min);
		if(key.equals("length_max")) return Integer.valueOf(length_max);
		
		if(key.equals("keys")) return new String[]{
			"list_data","list_length","list_number","set_symbols","freq",
			"nb_null","nb_empty","nb_number","nb_true","nb_false",
			"data_sum","data_min","data_max",
			"length_sum","length_min","length_max"};
		
		throw new Exception("Unknown key: "+key);
	}
}
