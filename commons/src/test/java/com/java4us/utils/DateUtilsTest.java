/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.utils;

import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.domain.builder.utils.TestDateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author turgay
 */
public class DateUtilsTest {
    	
    @Test
    public void shouldReturnLongFormattedDate(){
        Clock.freeze();
        Clock.setTime(TestDateUtils.toDateTimeWithSeconds("2014-04-22 22:16:03"));
        assertThat(DateUtils.toLongFormattedDate("2014-04-22 22:16:03"), equalTo(Clock.getTime()));
        Clock.unfreeze();
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void shouldReturnLongFormattedDateWith() throws ParseException{
    	Clock.freeze();
    	Date dt = new Date("Fri, 24 Oct 2014 16:00:04 +0000");    	
    	Clock.setTime(dt);		
    	assertThat(DateUtils.toLongFormatDate(dt), equalTo(dt));
        Clock.unfreeze();    	
    }
    
}
