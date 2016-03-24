/*
 * Copyright 2016 Anirban Biswas
 */

#ifndef _QUICKSORT_INT_H_
#define _QUICKSORT_INT_H_

#include <stdint.h>

/*
 * Takes an array of void pointers, its size and a function to compare
 * elements of the array and sorts the given array.
 * Comparator :- The comparator should return:
 *               - a negative number if first argument < second argument
 *               - zero if first argument == second argument
 *               - a positive number if first argumetn > second argument
 * Note - This is not a stable sort
 * Note - This function modifies the given array
 */
void QuickSortInt(void *arr[],
  uint32_t size, int32_t (*comparator)(void *, void *));

#endif  // _QUICKSORT_INT_H_
