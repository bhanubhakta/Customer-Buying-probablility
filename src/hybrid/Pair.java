package hybrid;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Pair implements WritableComparable<Pair> {

	public Text left;

	public Text right;

	public Pair() {
		left = new Text();
		right = new Text();
	}

	public Pair(String left, String right) {
		this.left = new Text(left);
		this.right = new Text(right);
	}

	public void setleft(Text left) {
		this.left = left;
	}

	public void setright(Text right) {
		this.right = right;
	}

	public Text getleft() {
		return left;
	}

	public Text getright() {
		return right;
	}

	@Override
	public boolean equals(Object b) {
		Pair p = (Pair) b;
		return p.left.toString().equals(this.left.toString())
				&& p.right.toString().equals(this.right.toString());
	}
	
	@Override
	public String toString() {
		return "(" + left + ", " + right + ")";
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		left.readFields(arg0);
		right.readFields(arg0);
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		left.write(arg0);
		right.write(arg0);
	}

	public int compareTo(Pair p1) {
		int k = this.left.toString().compareTo(p1.left.toString());

		if (k != 0) {
			return k;
		} else {
			return this.right.toString().compareTo(p1.right.toString());
		}
	}

}
