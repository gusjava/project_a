package a.entity.gus06.swing.textcomp.highlight.painter.manager1;

import a.framework.*;
import java.awt.Color;
import java.awt.Composite;
import java.awt.AlphaComposite;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20151107";}
	
	public static final Composite ALPHA = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f);

	public static final Color COLOR_SEARCH = new Color(51,204,255);
	public static final Color COLOR_SEARCH_I = new Color(102,153,255);
	public static final Color COLOR_REGEX = new Color(255,102,51);
	public static final Color COLOR_HIGH1 = new Color(102,255,0);
	public static final Color COLOR_REPLACE = new Color(153,153,153);
	public static final Color COLOR_F1 = new Color(0,153,153);
	
	public static final String KEY_SEARCH = "search";
	public static final String KEY_SEARCH_I = "search_i";
	public static final String KEY_REGEX = "regex";
	public static final String KEY_HIGH1 = "high1";
	public static final String KEY_REPLACE = "replace";
	public static final String KEY_F1 = "f1";
	

	private Service buildPainter;
	
	private Object painter_search;
	private Object painter_search_i;
	private Object painter_regex;
	private Object painter_high1;
	private Object painter_replace;
	private Object painter_f1;
	
	
	public EntityImpl() throws Exception
	{
		buildPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.builder1");
		
		painter_search = buildPainter.t(COLOR_SEARCH);
		painter_search_i = buildPainter.t(COLOR_SEARCH_I);
		painter_regex = buildPainter.t(COLOR_REGEX);
		painter_high1 = buildPainter.t(COLOR_HIGH1);
		painter_replace = buildPainter.t(COLOR_REPLACE);
		painter_f1 = buildPainter.t(COLOR_F1);
		
		((V) painter_search).v("alpha",ALPHA);
		((V) painter_search_i).v("alpha",ALPHA);
		((V) painter_regex).v("alpha",ALPHA);
		((V) painter_high1).v("alpha",ALPHA);
		((V) painter_f1).v("alpha",ALPHA);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals(KEY_SEARCH)) return painter_search;
		if(key.equals(KEY_SEARCH_I)) return painter_search_i;
		if(key.equals(KEY_REGEX)) return painter_regex;
		if(key.equals(KEY_HIGH1)) return painter_high1;
		if(key.equals(KEY_HIGH1)) return painter_high1;
		if(key.equals(KEY_REPLACE)) return painter_replace;
		if(key.equals(KEY_F1)) return painter_f1;
		
		throw new Exception("Unknown key: "+key);
	}
}
