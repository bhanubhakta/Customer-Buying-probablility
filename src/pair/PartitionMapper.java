package pair;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionMapper extends Partitioner<Pair, IntWritable> {

	@Override
	public int getPartition(Pair p, IntWritable value, int numReducers) {
		return Math.abs(p.left.hashCode()) % numReducers;
	}

}