package org.jenkinsci.plugins.irof;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ImageRequestFilterTest {

	/** テスト対象 */
	private ImageRequestFilter sut;
	
	@Before
	public void setUp() {
		sut = new ImageRequestFilter();
	}
	
	@Test
	public void とりあえずはインスタンスが出来るか() {
		assertThat(sut, is(notNullValue()));
	}

}
