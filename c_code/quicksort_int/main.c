#include "quicksort_int.h"

static int32_t comparator(int a, int b);

static void PrintArray(int arr[], int size);

int main(int argc, char **argv) {
  int arr[] = {1,3,2,1,2,2,3};
  QuickSortInt(arr, 7, &comparator);
  PrintArray(arr, 7);
}

static void PrintArray(int arr[], int size) {
  int i;
  for (i = 0; i < size - 1; i++) {
    printf("%d, ", arr[i]);
  }
  printf("%d\n", arr[size - 1]);
}

static int32_t comparator(int a, int b) {
  return a - b;
}
