#!/bin/bash

echo "Starting setup of LRA executor extension Narayana integration"

cd /tmp
git clone https://github.com/xstefank/lra-executor-extension.git
cd lra-executor-extension
mvn clean install -DskipTests

cd ../
git clone https://github.com/xstefank/narayana.git
cd narayana
git checkout lra-executor
mvn clean install -DskipTests

echo "Setup is finished"

