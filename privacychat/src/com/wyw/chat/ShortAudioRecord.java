package com.wyw.chat;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;


//短信模式，微信式语音通信，简短的语音录制，录制完成后发送给接收端
public class ShortAudioRecord {
	
	private Boolean isRecording = false;
	private File file = null;
	
	public ShortAudioRecord(){		
	}
	
	public void start(){
		//此处初始化音频对象的具体数值设置，需要看api研究
		int frequency = 11025;//频率
		int channelConfiguration = AudioFormat.CHANNEL_IN_DEFAULT;//频道
		int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;//编码格式：16bit比8bit质量好（但耗电量、内存、cpu大）
		int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration,  audioEncoding);
		AudioRecord audioRecord = new AudioRecord(AudioSource.MIC,
                frequency, channelConfiguration,
                audioEncoding, bufferSize);
		
		short[] buffer = new short[bufferSize];
		
		try {
			file = File.createTempFile("temp", "pcm");
			OutputStream os = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(os);
			
			audioRecord.startRecording();
			isRecording = true;
			while (isRecording) {
				int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
				for (int i = 0; i < bufferReadResult; i++){
		            dos.writeShort(buffer[i]);
		        }
			}

			audioRecord.stop();
			audioRecord.release();
			
			dos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void stop(){
		isRecording = false;
	}
}
