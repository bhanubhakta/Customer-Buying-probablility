package hybrid;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//import com.google.common.base.Strings;

public class Reduce extends
		Reducer<Pair, IntWritable, Text, MapWritableExtended> {

	private HashMap<String, Integer> sumMap;

	private String previousTerm = null;

	private double total;

	@Override
	protected void setup(
			Reducer<Pair, IntWritable, Text, MapWritableExtended>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
		sumMap = new HashMap<String, Integer>();
		total = 0;
	}

	@Override
	protected void reduce(
			Pair pair,
			Iterable<IntWritable> values,
			Reducer<Pair, IntWritable, Text, MapWritableExtended>.Context context)
			throws IOException, InterruptedException {

		String term = pair.left.toString();
		if (!term.equals(previousTerm) && previousTerm != null) {
			MapWritableExtended associativeArray = new MapWritableExtended();
			for (Entry<String, Integer> entry : sumMap.entrySet()) {
				associativeArray.put(new Text(entry.getKey()),
						getFormatedDoubleWritable(entry.getValue() / total));
			}
			context.write(new Text(previousTerm), associativeArray);
			total = 0;
			sumMap = new HashMap<String, Integer>();
		}
		int sum = getSum(values);
		previousTerm = term;
		sumMap.put(pair.key2.toString(), sum);
		total += sum;
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		super.cleanup(context);
		MapWritableExtended associativeArray = new MapWritableExtended();
		for (Entry<String, Integer> entry : sumMap.entrySet()) {
			associativeArray.put(new Text(entry.getKey()),
					getFormatedDoubleWritable(entry.getValue() / total));
		}
		context.write(new Text(previousTerm), associativeArray);
	}

	private DoubleWritable getFormatedDoubleWritable(double value) {
		DecimalFormat df = new DecimalFormat("#.###");
		return new DoubleWritable(Double.valueOf(df.format(value)));
	}

	private int getSum(Iterable<IntWritable> values) {
		int sum = 0;
		for (IntWritable intWritable : values)
			sum += intWritable.get();
		return sum;
	}
}
