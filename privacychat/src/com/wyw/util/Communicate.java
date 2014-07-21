/**
 * 
 */
package com.wyw.util;

import android.R.string;
import android.net.wifi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangyiwen02
 * 
 */
public class Communicate {

	ServerSocket sever;
	static Socket client;

	public Communicate(String site, int port) {
		try {
			sever = new ServerSocket(port);
			client = new Socket(site, port);
			System.out.println("Client is created! site:" + site + " port:"
					+ port);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beginListen() {
		while (true) {
			try {
				final Socket socket = sever.accept();

				new Thread(new Runnable() {
					public void run() {
						BufferedReader in;
						try {
							in = new BufferedReader(new InputStreamReader(
									socket.getInputStream(), "UTF-8"));
							PrintWriter out = new PrintWriter(
									socket.getOutputStream());
							while (!socket.isClosed()) {
								String str;
								str = in.readLine();
								out.println("Hello!world!! " + str);
								out.flush();
								if (str == null || str.equals("end"))
									break;
								System.out.println(str);
							}
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String sendMsg(String msg) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println(msg);
			out.flush();
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void closeSocket() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Communicate server = new Communicate("127.0.0.1", 12345);
		server.beginListen();

		Communicate client = new Communicate("127.0.0.1", 12345);
		System.out.println(client.sendMsg("nimei1"));
		client.closeSocket();

		Communicate client1 = new Communicate("127.0.0.1", 12345);
		System.out.println(client1.sendMsg("nimei1111"));
		client1.closeSocket();


	}

}
