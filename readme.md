This classifier is, in my opinion, the simplest and the most useful classifier for the NLP. 
It is simple, fast, and gives great results.
The original code was refactored from the old Classifier4j project, corrected, simplified and enhanced for the best performace.
The goal of this project is to evolve it, given the original potential, into something more useful.

The code has been changed so that classifier can be trained, words removed from the trained set to adjust the precision, etc. Any arithmetic is possible, however, the use of additional arithmetics is now investigated. For example, we can train as the IF-THEN logical implication assuming that one document implies the other. However, the meaning of the results is uncertain. For example if we train with documents A and B as A implies B, the calculated distance would mean:"Assuming A implies B, the C is therefore distant from an assumption as K ...". Sounds great, but what is the real-world application of it? Could we a distance of the known facts to multiple assumptions to see what documents imply one another... or perhaps there can be a syntax applied like with SQL ?  This is now investigated.


