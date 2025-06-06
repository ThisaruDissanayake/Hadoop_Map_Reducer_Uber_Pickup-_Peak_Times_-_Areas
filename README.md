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

 ## 📁 File Structure 
   ├── input/ │
   └── data3.txt # Uber pickup dataset (April 2014) 
   ├── src/ 
   │ ├── Mapper.java 
   │ ├── Reducer.java 
   │ └── Runner.java 
   ├── classes/ 
   │ ├── WC_Mapper.class 
   │ ├── WC_Reducer.class 
   │ └── WC_Runner.class 
   ├── Analizedata.jar # JAR file containing the MapReduce code 
   ├── output/ # HDFS output directory (created during execution) 
   └── output.txt # Local copy of results 

