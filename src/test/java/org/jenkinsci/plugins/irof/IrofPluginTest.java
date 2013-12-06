package org.jenkinsci.plugins.irof;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.auth.AccessToken;

public class IrofPluginTest {

	private IrofPlugin sut;

	@Before
	public void startUp() {
		sut = new IrofPlugin();
		// TwitterのアプリケーションIDを指定。
		sut.consumerKey = "mxsGonBL83TVN11yjKS9Q";
		sut.consumerSecret = "WrqFsVdaGkT89VmLT2xvFSrQNyE0lq2CuSjyxiEI7c";
		sut.accessToken = "84877685-MNxuLHogaC6t8Xz8JcWrCEq8oX3qNpGP6yaCB3uzF";
		sut.accessTokenSecret = "4beq7yAfXeeTwSAhNYBjHdmctecnRDhfhxULKT0jbVb8R";
	}

	@Ignore
	@Test
	public void test() {
		// act
		sut.startStreaming();
		// after
		sut.stop();
	}
}
