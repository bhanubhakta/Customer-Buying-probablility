# Customer-Buying-probablility
Customer buying probability using map reduce framework Hadoop-MapReduce

Make the jar file from the project.

Copy input files to the hadoop directory

hadoop fs -copyFromLocal <localDir> <hadoop-dir>

See inputs in hadoop directory
hadoop fs -cat <hadoop-input-dir>

Remove output folder if it is present
hadoop fs -rm -r <hadoop-output-dir>

``Pair Approach``

Start hadoop process
hadoop jar <compiled-jar> pair.Main <hadoop-input-dir> <hadoop-output-dir>

See Output
hadoop fs -cat <hadoop-output-dir>

``Stripe Approach``
Start hadoop process
hadoop jar <compiled-jar> stripe.Main <hadoop-input-dir> <hadoop-output-dir>

See Output
hadoop fs -cat <hadoop-output-dir>

``Hybrid Approach``
Start hadoop process
hadoop jar <compiled-jar> hybrid.Main <hadoop-input-dir> <hadoop-output-dir>

See Output
hadoop fs -cat <hadoop-output-dir>
