package org.jenkinsci.plugins.irof;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

import java.util.regex.Pattern;

/**
 * Jenkinsジョブを「実行するか否か」の判定管。
 * @author Miura Kazuhito
 */
public class JudgeOfJobExecute {

	/**
	 * 正規表現で指定された文字列に合致するか判定する。
	 * @param regex 検査に使う正規表現文字列。
	 * @param target 検査対象の文字列。
	 * @return 結果。合致したならtrue。
	 */
	public static boolean matcheOfNoCase(String regex, String target) {
		Pattern p = compile(regex, CASE_INSENSITIVE);
		return p.matcher(target).find();
	}
}
