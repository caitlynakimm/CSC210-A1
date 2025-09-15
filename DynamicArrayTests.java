import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
        compareToString(a1.append(s),"abcdefs");
        compareToString(s.append(a1),"sabcdef");
        compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

    /**
     * Tests that extracting part of an array
     * results in a new array with just the extracted part
     */
    @Test
    public void testExtractStandard() {
        DynamicArray<Character> result1 = a1.extract(1, 4);
        compareToString(result1, "bcd");
        compareSize(result1, "bcd");

        DynamicArray<Character> result2 = a2.extract(0, 2);
        compareToString(result2, "wx");
        compareSize(result2, "wx");
    }

    /**
     * Tests that extracting the entire array
     * results in a new array with all the elements of the previous array
     */
    @Test
    public void testExtractEntire() {
        DynamicArray<Character> result1 = a1.extract(0, 6);
        compareToString(result1, "abcdef");
        compareSize(result1, "abcdef");
        
        DynamicArray<Character> result2 = a2.extract(0, 4);
        compareToString(result2, "wxyz");
        compareSize(result2, "wxyz");
    }

    /**
     * Tests that extracting no elements from an array
     * results in a new array that is empty
     */
    @Test
    public void testExtractZero() {
        DynamicArray<Character> result1 = a1.extract(2, 2);
        compareToString(result1, "");
        compareSize(result1, "");
        
        DynamicArray<Character> result2 = a2.extract(3, 3);
        compareToString(result2, "");
        compareSize(result2, "");
    }

    /**
     * Tests that extracting from an empty array
     * results in a new array that is empty
     */
    @Test
    public void testExtractEmpty() {
        DynamicArray<Character> result = empty.extract(0, 0);
        compareToString(result, "");
        compareSize(result, "");
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractNegLowIndex() {
        // low index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(-1, 5);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractHighIndexGreaterThanLength() {
        // high index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(2, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractLowIndexGreaterThanLength() {
        // low index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(6, 7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractNegHighIndex() {
        // high index is negative => throws ArrayIndexOutOfBoundsException
        a2.extract(1, -2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractHighIndexLessThanLow() {
        // high index is less than low
        a2.extract(2, 1);
    }

    // ~*~*~*~*~ Get Tests Below ~*~*~*~*~
    /**
     * Tests that getting an element at a specified index
     * results in that element being returned
     */
    @Test
    public void testGetStandard() {
        assertEquals("a", a1.get(0));
        assertEquals("d", a1.get(3));
        assertEquals("f", a1.get(5));

        assertEquals("w", a2.get(0));
        assertEquals("z", a2.get(3));

        assertEquals("s", s.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyArray() {
        empty.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegIndex() {
        // index is negative => throws ArrayIndexOutOfBoundsException
        a1.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexEqualtoArraySize() {
        // index is equal to array length => throws ArrayIndexOutOfBoundsException
        a1.get(a1.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanArraySize() {
        // index is greater than array length => throws ArrayIndexOutOfBoundsException
        a2.get(a2.size() + 1);
    }
    // ~*~*~*~*~ Add Tests Below ~*~*~*~*~
    /**
     * Tests that adding an element at a specified index
     * results in the new element inserted into the current array 
     */
    @Test
    public void testAddStandard() {
        assertEquals("oabcdef", a1.add(0, 'o'));
        assertEquals("abocdef", a1.add(2, 'o'));
        assertEquals("abcdeof", a1.add(5, 'o'));

        assertEquals("owxyz", a2.add(0, 'o'));
        assertEquals("wxyoz", a2.add(3, 'o'));

        assertEquals("o", s.add(0, 'o'));
    }

    public void testAddOverload() {

    }

}





