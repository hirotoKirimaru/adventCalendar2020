FROM adoptopenjdk/openjd11:ubi
ARG JAR_PATH
COPY ${JAR_PATH} /usr/local/advent_calendar.jar
EXPOSE 80
CMD ["java" ,\
     "-Xms:2048m", \
     "-Xmx:2048m", \
     "-XX:MacRAM=2048m",\
     "-XX:+UseContainerSupport",\
     "-XX:+G1GC",\
     "-Xlog:gc*=info=/var/log/app/gc_%t.log:time,uptime,level,tags:filecount=30,filesize=10M",\
     "-Djava.io.tmpdir=/tmp",\
     "-jar",\
     "/usr/local/advent_calendar.jar"\
]
