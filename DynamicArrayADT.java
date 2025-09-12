/**
 * Interface provides array-like functionality using common index-based access operations
 * Implementations of this interface should include operations safe for creating, accessing, and changing array elements of all data types
 * Implementations should also use a constructor to initialize array
 * @param <T> Type of elements stored in array
 */
public interface DynamicArrayADT<T> {

    /**
     * Returns element at specified position in array
     * @param index The index of the returning element
     * @return Element at specified position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public T getElement(int index);

    /**
     * Replaces element at specified position with user-given element
     * @param index The index of the element to replace
     * @param newElement New element to be stored at specified position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public void setElement(int index, T newElement);

    /**
     * Returns number of elements in array
     * @return Number of elements in array
     * @throws IllegalStateException if array hasn't been correctly initialized
     */
    public int arraySize();

}


// Unit Tests
/** Test 1
 * Create an integer array with length 5 using createArray(5)
 * Assign integers to each index using setElement (values: 1, 2, 3, 4, 5)
 * AssertEqual - check if getElement(2) returns 3
 * AssertEqual - check if arraySize() returns 5
 * Test passes if both assertions are true
 */

/** Test 2
 * Create a character array with length 7 using createArray(7)
 * Assign chaaracters to each index using setElement (values: 'C', 'a', 'i', 't', 'l', 'y', 'n')
 * AssertEqual - check if getElement(4) returns 'l'
 * AssertEqual - check if arraySize() returns 7
 * Test passes if both assertions are true
 */

/** Test 3
 * Create an integer array with length 5 using createArray(5)
 * Assign integers to each index using setElement (values: 1, 2, 3, 4, 5)
 * Try to call getElement(-2) and check if IndexOutOfBoundsException is thrown
 * Try to call setElement(10, 35) and check if IndexOutOfBoundsException is thrown
 * Test passes if both operations throw expected exceptions
 */

/** Test 4
 * Try to create an array with negative size using createArray(-1)
 * Check if IllegalArgumentException is thrown
 * Test passes if exception is thrown
 */

/** Test 5
 * Try to call arraySize() on an uninitialized array (ex. array called boolArr) before calling createArray()
 * Check if IllegalStateException is thrown
 * Test passes if exception is thrown
 */