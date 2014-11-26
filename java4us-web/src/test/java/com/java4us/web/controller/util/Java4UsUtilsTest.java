package com.java4us.web.controller.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Java4UsUtilsTest {

	@Test
	public void shouldGeneratePassword() {
		assertNotNull(Java4UsUtils.generateRandomPassword());
		assertEquals(Long.valueOf(Java4UsUtils.generateRandomPassword().length()),
				Long.valueOf(8));
	}

}
