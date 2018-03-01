### 功能说明
---
#### 1. JAVA部分
>文件读取:ApacheCommon-io方式(读取效率比较高)

```
    //使用方式
    File file = new File(path);
    LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
    while (lineIterator.hasNext()) {
        String line = lineIterator.nextLine();
    }
```

##### 1.1 adReport
> 广告平台收益数据拉取代码:

| 平台 | 说明 |


##### 2. shell
>1. bootStartUp.sh :boot启动脚本(详细说明参见shell&注释)
