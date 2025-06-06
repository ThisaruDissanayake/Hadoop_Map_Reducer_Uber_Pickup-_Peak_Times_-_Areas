import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//WC_Mapper processes each line of input Uber pickup data.
 
public class WC_Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    // Input format for timestamp
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss a");
    private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH");

    private static final IntWritable ONE = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();

        // Skip empty lines
        if (line.isEmpty()) return;

        try {
            // Expecting tab-separated fields
            String[] fields = line.split("\t");
            if (fields.length < 3) return;

            // Extract and clean necessary fields
            String dateTimeStr = fields[0].trim();
            String latStr = fields[1].trim();
            String lonStr = fields[2].trim();

            // Parse the pickup time
            Date date = inputFormat.parse(dateTimeStr);
            String hour = hourFormat.format(date);

            // Emit hour-based key
            context.write(new Text("HOUR_" + hour), ONE);

            // Round coordinates to nearest integer to group locations roughly
            int lat = (int) Math.round(Double.parseDouble(latStr));
            int lon = (int) Math.round(Double.parseDouble(lonStr));

            // Emit location-based key
            context.write(new Text("LOC_" + lat + "," + lon), ONE);

        } catch (Exception e) {
            // Handle parse errors gracefully and log them
            System.err.println("Skipping malformed line: " + line);
        }
    }
}
