/**
 * Establishes operations for dealing with sequences of elements
 * @param <T> Type of elements in sequences
 */
public interface WholeArrayOperationsADT<T> {

    /**
     * 
     */
    public DynamicArray<T> append(DynamicArray<T> other);

    /**
     * 
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> other);

    /**
     * 
     */
    public DynamicArray<T> splitSuffix(int index);

    public DynamicArray<T> splitPrefix(int index);

    public DynamicArray<T> delete(int fromIndex, int toIndex);

    public DynamicArray<T> extract(int fromIndex, int toIndex);
}