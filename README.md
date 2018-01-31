# SubsetRemover
A small tool to remove subsets in the list of sets.

Based on the idea and python implementation in [1]. Its advantage, compared to naive implementation, like [2], is that it is very **efficient**; thus, expectedly run fast!

Supposed that there are sets: `[1,2,3]`, `[1,2]`, `[2,3]` and `[2,4]`. It's clear that `[1,2]` and `[2,3]` are subsets of `[1,2,3]`; and one may need to retain only meaningful largest sets.

Input: `[1,2,3]`, `[1,2]`, `[2,3]` and `[2,4]`

Output: `[1,2,3]` and `[2,4]`

I tried to create a compact class, thanks to Functional Programming in Java 8.

Development Environment

+ Intellij IDEA 2017.3
+ Java v1.8.0_151 x64
+ Maven v3.5.0
+ Windows 8.1 x64.

Reference:

[1] https://stackoverflow.com/questions/14106121/efficient-algorithm-for-finding-all-maximal-subsets

[2] https://stackoverflow.com/questions/8882097/how-to-calculate-the-intersection-of-two-sets
