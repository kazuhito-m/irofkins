package jp.irof.ac;

import hudson.Extension;
import hudson.Plugin;
import hudson.util.PluginServletFilter;

/**
 * irofkinsプラグインの実装クラス。
 *
 * @author Kazuhito Miura
 */
@Extension
public class PluginImpl extends Plugin {

	/**
	 * 開始メソッド。<br>
	 * 主にフィルターの追加を責務とする。
	 */
    @Override
    public void start() throws Exception {
    	load();
    	PluginServletFilter.addFilter(new ImageRequestFilter());
    	super.start();
    }
}
