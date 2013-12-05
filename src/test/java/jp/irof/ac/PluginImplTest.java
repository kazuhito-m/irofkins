package jp.irof.ac;

import net.sf.json.JSONObject;

import org.junit.Test;

public class PluginImplTest {

	@Test
	public void testStart001() throws Exception {
		// 準備
		PluginImpl sut = new PluginImpl();
		sut.configure(null, new JSONObject());
		// 実行
//		sut.start();
		// 検証
		// 通過した(動いた)からよし！
	}

}
