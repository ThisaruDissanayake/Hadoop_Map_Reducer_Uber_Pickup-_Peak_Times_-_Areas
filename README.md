# 🚖 Uber Pickup Analysis using Hadoop MapReduce

This project analyzes Uber pickup data using Hadoop MapReduce. It identifies **peak hours** and **hotspot locations** based on real pickup records, offering insights to optimize fleet distribution and service planning.

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
- Fields were cleaned by change the time and data structure and used as-is for MapReduce parsing
- The final input file used was named: `data.txt` (stored in the `/input/` folder)


---

 ## 📁 File Structure
  <pre> 
   ├── input/ │
   └── data3.txt # Uber pickup dataset (April 2014) 
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

