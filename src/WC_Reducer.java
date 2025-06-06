import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//WC_Reducer aggregates the values (IntWritable) associated with each key (Text)

public class WC_Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    // Reusable IntWritable object to avoid repeated object creation
    private final IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int sum = 0;

        // Sum all values for the given key
        for (IntWritable value : values) {
            sum += value.get();
        }

        // Set the result and write to context
        result.set(sum);
        context.write(key, result);
    }
}
