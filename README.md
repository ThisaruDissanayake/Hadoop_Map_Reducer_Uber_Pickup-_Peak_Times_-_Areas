# 🚖 Uber Pickup Analysis using Hadoop MapReduce

This project focuses on analyzing Uber pickup patterns in New York City during April 2014, using Apache Hadoop MapReduce. The goal is to identify the peak hours and hotspot locations for Uber rides, which can help in better ride allocation, driver scheduling, and business optimization.
The processing is done using a single-node Hadoop cluster set up on Ubuntu, and Java-based MapReduce programs are written to perform the data aggregation based on time (hourly) and location (rounded latitude/longitude). The final output helps visualize Uber usage trends, identify the most active hours, and locate the highest density pickup zones

## ⚙️ Technologies Used

| Tool / Technology     | Description                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| **Ubuntu 24.10**      | Operating system for setting up the local Hadoop environment                |
| **Apache Hadoop**     | Framework used for distributed storage (HDFS) and data processing (MapReduce)|
| **Hadoop HDFS**       | Hadoop Distributed File System for storing input/output data                |
| **Java 8+**           | Programming language used for developing Mapper, Reducer, and Runner classes|
| **MapReduce**         | Data processing model used to aggregate Uber pickup data                    |
| **Kaggle Dataset**    | Real-world Uber pickup data used for analysis (April 2014 subset)           |
| **Terminal & SSH**    | Used for compiling, running Hadoop jobs and accessing HDFS                  |
| **Browser (localhost)**| To access Hadoop Resource Manager UI (`http://localhost:8088/`)             |


---

## 🛠️ Setup: Hadoop on Ubuntu

Refer: [phoenixNAP Hadoop Setup Guide](https://phoenixnap.com/kb/install-hadoop-ubuntu)

### Steps to Install and Setup Hadoop:

1. **Install JDK on Ubuntu**
2. **Setup Hadoop user and configure SSH**
3. **Download and install Hadoop**
4. **Single-node Hadoop deployment**
5. **Format the HDFS NameNode**
6. **Start Hadoop cluster**
7. **Access Hadoop from the browser:**
   - Namenode: [http://localhost:9870](http://localhost:9870)
   - ResourceManager: [http://localhost:8088](http://localhost:8088)

---

## 📦 Dataset Description

### 📂 Source:
- Dataset: **Uber Pickups in New York City**
- Provider: [FiveThirtyEight on Kaggle](https://www.kaggle.com/datasets/fivethirtyeight/uber-pickups-in-new-york-city)
- License: Open data shared for analysis and visualization purposes.

### 📅 Data Used:
We selected **April 2014** data (from the `uber-raw-data-apr14.csv`) for this project to keep the scope manageable and focused.

### 🔍 Sample Rows:

- `Date/Time`: Timestamp of pickup  
- `Lat` / `Lon`: Latitude and Longitude of pickup location  
- `Base`: Uber dispatching base code (e.g., B02512)

### 🧼 Data Preprocessing:
- Only **April** records were used from the full dataset
- Fields were cleaned by change the time and data structure,convert .csv to .txt and used as-is for MapReduce parsing
- The final input file used was named: `data3.txt` (stored in the `/input/` folder)


---

 ## 📁 File Structure
  <pre> 
   ├── input/
   │└── data3.txt # Uber pickup dataset (April 2014) 
   ├── src/ 
   │ ├── WC_Mapper.java 
   │ ├── WC_Reducer.java 
   │ └── WC_Runner.java 
   ├── classes/ 
   │ ├── WC_Mapper.class 
   │ ├── WC_Reducer.class 
   │ └── WC_Runner.class 
   ├── Analizedata.jar # JAR file containing the MapReduce code 
   ├── output/ # HDFS output directory (created during execution) 
   └── output.txt # Local copy of results ``` </pre>

   ---

   ## ▶️ Step to Run the Project 
   ### 1.Initiate login Start Hadoop Cluster

   <pre>start-dfs.sh
start-yarn.sh</pre>
   

   ### 2.Create Input Directory and Upload Dataset

   <pre>mkdir input
# Place your `data.txt` inside the input directory

hdfs dfs -mkdir /input
hdfs dfs -put input/data.txt /input/</pre>

   ### 3.Create the Java files

   Make the Mapper.java,Reducer.java and Runner.java files inside the src folder

   ### 4.Compile Java Code and Create JAR

   <pre>mkdir -p classes
javac -classpath "$(hadoop classpath)" -d classes src/*.java
jar -cvf Analizedata.jar -C classes/ .
</pre>

   ### 5.Run the Hadoop Job

   <pre>hadoop jar Analizedata.jar WC_Runner /input /output
</pre>

   ### 6.View Output

   <pre>hdfs dfs -cat /output/part-r-00000
</pre>

---

## 📊 Result Summary

###⏰ Peak Pickup Hours

### ⏰ Hourly Pickup Distribution (3 PM – 9 PM)

| Hour | Time (24h) | Pickups | 
|------|------------|---------|
| 15   | 3 PM       | 35,324  | 
| 16   | 4 PM       | 42,003  | 
| 17   | 5 PM       | 45,475  | 🔥 Peak Hour            
| 18   | 6 PM       | 43,003  | 
| 19   | 7 PM       | 38,923  | 
| 20   | 8 PM       | 36,244  | 
| 21   | 9 PM       | 36,964  | 

📌 Peak hours fall between 3 PM and 9 PM, with the maximum around 5 PM.

📍 Pickup Location Hotspots
| Location (Lat, Lon) | Pickups  |
|---------------------|----------|
| LOC_41,-74          | 564,238  |
| LOC_41,-73          | 186      |
| LOC_40,-74          | 42       |

📌 Main location hotspot is at 41°N, 74°W — corresponds to NYC metro area.

Deploy more Uber drivers between 3 PM to 9 PM, especially 5 PM
Focus coverage around latitude 41 and longitude -74 (NYC urban core)
---
