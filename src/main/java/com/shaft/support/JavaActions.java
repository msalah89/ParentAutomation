package com.shaft.support;

import com.shaft.io.ReportManager;
import org.testng.Assert;

import java.util.Base64;

public class JavaActions {

    private JavaActions() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Takes two parameters, one is the array of special characters that are needed
     * to be replaced, and the text needed to be updated It converts text with
     * %40%23%24%25%26 ..etc (special characters) to return it with
     * %5C%5C%40%5C%5C%23%5C%5C%24%5C%5C%25%5C%5C%26
     *
     * @param specialCharactersArray
     * @param text
     * @return updated texts
     */
    public static String replaceRegex(String[] specialCharactersArray, String text) {
        // @#$%&
        // \\@\\#\\$\\%\\&

        String oldChar;
        for (int i = 0; i < (specialCharactersArray.length); i++) {
            oldChar = specialCharactersArray[i];
            specialCharactersArray[i] = ("\\" + specialCharactersArray[i]);
            text = text.replace(oldChar, specialCharactersArray[i]);
        }
        return text;
    }

    /**
     * Returns text after replaces its regular expressions which included in this
     * set []^$.|?*+(){}
     *
     * @param text
     * @return updated text after escaping its regular expressions
     */

    public static String replaceRegex(String text) {
        String[] specialCharactersArray = {"[", "]", "^", "$", ".", "|", "?", "*", "+", "(", ")", "{", "}"};
        return replaceRegex(specialCharactersArray, text);
    }

    public static String convertBase64(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    /**
     * Compares two objects (that can be cast to a string value) based on the
     * selected comparisonType and ValidationType, then returns the result in an
     * integer value
     *
     * @param expectedValue  the expected value (test data) of this assertion
     * @param actualValue    the actual value (calculated data) of this assertion
     * @param comparisonType 1 is literalComparison, 2 is regexComparison, 3 is
     *                       containsComparison, 4 is caseInsensitiveComparison
     * @param validationType either 'true' for a positive assertion that the objects
     *                       are equal, or 'false' for a negative assertion that the
     *                       objects are not equal
     * @return integer value; 1 in case of match, 0 in case of no match, -1 in case
     * of invalid comparison operator, -2 in case of another unhandled
     * exception
     */
    public static int compareTwoObjects(Object expectedValue, Object actualValue, int comparisonType,
                                        Boolean validationType) {
        if (validationType) {
            try {
                switch (comparisonType) {
                    case 1:
                        // case sensitive literal equivalence
                        Assert.assertTrue(actualValue.equals(expectedValue));
                        break;
                    case 2:
                        // regex comparison
                        Assert.assertTrue((String.valueOf(actualValue)).matches(String.valueOf(expectedValue)));
                        break;
                    case 3:
                        // contains
                        Assert.assertTrue((String.valueOf(actualValue)).contains(String.valueOf(expectedValue)));
                        break;
                    case 4:
                        // case insensitive equivalence
                        Assert.assertTrue((String.valueOf(actualValue)).equalsIgnoreCase(String.valueOf(expectedValue)));
                        break;
                    default:
                        // unhandled case
                        return -1;
                }
                return 1;
            } catch (AssertionError e) {
                return 0;
            } catch (Exception e) {
                ReportManager.log(e);
                return -2;
            }
        } else {
            try {
                switch (comparisonType) {
                    case 1:
                        // case sensitive literal equivalence
                        Assert.assertFalse(actualValue.equals(expectedValue));
                        break;
                    case 2:
                        // regex comparison
                        Assert.assertFalse((String.valueOf(actualValue)).matches(String.valueOf(expectedValue)));
                        break;
                    case 3:
                        // contains
                        Assert.assertFalse((String.valueOf(actualValue)).contains(String.valueOf(expectedValue)));
                        break;
                    case 4:
                        // case insensitive equivalence
                        Assert.assertFalse((String.valueOf(actualValue)).equalsIgnoreCase(String.valueOf(expectedValue)));
                        break;
                    default:
                        // unhandled case
                        return -1;
                }
                return 1;
            } catch (AssertionError e) {
                return 0;
            } catch (Exception e) {
                ReportManager.log(e);
                return -2;
            }
        }

    }
}