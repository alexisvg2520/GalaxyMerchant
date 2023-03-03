package com.galaxy.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.galaxy.model.RomanToArabicUtils;

public class RomanToArabicUtilsTest {
	private RomanToArabicUtils conversorRoman;

    @Before
    public void setUp() throws Exception {
        conversorRoman = new RomanToArabicUtils();
    }

    @Test
    public void testGetTranslateArabicRoman() {
        assertEquals("how many Credits is glob prok Gold ?", conversorRoman.getTranslateArabicRoman("how many Credits is glob prok Gold ?"));
        assertEquals("how much is pish tegj glob glob ?", conversorRoman.getTranslateArabicRoman("how much is pish tegj glob glob ?"));
    }
}
