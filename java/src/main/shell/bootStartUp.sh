#!/bin/sh
#Startup Options
JAVA_OPTS="-Xmx512m -Dfile.encoding=utf8"

jar_name=$1
this_dir="$( cd "$( dirname "$0"  )" && pwd )"
jar_file="${this_dir}/target/${jar_name}"
log_dir="${this_dir}/logs"
log_file="${log_dir}/catalina.out"

#参数个数<1或者参数空值时，中断执行
if [ $# -lt 1 ] || [ -z $1 ]; then
    echo -e "\033[31m请输入要部署的jar包名称!\033[0m"
    exit 1
fi

#日志文件夹不存在，则创建
if [ ! -d "${log_dir}" ]; then
    mkdir "${log_dir}"
fi

#父目录下jar文件存在
if [ -f "${jar_file}" ]; then
    #启动jar包；重定向标准错误输出到文件，丢掉标准输出
    java $JAVA_OPTS -jar ${jar_file} 1>/dev/null 2>"${log_file}" &
    exit 0
else
    echo -e "\033[31m${jar_file}文件不存在！\033[0m"
    exit 1
fi
