import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WC_Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss a"); // double space!
    private SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
    private final static IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();

        if (line.isEmpty()) return;

        try {
            String[] fields = line.split("\t");  // TAB-separated
            if (fields.length < 4) return;

            String dateTime = fields[0].trim();
            String latStr = fields[1].trim();
            String lonStr = fields[2].trim();

            Date date = inputFormat.parse(dateTime);
            String hour = hourFormat.format(date);

            context.write(new Text("HOUR_" + hour), one);

            int lat = (int) Math.round(Double.parseDouble(latStr));
            int lon = (int) Math.round(Double.parseDouble(lonStr));
            context.write(new Text("LOC_" + lat + "," + lon), one);

        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
        }
    }
}

