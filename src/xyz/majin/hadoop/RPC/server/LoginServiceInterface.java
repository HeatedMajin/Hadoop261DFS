package xyz.majin.hadoop.RPC.server;

public interface LoginServiceInterface {
	public static final long versionID = 1L;
	public abstract String login(String username,String password); 
}
