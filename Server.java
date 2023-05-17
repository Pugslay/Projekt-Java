package karta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Server extends Thread{
	private static Socket clientSocket1 = null;
    private static Socket clientSocket2 = null;
    private static ServerSocket serverSocket=null;
    private static Server threadOdczyt1=null;
	private static Server threadOdczyt2=null;
	private static Server threadServer=null;
	private static List<String> player1;
	private static List<String> player2;
	private static int ID;
	public Server() {
		ID=0;
		player1=new ArrayList<>();
		player2=new ArrayList<>();
		Collections.synchronizedList(player1);
		Collections.synchronizedList(player2);
		try {
			serverSocket = new ServerSocket(6868);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		threadServer=new Server();
		threadServer.start();
	}
	@Override
	public void run() {
		if(ID==0) {
			ID=1;
			do{
				if(clientSocket1==null) {
					try {
						clientSocket1 = serverSocket.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(clientSocket2==null) {
					try {
						clientSocket2 = serverSocket.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(threadOdczyt1==null) {
					threadOdczyt1=new Server();
					threadOdczyt1.start();
				}
				
			}while(true);
		}
		if(ID==1) {
			if(threadOdczyt2==null) {
				ID=2;
				threadOdczyt2=new Server();
				threadOdczyt2.start();
			}
			do {
				InputStream input = null;
				String str=null;
				if(clientSocket1!=null) {
					do {
						try {
							input = clientSocket1.getInputStream();
						} catch (IOException e) {
							e.printStackTrace();
						}
						BufferedReader reader = new BufferedReader(new InputStreamReader(input));
						try {
							str=reader.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(str!="EXIT"||str!=null) {
							player1.add(str);
						}
						str=null;
					}while(true);
				}
			}while(true);
		}
		if(ID==2) {
			do {
				InputStream input = null;
				String str=null;
				if(clientSocket2!=null) {
					do {
						try {
							input = clientSocket2.getInputStream();
						} catch (IOException e) {
							e.printStackTrace();
						}
						BufferedReader reader = new BufferedReader(new InputStreamReader(input));
						try {
							str=reader.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(str!=null) {
							player2.add(str);
						}
						str=null;
					}while(true);
				}
			}while(true);
		}
	}
	List<String> pobierzBufor(boolean player){
		if(player) {
			return player1;
		}else {
			return player2;
		}
	}
	void wyslijBufor(List<String> str,boolean player) {
		if(player) {
			OutputStream output = null;
			try {
				output = clientSocket1.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(output, true);
			for(int i=0;i<str.size();i++) {
				writer.println(str.get(ID));
			}
			writer.println("EXIT");
		}else {
			OutputStream output = null;
			try {
				output = clientSocket2.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(output, true);
			for(int i=0;i<str.size();i++) {
				writer.println(str.get(ID));
			}
			writer.println("EXIT");
		}
	}
}
