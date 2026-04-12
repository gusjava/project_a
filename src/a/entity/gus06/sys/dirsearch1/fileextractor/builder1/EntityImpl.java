package a.entity.gus06.sys.dirsearch1.fileextractor.builder1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200123";}
	


	private Service factory;
	private Service textExtractorBuilder;
	private Service colorArray;
	private Service toList;


	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"factory#gus06.sys.dirsearch1.fileextractor.holder1");
		textExtractorBuilder = Outside.service(this,"gus06.sys.dirsearch1.textextractor.build");
		colorArray = Outside.service(this,"gus06.sys.dirsearch1.tool.terms.colorarray");
		toList = Outside.service(this,"gus06.find.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		T termBuilder = (T) o[1];
		
		if(input==null) throw new Exception("Invalid input: "+input);
		
		boolean strict = false;
		if(input.startsWith("!"))
		{
			strict = true;
			input = input.substring(1);
		}
		
		List blockExtrList = new ArrayList(); 
		List terms = buildTerms(input,termBuilder);
		
		for(int i=0;i<terms.size();i++)
		{
			T blockExtr = (T) textExtractorBuilder.t(terms.get(i));
			Color c = (Color) colorArray.t(i);
			((V) blockExtr).v("color",c);
			if(strict) ((V) blockExtr).v("option","");
			
			blockExtrList.add(blockExtr);
		}
		
		V extractor = (V) factory.g();
		extractor.v("blockExtrList",blockExtrList);
		return extractor;
	}
	
	
	
	private List buildTerms(String input, T termBuilder) throws Exception
	{
		if(termBuilder!=null) return (List) toList.t(termBuilder.t(input));
		return buildTermsDefault(input);
	}
	
	
	
	private List buildTermsDefault(String input)
	{
		List list = new ArrayList();
		if(input.startsWith("'"))
		{
			list.add(input.substring(1));
		}
		else
		{
			String[] nn = input.trim().split(" +");
			for(String n : nn) if(!list.contains(n))
			list.add(n.trim());
		}
		return list;
	}
}