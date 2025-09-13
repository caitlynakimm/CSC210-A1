/**
 * Establishes operations for dealing with sequences of elements
 * @param <T> Type of elements in sequences
 */
public interface SequenceADT<T> {

    /**
     * Creates sequence with one element
     * @param ABC Element to include
     * @return New sequence with element
     */
    public T[] oneSeq(T ABC);

    /**
     * Operates on sequence at a specific index
     * @param ABC Sequence to operate on
     * @param index The position to access or change
     * @return Result of operation
     */
    public T[] seqAndIndex(T[] ABC, int index);

    /**
     * Combines two sequences to make a new one
     * @param ABC First sequence
     * @param DEF Second sequence
     * @return Combined sequence
     */
    public T[] twoSeq(T[] ABC, T[] DEF);
    
}