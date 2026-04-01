package a.entity.gus06.tostring.desc.short1;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.util.Date;
import java.net.URL;
import java.awt.Color;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}


	private Service shortString;
	private Service colToString;
	private Service mapToString;
	private Service fileToString;
	private Service dateToString;
	private Service classToString;
	private Service colorToString;
	private Service urlToString;
	private Service imageToString;
	private Service rectangleToString;
	private Service entityToString;
	private Service exceptionToString;
	private Service renderedImageToString;
	
	private Service arrayToString;
	private Service arrayBooleanToString;
	private Service arrayByteToString;
	private Service arrayCharToString;
	private Service arrayDoubleToString;
	private Service arrayFloatToString;
	private Service arrayIntToString;
	private Service arrayLongToString;
	private Service arrayShortToString;
	
	private Service array2ToString;
	private Service array2BooleanToString;
	private Service array2ByteToString;
	private Service array2CharToString;
	private Service array2DoubleToString;
	private Service array2FloatToString;
	private Service array2IntToString;
	private Service array2LongToString;
	private Service array2ShortToString;
	

	public EntityImpl() throws Exception
	{
		shortString = Outside.service(this,"gus06.tostring.desc.short1.string");
		colToString = Outside.service(this,"gus06.tostring.desc.short1.collection");
		mapToString = Outside.service(this,"gus06.tostring.desc.short1.map");
		fileToString = Outside.service(this,"gus06.tostring.desc.short1.file");
		dateToString = Outside.service(this,"gus06.tostring.desc.short1.date");
		classToString = Outside.service(this,"gus06.tostring.desc.short1.class1");
		colorToString = Outside.service(this,"gus06.tostring.desc.short1.color");
		urlToString = Outside.service(this,"gus06.tostring.desc.short1.url");
		imageToString = Outside.service(this,"gus06.tostring.desc.short1.image");
		rectangleToString = Outside.service(this,"gus06.tostring.desc.short1.rectangle");
		entityToString = Outside.service(this,"gus06.tostring.desc.short1.entity");
		exceptionToString = Outside.service(this,"gus06.tostring.desc.short1.exception");
		renderedImageToString = Outside.service(this,"gus06.tostring.desc.short1.renderedimage");
		
		arrayToString = Outside.service(this,"gus06.tostring.desc.short1.array");
		arrayBooleanToString = Outside.service(this,"gus06.tostring.desc.short1.arrayboolean");
		arrayByteToString = Outside.service(this,"gus06.tostring.desc.short1.arraybyte");
		arrayCharToString = Outside.service(this,"gus06.tostring.desc.short1.arraychar");
		arrayDoubleToString = Outside.service(this,"gus06.tostring.desc.short1.arraydouble");
		arrayFloatToString = Outside.service(this,"gus06.tostring.desc.short1.arrayfloat");
		arrayIntToString = Outside.service(this,"gus06.tostring.desc.short1.arrayint");
		arrayLongToString = Outside.service(this,"gus06.tostring.desc.short1.arraylong");
		arrayShortToString = Outside.service(this,"gus06.tostring.desc.short1.arrayshort");
		
		array2ToString = Outside.service(this,"gus06.tostring.desc.short1.array2");
		array2BooleanToString = Outside.service(this,"gus06.tostring.desc.short1.array2boolean");
		array2ByteToString = Outside.service(this,"gus06.tostring.desc.short1.array2byte");
		array2CharToString = Outside.service(this,"gus06.tostring.desc.short1.array2char");
		array2DoubleToString = Outside.service(this,"gus06.tostring.desc.short1.array2double");
		array2FloatToString = Outside.service(this,"gus06.tostring.desc.short1.array2float");
		array2IntToString = Outside.service(this,"gus06.tostring.desc.short1.array2int");
		array2LongToString = Outside.service(this,"gus06.tostring.desc.short1.array2long");
		array2ShortToString = Outside.service(this,"gus06.tostring.desc.short1.array2short");
	}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "null";
		
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		if(obj instanceof String) return shortString.t(obj);
		if(obj instanceof Collection) return colToString.t(obj);
		if(obj instanceof Map) return mapToString.t(obj);
		if(obj instanceof File) return fileToString.t(obj);
		if(obj instanceof Date) return dateToString.t(obj);
		if(obj instanceof Class) return classToString.t(obj);
		if(obj instanceof Color) return colorToString.t(obj);
		if(obj instanceof URL) return urlToString.t(obj);
		if(obj instanceof Image) return imageToString.t(obj);
		if(obj instanceof Rectangle) return rectangleToString.t(obj);
		if(obj instanceof Entity) return entityToString.t(obj);
		if(obj instanceof Exception) return exceptionToString.t(obj);
		if(obj instanceof RenderedImage) return renderedImageToString.t(obj);
		
		if(obj instanceof boolean[][]) return array2BooleanToString.t(obj);
		if(obj instanceof byte[][]) return array2ByteToString.t(obj);
		if(obj instanceof char[][]) return array2CharToString.t(obj);
		if(obj instanceof double[][]) return array2DoubleToString.t(obj);
		if(obj instanceof float[][]) return array2FloatToString.t(obj);
		if(obj instanceof int[][]) return array2IntToString.t(obj);
		if(obj instanceof long[][]) return array2LongToString.t(obj);
		if(obj instanceof short[][]) return array2ShortToString.t(obj);
		if(obj instanceof Object[][]) return array2ToString.t(obj);
		
		if(obj instanceof boolean[]) return arrayBooleanToString.t(obj);
		if(obj instanceof byte[]) return arrayByteToString.t(obj);
		if(obj instanceof char[]) return arrayCharToString.t(obj);
		if(obj instanceof double[]) return arrayDoubleToString.t(obj);
		if(obj instanceof float[]) return arrayFloatToString.t(obj);
		if(obj instanceof int[]) return arrayIntToString.t(obj);
		if(obj instanceof long[]) return arrayLongToString.t(obj);
		if(obj instanceof short[]) return arrayShortToString.t(obj);
		if(obj instanceof Object[]) return arrayToString.t(obj);
		
		return className(obj);
	}
	
	private String className(Object obj)
	{return obj.getClass().getSimpleName();}
}
