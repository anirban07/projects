/*
 * Copyright 2016 Anirban Biswas
 */

#ifndef _COUNTINGSORT_H_
#define _COUNTINGSORT_H_

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

/*
 * Takes an array of integers and its size and sorts the array in place
 * Note - This is not a stable sort
 */
void CountingSort(int arr[], uint32_t size);

#endif  // _COUNTINGSORT_H_
