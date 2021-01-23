package Model.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Model.Server.Network.CacheManager;
import Model.Server.Network.ClientHandler;
import Model.Server.Network.ClientHandlerPath;
import Model.Server.Network.FileCacheManager;
import Model.Server.Network.MyClientHandler;
import Model.Server.Network.Server;

public class MySerialServer implements Server {
	
	private int port;
	private static ClientHandler c;
	private volatile boolean stop;
	
	public MySerialServer() {
		this.stop = false;
	}
	@SuppressWarnings("static-access")
	@Override
	public void open(int port,ClientHandler c) {
		this.port=port;
		this.c=c;
		new Thread(()->{
			try {
				runServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	@Override
	public void stop() {
		stop=true;
	}
	public  void runServer()throws Exception {
		ServerSocket server=new ServerSocket(port);
		if(port==5000)
			System.out.println("Solver Server is alive on port: "+port+" !");
		else
			System.out.println("FlightGear Server is alive on port: "+port+" !");
		server.setSoTimeout(300000000);
		while(!stop){
			try{
				Socket aClient=server.accept(); // blocking call
				if(port==5000)
					System.out.println("You are connected to the solver server!");
				else
					System.out.println("FlightGear is connected to the FlightGear Server !");
				
				try {
					c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					if(port==5000)
						System.out.println(aClient.getOutputStream().toString());
					aClient.close();
		} catch (IOException e) {
			System.out.println("invalid input2-output");
			e.printStackTrace();
		}
		}catch(SocketTimeoutException e) {
			System.out.println("Time Out");
			e.printStackTrace();
		}
		}
		server.close();
	}
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
	     Server s=new MySerialServer(); 
	     CacheManager cm=new FileCacheManager();
	     MyClientHandler ch=new MyClientHandler(cm);
	     s.open(5000,new ClientHandlerPath(ch));
	}
}
