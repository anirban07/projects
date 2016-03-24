CC = gcc
CFLAGS = -g -Wall -Wpedantic

main: main.o quicksort.o
	$(CC) $(CFLAGS) -std=c11 -o $@ $^
main.o: main.c quicksort.h
	$(CC) $(CFLAGS) -std=c11 -c $<
quicksort_int.o: quicksort.c quicksort.h
	$(CC) $(CFLAGS) -std=c11 -c $<
clean:
	rm -f main main.o quicksort.o
