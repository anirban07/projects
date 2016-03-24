CC = gcc
CFLAGS = -g -Wall -Wpedantic

main: main.o quicksort_int.o
	$(CC) $(CFLAGS) -std=c11 -o $@ $^
main.o: main.c quicksort_int.h
	$(CC) $(CFLAGS) -std=c11 -c $<
quicksort_int.o: quicksort_int.c quicksort_int.h
	$(CC) $(CFLAGS) -std=c11 -c $<
clean:
	rm -f main main.o quicksort_int.o
