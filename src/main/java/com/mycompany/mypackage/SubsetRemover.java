package com.mycompany.mypackage;

import com.google.common.collect.Maps;
import org.apache.http.util.Args;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Original solution:
https://stackoverflow.com/questions/14106121/efficient-algorithm-for-finding-all-maximal-subsets
 */
/*
Implementation limitation:
Because of using "long" as bitmap, so the maximum number of Set supported is 63. We can't use the left-most bit
    because function checkIfOnlyOneBitIsSet() will fail due to negative numbers.
 */
public class SubsetRemover<T> {

    public SubsetRemover(){

    }

    public List<Set<T>> purify(List<Set<T>> inputCol){

        // Create inverse list of elements (T).
        Map<T, Long> inverseCol = Maps.newHashMap();

        IntStream.range(0, inputCol.size()).forEach(index -> {
            inputCol.get(index).stream().forEach(t -> {
                long flag = inverseCol.getOrDefault(t, 1L << index);
                flag |= 1L << index;
                inverseCol.put(t, flag);
            });
        });

        // Eliminate subsets.
        return inputCol.stream().filter(s -> s.size() > 0).filter(s -> {
            Optional<Long> intersectionFlag = s.stream().map(x -> inverseCol.get(x)).reduce((x, y) -> x & y);
            return checkIfOnlyOneBitIsSet(intersectionFlag.get());
        }).collect(Collectors.toList());
    }

    /*
    https://stackoverflow.com/questions/15951776/bitmask-how-to-determine-if-only-one-bit-is-set
    https://stackoverflow.com/questions/12483843/test-if-a-bitboard-have-only-one-bit-set-to-1
     */
    private boolean checkIfOnlyOneBitIsSet(long input){ // 8 bytes (64 bit)

        Args.check(input > 0, "input must be > 0");
        return (input & (input - 1)) == 0;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
/* IMPLEMENTATION IN PYTHON:

import collections
import operator

def is_power_of_two(n):
    """Returns True iff n is a power of two.  Assumes n > 0."""
    return (n & (n - 1)) == 0

def eliminate_subsets(sequence_of_sets):
    """Return a list of the elements of `sequence_of_sets`, removing all
    elements that are subsets of other elements.  Assumes that each
    element is a set or frozenset and that no element is repeated."""
    # The code below does not handle the case of a sequence containing
    # only the empty set, so let's just handle all easy cases now.
    if len(sequence_of_sets) <= 1:
        return list(sequence_of_sets)
    # We need an indexable sequence so that we can use a bitmap to
    # represent each set.
    if not isinstance(sequence_of_sets, collections.Sequence):
        sequence_of_sets = list(sequence_of_sets)
    # For each element, construct the list of all sets containing that
    # element.
    sets_containing_element = {}
    for i, s in enumerate(sequence_of_sets):
        for element in s:
            try:
                sets_containing_element[element] |= 1 << i
            except KeyError:
                sets_containing_element[element] = 1 << i
    # For each set, if the intersection of all of the lists in which it is
    # contained has length != 1, this set can be eliminated.
    out = [s for s in sequence_of_sets
           if s and is_power_of_two(reduce(operator.and_, (sets_containing_element[x] for x in s)))]
    return out

 */
