package interfaces.views;

import enums.SortType;

/**
 * The {@code SortViewable} interface defines a method for displaying a sorted view of implementing classes based on the specified {@link SortType}.
 * 
 * <p>Classes that implement this interface should provide their own implementation for the {@code sortView()} method, specifying how their information is presented in a sorted manner based on the provided {@code SortType}.</p>
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public interface SortViewable {

    /**
     * Displays a sorted view of the information or state of the implementing class based on the specified {@code SortType}.
     * 
     * @param sortType The {@link SortType} specifying the sorting criteria.
     */
    public void sortView(SortType sortType);
}