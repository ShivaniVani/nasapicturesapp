package com.example.test

import com.example.test.utils.Utils
import org.junit.Test
import org.junit.Assert.*

class DateParseTest {

    /* Check Date With Expected Format  */
    @Test
    fun `Date Parse Expected Format Should True`(){
        assertTrue(Utils.isDateParse("23 Jun 2012"))
    }

    /* Check Date Parse With Different Format */
    @Test
    fun `Date Parse UnExpected Format Should False`(){
        assertFalse(Utils.isDateParse("2019-02-24 04:04:17"))
    }

    /* Check Date Parse With Empty Format */
    @Test
    fun `Date Parse Empty Should False`(){
        assertFalse(Utils.isDateParse(""))
    }


}