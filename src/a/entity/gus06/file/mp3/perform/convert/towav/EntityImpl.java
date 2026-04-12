package a.entity.gus06.file.mp3.perform.convert.towav;

import a.framework.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250520";}

	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File mp3File = o[0];
		File wavFile = o[1];
		
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mp3File)))
		{
			Bitstream bitstream = new Bitstream(bis);
			Decoder decoder = new Decoder();
			
			ByteArrayOutputStream pcmOut = new ByteArrayOutputStream();
			
			AudioFormat format = null;
			Header frameHeader = null;
			
			while ((frameHeader = bitstream.readFrame()) != null)
			{
				SampleBuffer output = (SampleBuffer) decoder.decodeFrame(frameHeader, bitstream);

				// securite : utiliser la longueur effective du buffer
				short[] buffer = output.getBuffer();
				int validSamples = output.getBufferLength(); // important
				
				// Initialisation du format a partir de la premiere frame
				if (format == null)
				{
					int channels = output.getChannelCount();
					int sampleRate = output.getSampleFrequency();
					int sampleSize = 16; // toujours 16 bits avec JLayer

					format = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED,
						sampleRate,
						sampleSize,
						channels,
						channels * (sampleSize / 8), // frame size
						sampleRate,
						false // little endian
					);
				}
				
				// conversion short[] -> byte[] en little-endian (securise avec ByteBuffer)
				ByteBuffer bb = ByteBuffer.allocate(validSamples * 2);
				bb.order(ByteOrder.LITTLE_ENDIAN);
				for (int i = 0; i < validSamples; i++)
				{
					bb.putShort(buffer[i]);
				}
				pcmOut.write(bb.array());

				bitstream.closeFrame();
			}
			
			byte[] pcmBytes = pcmOut.toByteArray();

			if (format == null) throw new Exception("No audio frames decoded (empty/invalid MP3?)");
			
			try (
				ByteArrayInputStream bais = new ByteArrayInputStream(pcmBytes);
				AudioInputStream ais = new AudioInputStream(bais, format, pcmBytes.length / format.getFrameSize()))
			{
				AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wavFile);
			}
		}
	}
}