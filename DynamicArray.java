public class DynamicArray<T> implements DynamicArrayADT<T> {
    
    private T[] array;

    private int index;

    /** Constructor takes in an array */
    public DynamicArray(T[] array, int length) {
        this.array = array;
        this.index = 0;
    }

    /**
     * Replaces element at specified position with user-given element
     * @param index The index of the element to replace
     * @param newElement New element to be stored at specified position
     * @return prev_var Previous value stored at given index of array
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public T set(int index, T newElement){
        T prev_val = array[index];
        array[index] = newElement;
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. It is either negative or equal to or greater than the array's length. Please enter a valid index.");
        }
        return prev_val;
    }

    /**
     * Returns element at specified position in array
     * @param index The index of the returning element
     * @return Element at specified position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
     */
    public T get(int index){
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. It is either negative or equal to or greater than the array's length. Please enter a valid index.");
        }
        return(array[index]);
    }

    /**
     * Returns number of elements in array
     * @return Number of elements in array
     * @throws IllegalStateException if array hasn't been correctly initialized
     */
    public int size(){
        return array.length;
    }

    /**
     * Private utility to do array allocation
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    public void add(int index, T value) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        T[] newArray = allocate(array.length + 1);

        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        newArray[index] = value;

        for (int i = index; i < array.length; i++) {
            newArray[i+1] = array[i];
        }
        
        this.array = newArray;
    }

    public void add(T value) {
        T[] newArray = allocate(array.length + 1);
        newArray[newArray.length - 1] = value;
        this.array = newArray;
    }

    public T remove(int index) {
        T removed_element = array[index];
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        T[] newArray = allocate(array.length - 1);
        
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        for (int i = index; i < newArray.length; i++) {
            newArray[i] = array[i+1];
        }

        this.array = newArray;

        return removed_element;
    }


}
