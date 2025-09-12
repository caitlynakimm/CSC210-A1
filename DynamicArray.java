public class DynamicArray<T> implements DynamicArrayADT {
    
    private T[] array;

    private int index;

    /** Constructor takes in an array */
    public DynamicArray(T[] array) {
        this.array = array;
        index = 0;
    }

    /**
     * Returns element at specified position in array
     * @param index The index of the returning element
     * @return Element at specified position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public T getElement(int index){
        return(array[index]);
    }

    /**
     * Replaces element at specified position with user-given element
     * @param index The index of the element to replace
     * @param newElement New element to be stored at specified position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public void setElement(int index, T newElement){
        array[index] = newElement;
    }

    /**
     * Returns number of elements in array
     * @return Number of elements in array
     * @throws IllegalStateException if array hasn't been correctly initialized
     */
    public int arraySize(){
        return array.length;
    }
}
