package xyz.majin.hadoop.RPC.cleint;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import xyz.majin.hadoop.RPC.server.LoginServiceInterface;

public class ClientStarter {
	public static void main(String[] args) throws Exception {
		LoginServiceInterface proxy = RPC.getProxy(LoginServiceInterface.class, 1L,
				new InetSocketAddress("192.168.242.201", 10000), new Configuration());

		String result = proxy.login("majin", "12345");
		
		System.out.println(result);
	}
}
