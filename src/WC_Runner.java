import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

//WC_Runner is the main driver class for the MapReduce job.
 
public class WC_Runner {
    public static void main(String[] args) throws Exception {
        // Check for correct number of arguments
        if (args.length != 2) {
            System.err.println("Usage: WC_Runner <input path> <output path>");
            System.exit(1);
        }

        // Set up Hadoop configuration and job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Uber Pickup Time and Area Trends");

        job.setJarByClass(WC_Runner.class);

        // Set Mapper, Combiner, and Reducer classes
        job.setMapperClass(WC_Mapper.class);
        job.setCombinerClass(WC_Reducer.class);  
        job.setReducerClass(WC_Reducer.class);

        // Define output key and value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Exit based on job success
        boolean jobCompleted = job.waitForCompletion(true);
        System.exit(jobCompleted ? 0 : 1);
    }
}
