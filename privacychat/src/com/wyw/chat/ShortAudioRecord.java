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


//����ģʽ��΢��ʽ����ͨ�ţ���̵�����¼�ƣ�¼����ɺ��͸����ն�
public class ShortAudioRecord {
	
	private Boolean isRecording = false;
	private File file = null;
	
	public ShortAudioRecord(){		
	}
	
	public void start(){
		//�˴���ʼ����Ƶ����ľ�����ֵ���ã���Ҫ��api�о�
		int frequency = 11025;//Ƶ��
		int channelConfiguration = AudioFormat.CHANNEL_IN_DEFAULT;//Ƶ��
		int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;//�����ʽ��16bit��8bit�����ã����ĵ������ڴ桢cpu��
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
