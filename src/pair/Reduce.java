package pair;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Pair, IntWritable, Pair, DoubleWritable> {

	private double total;

	@Override
	protected void setup(
			Reducer<Pair, IntWritable, Pair, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
		total = 0.0;
	}

	@Override
	protected void reduce(Pair pair, Iterable<IntWritable> values,
			Reducer<Pair, IntWritable, Pair, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		int s = 0;
		for (IntWritable v : values) {
			s += v.get();
		}

		if (pair.getright().toString().equals("a")) {
			total = s;
		} else {
			double r = s / total;
			DecimalFormat df = new DecimalFormat("#.###");
			context.write(pair, new DoubleWritable(Double.valueOf(df.format(r))));
		}
	}
}
