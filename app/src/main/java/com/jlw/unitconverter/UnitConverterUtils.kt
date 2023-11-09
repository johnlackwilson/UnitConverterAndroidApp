package com.jlw.unitconverter

import android.util.Log


/**
 * Class that provides utility methods for unit conversion.
 */
class UnitConverterUtils {
    /**
     * Counts the occurrences of a given character.
     *
     * @param str the string to check
     * @param char the char to check for number of occurrences.
     * @return number of occurrences of the character.
     */
    fun countCharacterOccurrencesInString(str: String, char: Char): Int {
        var count = 0
        for (i in str.indices) {
            if (str[i] == char) {
                count++
            }
        }
        return count
    }

    /**
     * Returns whether the input text is permitted or not.
     *
     * Permitted input text includes:
     *  - Digits only (i.e. a string that can convert to a double)
     *  - A single decimal point anywhere in the string
     *
     * @param userInputText the input text from user
     * @return whether the text is permitted or not.
     */
    fun isInputTextPermitted(userInputText: String): Boolean {
        Log.v(TAG, "isInputTextPermitted($userInputText)")

        // Make sure input is a number
        if (userInputText.toDoubleOrNull() == null) {
            Log.d(TAG, "$userInputText: not permitted")
            return false
        }

        // Make sure that we only permit a single decimal point
        if (userInputText.last() == '.' && userInputText.first() != '.' &&
            countCharacterOccurrencesInString(userInputText, '.') > 1) {
            Log.d(TAG, "$userInputText: not permitted")
            return false
        }

        Log.d(TAG, "$userInputText: is permitted")
        return true
    }

    /**
     * Returns a string without trailing decimals points and zeros so that
     * the output string looks cleaner.
     *
     * @param input the user input
     * @return string that has been stripped of unwanted characters
     */
    fun getCleanOutput(input: Double?): String {
        Log.v(TAG, "getCleanOutput($input)")

        if (input == null || input == 0.0) {
            return ""
        }

        return if (input.toString().contains('.')) {
            // Strip trailing .0's
            input.toString().dropLastWhile { it == '0' }.dropLastWhile { it == '.' }
        } else {
            input.toString()
        }
    }
}