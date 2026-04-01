package a.entity.gus06.file.wav.split.todir;

import a.framework.*;
import java.io.File;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFileFormat;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250910";}
	
	public static final double[] DEFAULT_PARAMS = new double[]{
		0.01, //windowDuration
		0.02, //silenceThreshold
		0.5,  //minSilenceDuration
		0.3   //marginDuration
	};
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) {handle((File) o[0], (File) o[1]);return;}
		if(o.length==3) {handle((File) o[0], (File) o[1], (double[]) o[2]);return;}
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	private void handle(File inputFile, File outputDir) throws Exception
	{
		handle(inputFile, outputDir, DEFAULT_PARAMS);
	}
	
	private void handle(File inputFile, File outputDir, double[] params) throws Exception
	{
		if(params==null) params = DEFAULT_PARAMS;
		if(params.length!=4) throw new Exception("Wrong params number: "+params.length);
		
		try (AudioInputStream ais = AudioSystem.getAudioInputStream(inputFile))
		{
			AudioFormat format = ais.getFormat();
			
			// AUDIO INFOS
			
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate(); //nombre de frames audio par seconde (Hz)
			long frameNb = ais.getFrameLength();
			
			double duration = frameNb / frameRate;
			long bytesNb = frameNb * frameSize;
			
			// PARAMETERS
			
			double windowDuration = params[0]; //duree de la fenetre d'analyse RMS
			double silenceThreshold = params[1]; //seuil de silence (0 à 1)
			double silenceDuration = params[2]; //duree du silence
			double marginDuration = params[3]; //duree du la marge
			
			int windowFrameNb = (int)(windowDuration * frameRate);
			int minSilenceFrameNb = (int)(silenceDuration * frameRate);
			int marginFrameNb = (int)(marginDuration * frameRate);
			
			// DATA
			
			List<byte[]> pendingFrames = new ArrayList<>();
			byte[] frame = new byte[frameSize];
			double[] window = new double[windowFrameNb];
			boolean windowFull = false;
			double sumSquares = 0.0;
			int silentFrameCount = 0;
			int segmentNb = 0;
			int pos = 0;
			
			int bytesRead;
			while ((bytesRead = ais.read(frame)) != -1)
			{
				if (bytesRead < frameSize)
					for (int i = bytesRead; i < frameSize; i++) frame[i] = 0;
				
				double sample = getSample(frame, format);
				double square = sample * sample;
				double previousSquare = window[pos];
				
				window[pos] = square;
				sumSquares += square - previousSquare;
				pos = (pos + 1) % windowFrameNb;
				
				if(!windowFull)
				{
					if (pos == 0) windowFull = true;
				}
				else
				{
					// window is full
					
					double rms = Math.sqrt(sumSquares / windowFrameNb);
					if (rms < silenceThreshold)
					{
						// Silence is beeing detected
						silentFrameCount++;
					}
					else
					{
						if(silentFrameCount>=minSilenceFrameNb)
							savePendingFrames(outputDir, pendingFrames, ++segmentNb, silentFrameCount, format, marginFrameNb);
						silentFrameCount = 0;
					}
					pendingFrames.add(frame.clone());
				}
			}
			
			if(silentFrameCount>=minSilenceFrameNb || pendingFrames.size()>0)
				savePendingFrames(outputDir, pendingFrames, ++segmentNb, silentFrameCount, format, marginFrameNb);
		}
	}
	
	
	private void savePendingFrames(
		File outputDir,
		List<byte[]> pendingFrames,
		int segmentNumber,
		int silentFrameCount,
		AudioFormat format,
		int marginFrameNb) throws Exception
	{
		if(pendingFrames.isEmpty()) return;
		int usefulFrames = pendingFrames.size() - silentFrameCount;
		if (usefulFrames <= 0)
		{
			pendingFrames.clear();
			return;
		}
		
		int frameSize = format.getFrameSize();
		int totalFrameNb = usefulFrames + 2*marginFrameNb;
		
		byte[] segment = new byte[totalFrameNb * frameSize];
		for (int i = 0; i < usefulFrames; i++)
		System.arraycopy(pendingFrames.get(i), 0, segment, (i + marginFrameNb) * frameSize, frameSize);
		
		String fileName = String.format("segment_%03d.wav", segmentNumber);
		File file = new File(outputDir, fileName);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(segment);
		try (AudioInputStream ais = new AudioInputStream(bais, format, totalFrameNb))
		{AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);}
		
		pendingFrames.clear();
	}
	
	private double getSample(byte[] frame, AudioFormat format)
	{
		int sampleSizeInBits = format.getSampleSizeInBits();
		boolean bigEndian = format.isBigEndian();
		AudioFormat.Encoding encoding = format.getEncoding();
		
		int bytesPerSample = sampleSizeInBits / 8;
		int frameSize = format.getFrameSize();
		
		// Pour simplifier, on ne prend que le premier canal si stereo
		int channels = format.getChannels();
		double sum = 0.0;
		
		for (int ch = 0; ch < channels; ch++)
		{
			int channelOffset = ch * bytesPerSample;
			long sampleCh = 0;
			if (bigEndian)
			{
				for (int b = 0; b < bytesPerSample; b++)
				sampleCh = (sampleCh << 8) | (frame[channelOffset + b] & 0xFF);
			}
			else
			{
				for (int b = bytesPerSample - 1; b >= 0; b--)
				sampleCh = (sampleCh << 8) | (frame[channelOffset + b] & 0xFF);
			}
			long signBit = 1L << (sampleSizeInBits - 1);
			long mask = (1L << sampleSizeInBits) - 1;
			
			if (encoding.equals(AudioFormat.Encoding.PCM_SIGNED))
			{
				if ((sampleCh & signBit) != 0) sampleCh -= mask + 1;
			}
			else if (encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED))
			{
				sampleCh -= (1L << (sampleSizeInBits - 1));
			}
			sum += sampleCh;
		}
		double sample = sum / channels; // moyenne
		double max = (double)(1L << (sampleSizeInBits - 1));
		return sample / max;
	}
}