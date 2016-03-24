/*
 * Copyright 2016 Anirban Biswas
 */

#include "quicksort_int.h"

static int *array_to_sort;

static void Swap(uint32_t first_index, uint32_t second_index);

static void QuickSortIntHelper(uint32_t lo,
                               uint32_t hi,
                               int32_t (*comparator)(int, int));

static uint32_t FindPivot(uint32_t lo,
                          uint32_t hi,
                          int32_t (*comparator)(int, int));

void QuickSortInt(int arr[],
                  uint32_t size,
                  int32_t (*comparator)(int, int)) {
  array_to_sort = arr;
  QuickSortIntHelper(0, size, comparator);
}

/*
 * Helper fucntion of QuickSortInt.
 * Sorts the subarray of array_to_sort marked by lo and hi.
 */
static void QuickSortIntHelper(uint32_t lo,
                               uint32_t hi,
                               int32_t (*comparator)(int, int)) {
  if (hi - lo <= 1) {  // No sorting to be done in empty or single element array
    return;
  }
  // Pivot point cannot be found here, manually sort and return
  if (hi - lo == 2) {
    if (comparator(array_to_sort[lo], array_to_sort[hi - 1]) > 0) {
      Swap(lo, hi - 1);
    }
    return;
  }
  uint32_t pivot = FindPivot(lo, hi, comparator);
  int pivot_value = array_to_sort[pivot];
  Swap(pivot, lo);  // Move pivot to the front

  uint32_t i = lo + 1;  // Keeps track of "less than" half
  uint32_t j = hi - 1;  // Keeps track of "more than or equal to" half
  // Fencepost. Update i, j before swapping for the first time
  while ((i < j) && comparator(array_to_sort[i], pivot_value) < 0) {
    i++;
  }
  while ((i <= j) && comparator(array_to_sort[j], pivot_value) >= 0) {
    j--;
  }
  // Swap only if i < j. Update i, j
  while (i < j) {
    Swap(i, j);
    i++;
    j--;
    while ((i < j) && comparator(array_to_sort[i], pivot_value) < 0) {
      i++;
    }
    while ((i <= j) && comparator(array_to_sort[j], pivot_value) >= 0) {
      j--;
    }
  }
  Swap(lo, j);  // Move pivot in between the 2 halves
  QuickSortIntHelper(lo, j, comparator);  // Sort left half
  QuickSortIntHelper(i, hi, comparator);  // Sort right half
}

/*
 * Takes in two indexes as input and swaps the entries at those indexes
 * in the array_to_sort array.
 */
static void Swap(uint32_t first_index, uint32_t second_index) {
  int temp = array_to_sort[first_index];
  array_to_sort[first_index] = array_to_sort[second_index];
  array_to_sort[second_index] = temp;
}

/*
 * Pivot - The index of the median of the lowest, highest and middle element
 *         in an array.
 * Finds the pivot in the subarray of array_to_sort array marked by lo and hi.
 * lo is inclusive and hi is exclusive.
 * Uses the comparator function passed to compare elements of the array.
 */
static uint32_t FindPivot(uint32_t lo,
                          uint32_t hi,
                          int32_t (*comparator)(int, int)) {
  uint32_t mid = lo + (hi - lo) / 2;
  if (comparator(array_to_sort[lo], array_to_sort[hi - 1]) *
      comparator(array_to_sort[lo], array_to_sort[mid]) < 0) {
    // array_to_sort[lo] is in between array_to_sort[hi - 1] and array_to_sort[mid]
    return lo;    
  } else if (comparator(array_to_sort[mid], array_to_sort[hi - 1]) *
             comparator(array_to_sort[mid], array_to_sort[lo]) < 0) {
    // array_to_sort[mid] is in between array_to_sort[hi - 1] and array_to_sort[lo]
    return mid;
  } else {
    // array_to_sort[hi - 1] is in between array_to_sort[lo] and array_to_sort[mid]
    return hi - 1;
  }
}
