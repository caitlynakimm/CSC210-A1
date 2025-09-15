/**
 * 
 */
public class DynamicArray<T> implements DynamicArrayADT<T>, WholeArrayOperationsADT<T> {
    
    private T[] array;
    private int size; //number of elements stored in array

    /**
     * Constructor allocates space for an array
     * @param length
     */
    //@SuppressWarnings("unchecked")
    public DynamicArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Allocated space for array cannot be negative.");
        }
        this.array = allocate(length);
        this.size = 0; //no elements initially
    }

    /**
     * Copy constructor that takes a DynamicArray and makes a deep copy of it
     * @param other
     */
    //@SuppressWarnings("unchecked")
    public DynamicArray(DynamicArray<T> other) {
        this.array = allocate(other.array.length);
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
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
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
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 or index >= arraySize())
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
     * Private utility to do array allocation
     * @param len
     * @return
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    /**
     * 
     * @param index
     * @param value
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
     * overloads add method to add element to end of array
     * @param value
     */
    public void add(T value) {
        add(size, value);
    }

    /**
     * 
     * @param index
     * @return
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index chosen is out of bounds. Please enter a valid index.");
        }

        T removedElement = array[index];

        T[] newArray = allocate(size - 1);
        
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        for (int i = index; i < size; i++) {
            newArray[i] = array[i+1];
        }

        this.array = newArray;
        size--;

        return removedElement;
    }

    
    /**
     * 
     * @param other
     * @return
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
     * 
     * @param index
     * @param other
     * @return
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
     * 
     * @param index
     * @return
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
     * 
     * @param index
     * @return
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
     * 
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex){

        if (fromIndex < 0 || fromIndex > this.size || fromIndex > toIndex) {
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
     * 
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex){

        if (fromIndex < 0 || fromIndex > this.size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Indices chosen are out of bounds. Please enter valid indices.");
        }

        DynamicArray<T> result = new DynamicArray<>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            result.add(this.get(i));
        }

        return result;
    }
}

