/**
 * Implementation of a dynamic array that can increase or decrease in size
 * Allows basic array and whole-array operations
 * @param <T> Type of elements stored in array
 */
public class DynamicArray<T> implements DynamicArrayADT<T>, WholeArrayOperationsADT<T> {
    
    private T[] array;
    private int size; //number of elements stored in array
    private int capacity; //total allocated memory for array 

    /**
     * Constructor makes a new DynamicArray with specified initial capacity
     * @param length Initial capacity of array
     * @throws IllegalArgumentException if length is negative
     */
    //@SuppressWarnings("unchecked")
    public DynamicArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Allocated space for array cannot be negative.");
        }
        this.capacity = length;
        this.array = allocate(length);
        this.size = 0; //no elements initially
    }

    /**
     * Creates deep copy of another DynamicArray
     * @param other DynamicArray to copy
     */
    //@SuppressWarnings("unchecked")
    public DynamicArray(DynamicArray<T> other) {
        this.capacity = other.capacity;
        this.array = allocate(capacity);
        this.size = other.size;

        for (int i = 0; i < size; i++) {
            this.array[i] = other.array[i];
        }
    }

    /**
     * Replaces element at specified position with user-given element
     * @param index The index of the element to replace
     * @param newElement New element to be stored at specified position
     * @return oldValue Previous value stored at given index of array
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T set(int index, T newElement){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. It is either negative or equal to/greater than the array's size. Please enter a valid index.");
        }
        T oldValue = array[index];
        array[index] = newElement;
        return oldValue;
    }

    /**
     * Returns element at specified position in array
     * @param index The index of the returning element
     * @return Element at specified position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. It is either negative or equal to/greater than the array's size. Please enter a valid index.");
        }
        return(array[index]);
    }

    /**
     * Returns number of elements in array
     * @return Number of elements in array
     * @throws IllegalStateException if array hasn't been correctly initialized
     */
    public int size(){
        return size;
    }

    /**
     * Private utility to do array allocation of specified length
     * @param len Length of array to allocate
     * @return a new array type T[] with given length
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    /**
     * Inserts specified element at specified index
     * @param index Index to insert element at
     * @param value Element to be inserted
     * @throws IndexOutOfBoundsException if index out of range
     */
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        T[] newArray = allocate(size + 1);

        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        newArray[index] = value;

        for (int i = index; i < size; i++) {
            newArray[i+1] = array[i];
        }
        
        this.array = newArray;
        size++;
    }

    /**
     * Appends specified element to end of array
     *  @param value Element to be appended
     */
    public void add(T value) {
        add(size, value);
    }

    /**
     * Removes and returns element at specified index
     * @param index Index of element to remove
     * @return removedElement Element previously at specified index
     * @throws IndexOutOfBoundsException if index out of range
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        T removedElement = array[index];

        //T[] newArray = allocate(size - 1);
        
        //shifts elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }


        array[size - 1] = null; //clear last element in array
        size--; //array has one less element

        return removedElement;
    }

    
    /**
     * Appends all elements from another DynamicArray to this array
     * @param other DynamicArray containing elements to be appended
     * @return result New DynamicArray with all elements from both arrays
     */
    public DynamicArray<T> append(DynamicArray<T> other) {
        DynamicArray<T> result = new DynamicArray<>(this.size + other.size);
        
        //copy elements from current array
        for (int i = 0; i < this.size; i++) {
            result.add(this.get(i));
        }

        //copy elements from passed array
        for (int i = 0; i < other.size; i++) {
            result.add(other.get(i));
        }

        return result;
    }

    /**
     * Inserts all elements from another DynamicArray at specified index
     * @param index Index where the other array is inserted
     * @param other DynamicArray with elements to be inserted
     * @return result New DynamicArray with combined elements
     * @throws IndexOutOfBoundsException if index out of range
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> other) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        DynamicArray<T> result = new DynamicArray<>(this.size + other.size);
        
        //copy elements before insertion point
        for (int i = 0; i < index; i++) {
            result.add(this.get(i));
        }
        
        //copy elements of passed array
        for (int i = 0; i < other.size; i++) {
            result.add(other.get(i));
        }

        //copy elements after insertion point, first getting element at the index (of the current array) we previously stopped at
        for (int i = index; i < this.size; i++) {
            result.add(this.get(i));
        }

        return result;
    }

    /**
     * Creates new array containing elements from specified index to end
     * @param index Starting index for suffix (included)
     * @return result New DynamicArray that has the suffix elements
     * @throws IndexOutOfBoundsException if index out of range
     */
    public DynamicArray<T> splitSuffix(int index){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        DynamicArray<T> result = new DynamicArray<>(this.size - index);

        for (int i = index; i < this.size; i++) {
            result.add(this.get(i));
        }

        return result;

    }

    /**
     * Creates new array containing elements from start to specified index
     * @param index Ending index for prefix (not included)
     * @return result New DynamicArray that has the prefix elements
     * @throws IndexOutOfBoundsException if index out of range
     */
    public DynamicArray<T> splitPrefix(int index){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        DynamicArray<T> result = new DynamicArray<>(index);

        for (int i = 0; i < index; i++) {
            result.add(this.get(i));
        }

        return result;
    }

    /**
     * Creates new array with elements in specified range removed
     * @param fromIndex Start index of range of elements to be removed (included)
     * @param toIndex End index of range of elements to be removed (not included)
     * @return result New DynamicArray with range removed
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex){

        if (fromIndex < 0 || fromIndex > this.size || toIndex > this.size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Indices chosen are out of bounds. Please enter valid indices.");
        }

        DynamicArray<T> result = new DynamicArray<>(this.size - (toIndex - fromIndex));

        for (int i = 0; i < fromIndex; i++) {
            result.add(this.get(i));
        }

        for (int i = toIndex; i < this.size; i++) {
            result.add(this.get(i));
        }

        return result;
    }

    /**
     * Creates new array with elements in specified range
     * @param fromIndex Start index of extracted range (included)
     * @param toIndex End index of extracted range (not included)
     * @return result New DynamicArray with extracted elements
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex){

        if (fromIndex < 0 || fromIndex > this.size || toIndex > this.size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Indices chosen are out of bounds. Please enter valid indices.");
        }

        DynamicArray<T> result = new DynamicArray<>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            result.add(this.get(i));
        }

        return result;
    }
}

