#!/bin/sh
# Test script that measures requests per CPU second as well as requests per Megabyte second.
N=10000
# Sends N number of requests to localhost:8080 via curl. Modify according to your application.
COMMAND="curl -s http://localhost:8080/?[1-$N] -o /dev/null"
# Number of iterations
ITER=10
# Execute with maximum of 128 mb memory.
./@app.name@ -Xmn24m -Xmx128m &
MY_PID=$!
LAST_TIME=0
FORMAT_STRING="%10s %10s %10s %10s %10s\n"
REAL_TIME_SUM=0
i="1"
while [ $i -lt $(($ITER + 1)) ]
do
  REAL_TIME=$({ time $COMMAND ; } 2>&1 | grep real | sed -E 's/[^0-9\]+//g' | sed 's/^0*//')
  REAL_TIME_SUM=$(($REAL_TIME_SUM+$REAL_TIME))
  CPU_TIME_TOTAL="$(ps -p $MY_PID -o 'time=' | awk -F'[:.]+' '{t=$3*10+1000*($2+60*$1); print t}')"
  CPU_TIME=$((CPU_TIME_TOTAL - LAST_TIME))
  LAST_TIME=$CPU_TIME_TOTAL
  RSS=$(ps -p $MY_PID -o 'rss=')
  RSS=$(($RSS/1024))
  MEMORY_MS=$(($RSS*$REAL_TIME/1000))
  REQ_PER_S=$(($N*1000/$REAL_TIME))
  REQ_PER_CPUS=$(($N*1000/$CPU_TIME))
  REQ_PER_MBS=$(($N/$MEMORY_MS))
  if [ "$i" -eq "1" ]; then
    printf "$FORMAT_STRING" "n" "cpums" "req/cpus" "rss in mb" "req/mbs";
  fi
  printf "$FORMAT_STRING" $i $CPU_TIME $REQ_PER_CPUS $RSS $REQ_PER_MBS
  i=$[$i+1]
done
MEMORY_MS=$(($RSS*$REAL_TIME_SUM/1000))
REQ_PER_CPUS=$(($ITER*$N*1000/$CPU_TIME_TOTAL))
REQ_PER_MBS=$(($ITER*$N/$MEMORY_MS))
printf "$FORMAT_STRING" "TOTAL" $CPU_TIME_TOTAL $REQ_PER_CPUS $RSS $REQ_PER_MBS
kill $MY_PID
