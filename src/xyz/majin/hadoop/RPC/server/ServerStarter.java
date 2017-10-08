package xyz.majin.hadoop.RPC.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class ServerStarter {
	public static void main(String[] args) throws Exception {
		//1¡¢
		Builder builder = new RPC.Builder(new Configuration());

		builder.setBindAddress("192.168.242.201")
			.setPort(10000)
			.setProtocol(LoginServiceInterface.class)
			.setInstance(new LoginServiceImpl());
		
		//2¡¢
		Server server = builder.build();

		//3¡¢
		server.start();
	}
}
