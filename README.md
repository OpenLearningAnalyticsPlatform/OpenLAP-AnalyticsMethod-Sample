# OpenLAP-AnalyticsMethodFramework Sample

## INTRODUCTION

This is an example of an Analytics Method created using the OpenLAP-AnalyticsMethodsFramework.
It additionally has a main routine that simulates the execution of the Analytics Method with a sample OLAPDataSet and
 a sample OLAPPortConfiguration provided in the `resources` folder.


## USAGE
Execute the class `ExecutionExample` as a java program.
The Analytics Method called WikiWordCounterAnalyticsMethod will count the apparition of a set of words given as input
into another set of wiki entries (or any string) also given as input. The inputs are configured using the 
`ConfigurationExampleWikiWordCount.json` file and the sample input OLAPDataSet is on the
`DataSetInputExampleWikiWordCount.json` file. 
