package com.java4us.web.controller.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Java4UsUtilsTest {

	@Test
	public void shouldGenerastePassword() {
		assertNotNull(Java4UsUtils.randomString(8));
		assertEquals(Long.valueOf(Java4UsUtils.randomString(8).length()),
				Long.valueOf(8));
	}

}
