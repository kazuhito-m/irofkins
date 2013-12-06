package org.jenkinsci.plugins.irof;

import static org.hamcrest.CoreMatchers.is;
import static org.jenkinsci.plugins.irof.JudgeOfJobExecute.matcheOfNoCase;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * JudgeOfJobExecuteのテスト。
 * @author Miura Kazuhito
 */
public class JudgeOfJobExecuteTest {

	@Test
	public void 正規表現検査は値を返し大文字小文字も無視して検索するか() {
		// arenge
		String target = "いtestとbuildとろテストとビルドとぶり大根ふ";
		// act / assert
		assertThat(matcheOfNoCase(".*test.*", target), is(true));
		assertThat(matcheOfNoCase(".*build.*", target), is(true));
		assertThat(matcheOfNoCase(".*テスト.*", target), is(true));
		assertThat(matcheOfNoCase(".*ビルド.*", target), is(true));
		assertThat(matcheOfNoCase(".*ブリ.*", target), is(false));

		// arrange
		String regex = ".*(test|build|テスト|ビルド|い.*ろ.*ふ).*";
		// act / assert
		assertThat(matcheOfNoCase(regex, "abctestdef"), is(true));
		assertThat(matcheOfNoCase(regex, "abcTeStdef"), is(true));
		assertThat(matcheOfNoCase(regex, "あBUILDい"), is(true));
		assertThat(matcheOfNoCase(regex, "んビルドZ"), is(true));
		assertThat(matcheOfNoCase(regex, "1テスト9"), is(true));
		assertThat(matcheOfNoCase(regex, "いちげきひっさつろんよりしょうこふんじんけん"), is(true));

	}

}
