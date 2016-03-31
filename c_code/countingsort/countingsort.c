/*
 * Copyright 2016 Anirban Biswas
 */

#include "countingsort.h"

static void FindMinMax(int arr[],
  uint32_t size, int *min_result, int *max_result);

void CountingSort(int arr[], uint32_t size) {
  if (size < 2) {
    return;
  }
  int min, max;
  FindMinMax(arr, size, &min, &max);
  int *bound_array = (int *) calloc(max - min + 1, sizeof(int));
  if (bound_array == NULL) {
    printf("malloc failed. Exiting.");
    return;
  }
  int i;
  for (i = 0; i < size; i++) {
    bound_array[arr[i] - min]++;
  }
  int j;
  int idx = 0;
  for (j = 0; j < max - min + 1; j++) {
    while (bound_array[j] != 0) {
      arr[idx] = j + min;
      idx++;
      bound_array[j]--;
    }
  }
  free(bound_array);
}

static void FindMinMax(int arr[],
  uint32_t size, int *min_result, int *max_result) { 
  int max = arr[0];
  int min = arr[0];
  int i;
  for (i = 1; i < size; i++) {
    if (arr[i] > max) {
      max = arr[i];
    } else if (arr[i] < min) {
      min = arr[i];
    }
  }
  *min_result = min;
  *max_result = max;
}
