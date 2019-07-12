#!/bin/bash
version="1.8"
github="http://github.com/YooDing/auto-sh"
mail="coding1618@gmail.com"
jdkfileurl="https://mail-tp.fareoffice.com/java/jdk-8u162-linux-x64.tar.gz"
mavenfileurl="http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz"
InstallJDK() {
    echo -e "  [ \033[32mINFO\033[0m ]   Install JDK to OS ....."
    echo
    wget -c ${jdkfileurl}
    ipath="/usr/local"
    installpath=$(
        cd $(dirname $0)
        pwd
    )
    j=$(whereis java)
    java=$(echo ${j} | grep "jdk")
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
    if [[ "$java" != "" ]]; then
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mJava was installed!\033[0m"
    else
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mJava not installed!!\033[0m"
        echo
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m解压 jdk-*-linux-x64.tar.gz!\033[0m"
        tar -xvf jdk-*-linux-x64.tar.gz >/dev/null 2>&1
        echo
        echo
        cd jdk* && jdkname=$(pwd | awk -F '/' '{print $NF}')
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m获取jdk版本: ${jdkname}\033[0m"
        echo
        cd ${installpath}
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m获取当前目录:${installpath}\033[0m"
        echo
        echo
        mv ${jdkname} ${ipath}
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m转移${jdkname}文件到${ipath}安装目录\033[0m"
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mjdk安装目录:${ipath}/${jdkname}\033[0m"
        echo
        echo
        echo "#java jdk" >>/etc/profile
        echo "export JAVA_HOME=${ipath}/${jdkname}" >>/etc/profile
        echo 'export JRE_HOME=${JAVA_HOME}/jre' >>/etc/profile
        echo 'export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib' >>/etc/profile
        echo 'export PATH=${JAVA_HOME}/bin:$PATH' >>/etc/profile
        source /etc/profile >/dev/null 2>&1
        echo
        echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mJDK安装完成!\033[0m"
        echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
    fi
}
InstallMaven() {
    echo -e "  [ \033[32mINFO\033[0m ]  Install Maven to OS ....."
    # install maven
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
    wget http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz
    tar -vxf apache-maven-3.6.1-bin.tar.gz
    mv apache-maven-3.6.1 /usr/local/maven3
    mavenHome=$(cat /etc/profile | grep 'export MAVEN_HOME=')
    if [ "$mavenHome" == "" ]; then
        echo '#maven path' >>/etc/profile
        echo 'export MAVEN_HOME=/usr/local/maven3' >>/etc/profile
        echo 'export PATH=$MAVEN_HOME/bin:$PATH' >>/etc/profile
        source /etc/profile >/dev/null 2>&1
        echo $(mvn -v)
    fi
    echo
    echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mMaven已成功安装!\033[0m"
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"

}
PrintInfo() {
    mver=$(mvn -version | awk '$2=="Maven" {print$3}')
    mhome=$(mvn -version | awk '$2=="home:" {print$3}')
    osver=$(mvn -version | awk '$2=="name:"  {print $3,$4,$5,$6,$7,$8,$9}')
    jre=$(mvn -version | awk '$7=="runtime:" {print $8}')
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
    echo -e "  [ \033[32mINFO\033[0m ] Java Runtime: \033[0;33m${jre}\033[0m"
    echo -e "  [ \033[32mINFO\033[0m ] Maven Version: \033[0;33m${mver}\033[0m "
    echo -e "  [ \033[32mINFO\033[0m ] Maven Home: \033[0;33m${mhome}\033[0m"
    echo -e "  [ \033[32mINFO\033[0m ] OS Info: \033[0;33m${osver}\033[0m"
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
}
About() {
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
    echo -e "  [ \033[32mINFO\033[0m ] Email : \033[0;33m${mail}\033[0m "
    echo -e "  [ \033[32mINFO\033[0m ] this verison: \033[0;33m${version}\033[0m"
    echo -e "  [ \033[32mINFO\033[0m ] GitHub : \033[0;33m${github}\033[0m "
    echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
}
About
InstallJDK
InstallMaven
PrintInfo
