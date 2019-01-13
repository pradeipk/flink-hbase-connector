/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pradeipk.flink.hbase;

import java.util.List;

import org.apache.flink.addons.hbase.HBaseTableSource;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.types.Row;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;


public class HBaseBatchJob {

	public static void main(String[] args) throws Exception {
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		//System.setProperty("hadoop.home.dir", "ANY_DIRECTORY");
		Configuration conf = HBaseConfiguration.create();
		conf.clear();
		conf.set("hbase.zookeeper.quorum", "10.24.31.52");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.master", "10.24.31.52:16010");
	//	conf.setQuietMode(false);
		
		HBaseTableSource hbt = new HBaseTableSource(conf, "person");		
		DataSet<Row> g = hbt.getDataSet(env);		
		/*for (Row row : g) {
			System.out.println(row.toString());
		}*/
		System.out.println("------------->");		
		env.execute("Flink Batch Java API Skeleton");
	}
}
