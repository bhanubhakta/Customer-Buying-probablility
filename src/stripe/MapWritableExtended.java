package stripe;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;

public class MapWritableExtended extends MapWritable {

	public MapWritableExtended() {
		super();
	}

	@Override
	public String toString() {
		//Print format: [(key1, key2), value]
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int length = this.entrySet().size();
		int count = 0;
		for (Entry<Writable, Writable> entry : this.entrySet()) {
			sb.append("(");
			sb.append(entry.getKey().toString());
			sb.append(", ");
			sb.append(entry.getValue().toString());
			sb.append(")");
			count++;
			if (count != length) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
