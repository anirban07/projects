#include "quicksort.h"
#include <stdlib.h>
#include <stdio.h>

static int32_t comparator(void *a, void *b);

static void PrintArray(void *arr[], int size);

int main(int argc, char **argv) {
  int *a = malloc(sizeof(int));
  *a = 1;
  int *b = malloc(sizeof(int));
  *b = 2;
  int *c = malloc(sizeof(int));
  *c = 3;
  int *d = malloc(sizeof(int));
  *d = 4;
  void *arr[] = {d, c, a, b};
  QuickSortInt(arr, 4, &comparator);
  PrintArray(arr, 4);
  free(a);
  free(b);
  free(c);
  free(d);
}

static void PrintArray(void *arr[], int size) {
  int i;
  for (i = 0; i < size - 1; i++) {
    printf("%d, ", *((int *) (arr[i])));
  }
  printf("%d\n", *((int *) (arr[size - 1])));
}

static int32_t comparator(void *a, void *b) {
  return *((int *) a) - *((int *) b);
}
