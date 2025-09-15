/**
 * Establishes operations for dealing with sequences of elements
 * @param <T> Type of elements in sequences
 */
public interface WholeArrayOperationsADT<T> {

    /**
     * 
     * @param other
     * @return
     */
    public DynamicArray<T> append(DynamicArray<T> other);

    /**
     * 
     * @param index
     * @param other
     * @return
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> other);

    /**
     * 
     * @param index
     * @return
     */
    public DynamicArray<T> splitSuffix(int index);

    /**
     * 
     * @param index
     * @return
     */
    public DynamicArray<T> splitPrefix(int index);

    /**
     * 
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex);

    /**
     * 
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex);
}