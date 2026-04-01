package a.entity.gus06.sys.dirsearch1.textextractor.build;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200124";}
	
	public static final String OPTION_ = "";
	public static final String OPTION_I = "i";
	public static final String OPTION_N = "n";
	
	public static final String DEFAULT_OPTION = OPTION_N;


	private Service buildContains;
	private Service buildContains_i;
	private Service buildContains_n;
	
	public EntityImpl() throws Exception
	{
		buildContains = Outside.service(this,"gus06.sys.dirsearch1.textextractor.build1.contains");
		buildContains_i = Outside.service(this,"gus06.sys.dirsearch1.textextractor.build1.contains_i");
		buildContains_n = Outside.service(this,"gus06.sys.dirsearch1.textextractor.build1.contains_n");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Extractor((String) obj);
	}
	
	
	private class Extractor implements T, R, V
	{
		private String search;
		private String option;
		private Color color;
		private T extr;
		
		
		public Extractor(String search) throws Exception
		{
			this.search = search;
			changeOption(DEFAULT_OPTION);
		}
		
		
		private void changeOption(String option) throws Exception
		{
			this.option = option;
			extr = rebuild();
		}
		
		private void changeSearch(String search) throws Exception
		{
			this.search = search;
			extr = rebuild();
		}
		
		private T rebuild() throws Exception
		{
			if(option.equals(OPTION_)) return (T) buildContains.t(search);
			if(option.equals(OPTION_I)) return (T) buildContains_i.t(search);
			if(option.equals(OPTION_N)) return (T) buildContains_n.t(search);
			
			throw new Exception("Unknown option value: "+option);
		}
		
		
		
		public Object t(Object obj) throws Exception
		{return extr.t(obj);}
		
		
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("search")) return search;
			if(key.equals("option")) return option;
			if(key.equals("color")) return color;
			
			if(key.equals("keys")) return new String[]{"search","option","color"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("search")) {changeSearch((String) obj);return;}
			if(key.equals("option")) {changeOption((String) obj);return;}
			if(key.equals("color")) {color = (Color) obj;return;}
			
			throw new Exception("Unknown key: "+key);
		}
	}
}