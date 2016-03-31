CC = gcc
CFLAGS = -g -Wall -Wpedantic

main: main.o countingsort.o
	$(CC) $(CFLAGS) -std=c11 -o $@ $^
main.o: main.c countingsort.h
	$(CC) $(CFLAGS) -std=c11 -c $<
quicksort_int.o: countingsort.c countingsort.h
	$(CC) $(CFLAGS) -std=c11 -c $<
clean:
	rm -f main main.o countingsort.o
