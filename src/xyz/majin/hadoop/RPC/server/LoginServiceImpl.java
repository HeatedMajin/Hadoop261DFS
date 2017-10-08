package xyz.majin.hadoop.RPC.server;

public class LoginServiceImpl implements LoginServiceInterface{

	@Override
	public String login(String username, String password) {
		return username + " logged in ~~~~";
	}

}
