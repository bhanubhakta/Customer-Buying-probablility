package stripe;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, MapWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, MapWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString().trim();
		String[] input = line.split("\\s+");

		for (int i = 0; i < input.length; i++) {
			MapWritable mapWritable = new MapWritable();
			for (int j = i + 1; j < input.length && !input[i].equals(input[j]); j++) {
				mapWritable.put(new Text(input[j]), new IntWritable(1));
			}
			context.write(new Text(input[i]), mapWritable);
		}
	}
}
