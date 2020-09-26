## 论文查重
|        项目        |        内容        |
| :------: | :------: |
| 这个项目属于哪个课程 | [软件工程](https://edu.cnblogs.com/campus/gdgy/informationsecurity1812) |
|  这个作业要求在哪里  |   [作业要求](https://edu.cnblogs.com/campus/gdgy/informationsecurity1812/homework/11155)   |
|    这个作业的目标    |     实现论文查重，熟悉git的使用    |

## github链接
https://github.com/PURSUE2/3118005369

## PSP表格
|PSP2.1|Personal Software Process Stages|预估耗时（分钟）|实际耗时（分钟）|
|------|------|------|------|
|Planning|计划|40|40|
|Estimate|估计这个任务需要多少时间|1060|1060|
|Development|开发|180|180|
|Analysis|需求分析 (包括学习新技术)|60|60|
|Design Spec|生成设计文档|120|120|
|Design Review|设计复审|60|60|
|Coding Standard|代码规范 (为目前的开发制定合适的规范)|60|60|
|Design|具体设计|60|60|
|Coding|具体编码|120|120|
|Code Review|代码复审|60|60|
|Test|测试（自我测试，修改代码，提交修改）|240|240|
|Reporting|报告|60|60|
|Test Repor|测试报告|30|30|
|Size Measurement|计算工作量|30|30|
|Postmortem & Process Improvement Plan|事后总结, 并提出过程改进计划|60|60|
||合计|1180|1180|

## 计算模块接口的设计与实现过程
**算法关键：采用余弦相似性和TF算法相结合的方式**
- 实现论文查重功能共使用了三个类：WordFrequency.java、Test.java和TestExample.java;
    - WordFrequency类主要包含统计高频出现的关键字以及对两篇文章进行对比后计算出相似度，而Test类则是调用WordFrequency类的功能来将结果输出。
- WordFrequency类包含了5个方法，各自的功能如下：
    - Map<String, Integer> count(Map<String, Integer> frequencies, String content)：
        - 利用ik分词器将文章内容就行分词，对各词出现的频率进行统计，并存放到map中。
    - List<Map.Entry<String, Integer>> order(Map<String, Integer> data)：
        - 对词按出现的次数从高到低排序。
    - HashMap<String, Float> getKeyWords(String content, int num)：
        - 根据需保留关键字字数及文章内容，调用上述2个方法，得到处理后的HashMap。
    - HashMap<String, Float> getKeyWords(File file, int num)：
        - 传入文件，将文件文本内容取出并存放到String中，再调用上个getKeyWords(String content, int num)方法。
    - getSimilarity(File f1, File f2, int num)：
        - 传入两个文件，调用getKeyWords(File file, int num)方法得到各自的关键字map，然后通过遍历各自的键，将对方没有的键增添到对方map中并设置值为0，之后再通过**余弦相似性**，对两个map进行向量计算，得出相似率。

- Test类将控制台传入的参数传入到WordFrequency类的方法中调用，通过outputFile(double similarity, String path)方法将答案文件写出。

- TestExample类主要负责多样例的测试，保证结果的准确性。

## 计算模块接口部分的性能改进
`Set<String> keys1 = f1KeyWords.keySet();`
在计算TF算法时利用上面得出的keys1进行遍历；
下方对两个关键字map用同一个key集合操作；
`for(String key : keys1){
            value1 += f1KeyWords.get(key)*f2KeyWords.get(key);
            value2 += f1KeyWords.get(key)*f1KeyWords.get(key);
            value3 += f2KeyWords.get(key)*f2KeyWords.get(key);
        }`

## 计算模块部分单元测试展示
- 部分测试样例如下：
`double similarity;
        WordFrequency wf = new WordFrequency();

        System.out.println("orig.txt和orig_0.8_add.txt的相似度为：");
        similarity = wf.getSimilarity(new File("D:\\test\\orig.txt"), new File("D:\\test\\orig_0.8_add.txt"), 25);
        System.out.println(similarity);

        System.out.println("orig.txt和orig_0.8_del.txt的相似度为：");
        similarity = wf.getSimilarity(new File("D:\\test\\orig.txt"), new File("D:\\test\\orig_0.8_del.txt"), 25);
        System.out.println(similarity);`
- 测试结果如下：
![](https://img2020.cnblogs.com/blog/2149577/202009/2149577-20200925015617432-413585438.png)

## 计算模块部分异常处理说明
在HashMap<String, Float> getKeyWords(String content, int num)方法中，若不对num做处理而直接使用，一方面会引发空指针异常，一方面又会降低代码性能，故用三目运算符对i值阈值进行处理。
`for(int i = 0; i < (num < size ? num : size); i++){
      String key = result.get(i).getKey();
      Integer f = result.get(i).getValue();
      Float frequency = f/size;
      map.put(key, frequency);
}`

### 最后
`该代码对应jar包在命令行界面运行时需按如下格式：java -Dfile.encoding=utf-8 -jar project03-1.0-SNAPSHOT.jar D:\test\orig.txt D:\test\orig_0.8_dis_15.txt D:\test\result.txt`