# determine base directory; preserve where you're running from
#echo "Path to $(basename $0) is $(readlink -f $0)"
realpath=$(readlink -f "$0")
filepath=$(dirname "$realpath")
basedir=${filepath%/*}
echo $basedir
LOG_DIR=${basedir}/logs
mkdir -p ${LOG_DIR}
GC_LOG_DIR=${basedir}/logs/gc
mkdir -p ${GC_LOG_DIR}
GC_FILE_PATH="${GC_LOG_DIR}/gc-$(date +%s).log"

# jdk8
#-XX:MetaspaceSize=128M 
#-XX:MaxMetaspaceSize=256M 

export JAVA_OPTS="$JAVA_OPTS 
-server -Xmn512M -Xms2048M -Xmx2048M 
-Djava.net.preferIPv4Stack=true 
-Djava.awt.headless=true 
-Dorg.apache.catalina.connector.RECYCLE_FACADES=false 
-XX:+UseConcMarkSweepGC 
-XX:+UseParNewGC 
-XX:+CMSParallelRemarkEnabled 
-XX:+UseCMSCompactAtFullCollection 
-XX:CMSFullGCsBeforeCompaction=1 
-XX:+CMSClassUnloadingEnabled 
-XX:+UseFastAccessorMethods 
-XX:+UseCMSInitiatingOccupancyOnly  
-XX:SurvivorRatio=65536 
-XX:MaxTenuringThreshold=0 
-XX:CMSInitiatingOccupancyFraction=81 
-XX:SoftRefLRUPolicyMSPerMB=0 
-XX:+PrintGCDetails 
-XX:+PrintGCTimeStamps 
-verbose:gc 
-Xloggc:${GC_FILE_PATH} 
-XX:+UseGCLogFileRotation 
-XX:NumberOfGCLogFiles=10 
-XX:GCLogFileSize=100M
"

export JAVA_OPTS="$JAVA_OPTS -DLOG_DIR=${LOG_DIR} -Denv=idc"

export CATALINA_OPTS="$CATALINA_OPTS 
-Dcom.sun.management.jmxremote.port=8901
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=true
-Dcom.sun.management.jmxremote.password.file=jmxremote.password
-Dcom.sun.management.jmxremote.access.file=jmxremote.access
-Djava.rmi.server.hostname=127.0.0.1"



