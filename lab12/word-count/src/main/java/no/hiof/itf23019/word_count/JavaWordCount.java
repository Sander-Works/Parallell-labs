/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.hiof.itf23019.word_count;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public final class JavaWordCount {
	private static final Pattern SPACE = Pattern.compile("(\\s|\\p{Punct})+");

	public static void main(String[] args) throws Exception {


		String inputFile = "text.txt";
		int niterations = 5;
		int nCores = 1;

		long totalSerial, totalPar,start,end;

		start = System.currentTimeMillis();
		System.out.println("Serial running ....");
		List<Tuple2<String, Integer>> output = runWordCount(inputFile, nCores, niterations);
		end = System.currentTimeMillis();
		totalSerial = end - start;

		start = System.currentTimeMillis();
		nCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Paralel running ....");
		output = runWordCount(inputFile, nCores, niterations);
		end = System.currentTimeMillis();
		totalPar = end - start;

		System.out.println("\nResult:");
		for (Tuple2<?, ?> tuple : output) {
			System.out.println(tuple._1() + ": " + tuple._2());
		}
		
		//TODO: Compute Speedup
		System.out.println("serial time: " + totalSerial);
		System.out.println("Parallel time: " + totalPar);
		System.out.println("Parallel speedup: " + (totalSerial/totalPar));
		
	}

	private static List<Tuple2<String, Integer>> runWordCount(String inputFile, int nCores, int niterations) {

		long startTime, endTime;
		
		
		List<Tuple2<String, Integer>> output = null;
		
		JavaSparkContext ctx = getSparkContext(1);
		
		startTime = System.currentTimeMillis();
		
		JavaRDD<String> lines = ctx.textFile(inputFile);
		
		
		for(int i = 0; i < niterations; i++)
		{

			JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

			JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

			JavaPairRDD<String, Integer> counts = ones.reduceByKey(Integer::sum);
			
			// You need to install Spark in order to run this command
			// counts.saveAsTextFile("./out");

			output = counts.collect();
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("Running time: " + (endTime - startTime)/niterations + " miliseconds");

		
		ctx.close();
		ctx.stop();
		
		return output;

	}

	private static JavaSparkContext getSparkContext(final int nCores) {
		Logger.getLogger("org").setLevel(Level.OFF);
		Logger.getLogger("akka").setLevel(Level.OFF);

		final SparkConf conf = new SparkConf().setAppName("edu.coursera.distributed.PageRank")
				.setMaster("local[" + nCores + "]").set("spark.ui.showConsoleProgress", "false");
		JavaSparkContext ctx = new JavaSparkContext(conf);
		ctx.setLogLevel("OFF");
		return ctx;
	}
}