package com.example.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCount {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("spark-wordcount").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> lines = sparkContext.textFile("/Users/user/Documents/github/software/oss/apache/spark/java/apps/app1/demo-spark/src/main/resources/data/data.txt");
        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());

        JavaPairRDD<String, Integer> wordsOnes = words.mapToPair(s -> new Tuple2(s, 1));

        JavaPairRDD<String, Integer> wordsCounts = wordsOnes.reduceByKey((v1, v2) -> v1 + v2 );
        wordsCounts.saveAsTextFile("/Users/user/Documents/github/software/oss/apache/spark/java/apps/app1/demo-spark/src/main/resources/out-data");
    }
}
