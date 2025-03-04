#!/bin/bash
if [ "$1" = "build" ]; then
    ./gradlew build
else
    ./gradlew bootRun --args="$*"
fi