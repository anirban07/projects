/*
 * Copyright 2016 Anirban Biswas
 */

#include "countingsort.h"

static void PrintArray(int arr[], int size);

int main(int argc, char **argv) {
  int arr[] = {2,-4,6,1,5,-9,4,2};
  CountingSort(arr, 8);
  PrintArray(arr, 8);
}

static void PrintArray(int arr[], int size) {
  int i;
  for (i = 0; i < size - 1; i++) {
    printf("%d, ", arr[i]);
  }
  printf("%d\n", arr[size - 1]);
}
