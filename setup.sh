#!/bin/bash

echo "Starting setup of LRA executor extension Narayana integration"

cd /tmp
git clone https://github.com/xstefank/lra-executor-extension.git
cd lra-executor-extension
mvn clean install

cd ../
git clone https://github.com/xstefank/narayana.git
cd narayana
git checkout lra-executor
cd rts/lra/lra-rest-definitions
mvn clean install

cd ../lra-client
mvn clean install

echo "Setup is finished"
