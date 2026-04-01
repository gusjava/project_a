package a.entity.gus06.file.svg.convert.topng;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250817";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = o[0];
		File output = o[1];
		
		String svgURI = input.toURI().toString();
		TranscoderInput tInput = new TranscoderInput(svgURI);
		
		FileOutputStream outputStream = new FileOutputStream(output);
		TranscoderOutput tOutput = new TranscoderOutput(outputStream);
		
		PNGTranscoder transcoder = new PNGTranscoder();
		transcoder.transcode(tInput, tOutput);
		
		outputStream.flush();
		outputStream.close();
	}
}
