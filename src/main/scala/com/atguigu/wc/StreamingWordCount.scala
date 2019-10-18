package com.atguigu.wc

import org.apache.flink.streaming.api.scala._

/**
 * @author wststart 
 * @create 2019-10-18 12:39 
 */
/*
	流处理wordcount
 */
object StreamingWordCount {
	def main(args: Array[String]): Unit = {
		//		创建执行环境
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		//		获取一个socket文件流
		val textDStream = env.socketTextStream("hadoop102", 10000)
		// 		flatMap和Map需要引用的隐式转换
		val dataDStream = textDStream.flatMap(_.split("\\W+")).map((_, 1)).keyBy(0).sum(1)
		dataDStream.print()
		//		启动executor，执行任务
		env.execute("Socket stream word count")
	}
}
