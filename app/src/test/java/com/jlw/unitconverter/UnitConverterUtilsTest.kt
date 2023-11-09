package com.jlw.unitconverter

import org.junit.Test

import org.junit.Assert.*

class UnitConverterUtilsTest {

    private val unitConverterUtils: UnitConverterUtils = UnitConverterUtils()

    @Test
    fun countCharacterOccurrencesInString_singleCharacter_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("s", 's'), 1)
    }

    @Test
    fun countCharacterOccurrencesInString_multipleCharacters_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("sssss", 's'), 5)
    }

    @Test
    fun countCharacterOccurrencesInString_multipleCharactersWithinVariedString_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("sasbhslklaskjjrys", 's'), 5)
    }

    @Test
    fun countCharacterOccurrencesInString_multipleCharactersWithinVariedStringContainingNumbers_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("sasbhslklaskjjr17172649jxnmjjjs", 's'), 5)
    }

    @Test
    fun countCharacterOccurrencesInString_emptyString_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("", 's'), 0)
    }

    @Test
    fun countCharacterOccurrencesInString_noCharPresentInString_correct() {
        assertEquals(unitConverterUtils.countCharacterOccurrencesInString("nocharhere", 's'), 0)
    }

    @Test
    fun isInputTextPermitted_cantConvertToDouble_notcorrect() {
        assertFalse(unitConverterUtils.isInputTextPermitted("hello"))
    }

    @Test
    fun isInputTextPermitted_cantConvertToDoubleWithAlphaNumerics_notcorrect() {
        assertFalse(unitConverterUtils.isInputTextPermitted("hello123"))
    }

    @Test
    fun isInputTextPermitted_withTwoDecimals_notcorrect() {
        assertFalse(unitConverterUtils.isInputTextPermitted("0..0"))
    }

    @Test
    fun isInputTextPermitted_withSingleLeadingDecimal_notcorrect() {
        assertFalse(unitConverterUtils.isInputTextPermitted("."))
    }

    @Test
    fun isInputTextPermitted_withInteger_correct() {
        assertTrue(unitConverterUtils.isInputTextPermitted("1"))
    }

    @Test
    fun isInputTextPermitted_withLargeInteger_correct() {
        assertTrue(unitConverterUtils.isInputTextPermitted("10000991827364901"))
    }

    @Test
    fun isInputTextPermitted_withDouble_correct() {
        assertTrue(unitConverterUtils.isInputTextPermitted("1.0"))
    }

    @Test
    fun isInputTextPermitted_withLargeDouble_correct() {
        assertTrue(unitConverterUtils.isInputTextPermitted("1111111111111.000000000001"))
    }

    @Test
    fun getOutputStringFromInput_withZeroInput_correct() {
        val output = unitConverterUtils.getCleanOutput(0.0)
        assertEquals(output, "")
    }

    @Test
    fun getOutputStringFromInput_withNullInput_correct() {
        val output = unitConverterUtils.getCleanOutput(null)
        assertEquals(output, "")
    }

    @Test
    fun getOutputStringFromInput_withDouble_correct() {
        val output = unitConverterUtils.getCleanOutput(1.0)
        assertEquals(output, "1")
    }

    @Test
    fun getOutputStringFromInput_withLargeDouble_correct() {
        val output = unitConverterUtils.getCleanOutput(123456.0)
        assertEquals(output, "123456")
    }

    @Test
    fun getOutputStringFromInput_withTrailingZero_correct() {
        val output = unitConverterUtils.getCleanOutput(1.1234567890)
        assertEquals(output, "1.123456789")
    }

    @Test
    fun getOutputStringFromInput_withLeadingZeroAfterDecimal_correct() {
        val output = unitConverterUtils.getCleanOutput(1.0123456789)
        assertEquals(output, "1.0123456789")
    }
}