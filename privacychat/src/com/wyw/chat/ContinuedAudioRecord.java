package com.wyw.chat;

import java.net.Socket;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;

//�绰ģʽ����������������
public class ContinuedAudioRecord {
	
	private Boolean isRecording = false;
	
	//��Ҫ�������Լ���socket�����ڴ˴�������
	private Socket tcpSocket = null;
	
	public ContinuedAudioRecord(Socket mySocket){
		
		tcpSocket = mySocket;
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
		
		audioRecord.startRecording();
		isRecording = true;
		while (isRecording) {
			int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
			
			//������socket����bufferReadResult��С��buffer
			//�ڽ��ն������Ƶ����
			//�Ƿ���Ҫ�ŵ��߳�����ʵ�ʲ���
			//...
			
		}
		
		audioRecord.stop();
		audioRecord.release();
	}
	
	
	public void stop(){
		isRecording = false;
	}
}
