package com.atguigu.wc

import org.apache.flink.api.scala._

/**
 * @author wststart 
 * @create 2019-10-18 11:25 
 */
/*
	批处理word count程序
 */
object WordCount {
	def main(args: Array[String]): Unit = {
		//		创建一个执行环境
		val env = ExecutionEnvironment.getExecutionEnvironment
		//		从文件中读取数据
		val inputDS = env.readTextFile("D:\\workspace_idea1\\wst-flink-tutorial\\src\\main\\resources\\hello.txt")
		//		切分数据得到word,然后按照word做分组聚合
		// 		flatMap和Map需要引用的隐式转换
		val wordCountDS = inputDS.flatMap(_.split("\\W+")).map((_, 1)).groupBy(0).sum(1)
		//		打印输出
		wordCountDS.print()
	}
}
