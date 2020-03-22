#!/bin/bash

# Global variables
SERVER_DIR=/home/ubuntu
JAVA_BIN=/usr/bin/java

am_i_root() {
    [ "$(id -u)" -eq 0 ]
}

stop_if_necessary() {
        if [ -f  server.pid ]; then
                        echo Server already running, will stop then restart.
                        PID=$(cat server.pid)
                        if [ "$PID" ]; then
                                        echo PID of running server is $PID, stopping now...
                                        kill -15 $PID
                                        echo Server stopped, now starting...
                                        rm server.pid
                        fi
        fi
}

start_server() {
        cd $SERVER_DIR

        $JAVA_BIN -jar queue-service.jar &
        PID=$!
        echo $PID > server.pid
        echo Please check the queue-service.log for finish of startup! e.g. using tail -f queue-service.log
}

if am_i_root; then
    echo 'Server cannot be started as root!'
else
    stop_if_necessary
    start_server
fi
