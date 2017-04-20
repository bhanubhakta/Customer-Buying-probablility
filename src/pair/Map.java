package pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Pair, IntWritable> {
	HashMap<Pair, Integer> pairHash = new HashMap<Pair, Integer>();

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Pair, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString().trim();
		String[] input = line.split("\\s+");
		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length && !input[i].equals(input[j]); j++) {
				Pair pair = new Pair(input[i], input[j]);
				this.pairHash.put(
						pair,
						this.pairHash.containsKey(pair) ? this.pairHash
								.get(pair) + 1 : 1);
			}
		}
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		for (Entry<Pair, Integer> element : pairHash.entrySet()) {
			Pair starPair = new Pair(element.getKey().getKey1().toString(), "a");
			context.write(starPair, new IntWritable(1));
			context.write(element.getKey(), new IntWritable(element.getValue()));
		}
	}
}
