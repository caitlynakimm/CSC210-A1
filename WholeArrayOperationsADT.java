/**
 * Interface for operations that deal with entire arrays
 * @param <T> Type of elements in arrays
 */
public interface WholeArrayOperationsADT<T> {

    /**
     * Adds another array to end of this array
     * @param other Array to add to end
     * @return New array with both arrays combined
     */
    public DynamicArray<T> append(DynamicArray<T> other);

    /**
     * Puts another array into this array at a specified index
     * @param index Where to put other array
     * @param other Array to insert
     * @return New array with inserted elements
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> other);

    /**
     * Gets part of array from an index to the end
     * @param index Starting position
     * @return New array with elements from index to end
     */
    public DynamicArray<T> splitSuffix(int index);

    /**
     * Gets part of array from start to before index
     * @param index Ending position (not included)
     * @return New array with elements from start to before index
     */
    public DynamicArray<T> splitPrefix(int index);

    /**
     * Removes element(s) between two indices
     * @param fromIndex Start position (included)
     * @param toIndex End position (not included)
     * @return New array with specified elements removed
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex);

    /**
     * Gets elements between two indices
     * @param fromIndex Start position (included)
     * @param toIndex End position (not included)
     * @return New array with only specified elements
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex);
}