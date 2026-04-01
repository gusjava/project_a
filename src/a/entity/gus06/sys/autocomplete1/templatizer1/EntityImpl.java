package a.entity.gus06.sys.autocomplete1.templatizer1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20160518";}
	
	
	public static final String KEY_UPPER = "AAA";
	public static final String KEY_LOWER = "aaa";
	
	public static final String KEY_CAMELCASE = "aAaA";
	public static final String KEY_PASCALCASE = "AaAa";
	
	public static final String KEY_UPPER_SNAKECASE = "A_A";
	public static final String KEY_UPPER_KEBABCASE = "A-A";
	public static final String KEY_UPPER_POINTCASE = "A.A";
	public static final String KEY_UPPER_SPACECASE = "A A";
	
	public static final String KEY_LOWER_SNAKECASE = "a_a";
	public static final String KEY_LOWER_KEBABCASE = "a-a";
	public static final String KEY_LOWER_POINTCASE = "a.a";
	public static final String KEY_LOWER_SPACECASE = "a a";


	private Service variants;
	private Service format;
	
	
	public EntityImpl() throws Exception
	{
		variants = Outside.service(this,"gus06.string.case1.variants");
		format = Outside.service(this,"gus06.string.transform.format.brackets.curly");
		
	}


	public void p(Object obj) throws Exception
	{v("name",obj);}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String selected = comp.getSelectedText();
		if(selected==null || selected.trim().equals("")) return;
		
		Map map = (Map) variants.t(selected);
		Map map1 = new HashMap();
		
		String upper = (String) map.get(KEY_UPPER);
		String lower = (String) map.get(KEY_LOWER);
		String camelCase = (String) map.get(KEY_CAMELCASE);
		String pascalCase = (String) map.get(KEY_PASCALCASE);
		
		String upperSnakeCase = (String) map.get(KEY_UPPER_SNAKECASE);
		String upperKebabCase = (String) map.get(KEY_UPPER_KEBABCASE);
		String upperPointCase = (String) map.get(KEY_UPPER_POINTCASE);
		String upperSpaceCase = (String) map.get(KEY_UPPER_SPACECASE);
		
		String lowerSnakeCase = (String) map.get(KEY_LOWER_SNAKECASE);
		String lowerKebabCase = (String) map.get(KEY_LOWER_KEBABCASE);
		String lowerPointCase = (String) map.get(KEY_LOWER_POINTCASE);
		String lowerSpaceCase = (String) map.get(KEY_LOWER_SPACECASE);
	
		add(map1,upper,"{>"+key+"._upper}");
		add(map1,lower,"{>"+key+"._lower}");
		add(map1,camelCase,"{>"+key+"._to_camelcase}");
		add(map1,pascalCase,"{>"+key+"._to_pascalcase}");
		
		add(map1,upperSnakeCase,"{>"+key+"._upper_snakecase}");
		add(map1,upperKebabCase,"{>"+key+"._upper_kebabcase}");
		add(map1,upperPointCase,"{>"+key+"._upper_pointcase}");
		add(map1,upperSpaceCase,"{>"+key+"._upper_spacecase}");
		
		add(map1,lowerSnakeCase,"{>"+key+"._lower_snakecase}");
		add(map1,lowerKebabCase,"{>"+key+"._lower_kebabcase}");
		add(map1,lowerPointCase,"{>"+key+"._lower_pointcase}");
		add(map1,lowerSpaceCase,"{>"+key+"._lower_spacecase}");
		
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		text = (String) format.t(text);
		
		Iterator it = map1.keySet().iterator();
		while(it.hasNext())
		{
			String k = (String) it.next();
			String v = (String) map1.get(k);
			
			text = text.replace(k,v);
		}
		
		pos = Math.min(pos,text.length());
		comp.setText(text);
		comp.setCaretPosition(pos);
	}
	
	
	
	private void add(Map map, String key, String value)
	{
		if(!map.containsKey(key)) map.put(key,value);
	}
}