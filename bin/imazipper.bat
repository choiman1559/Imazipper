@echo off
cd /d %~dp0
cd ..
cd lib

java -jar imazipper_cui.jar %*
Pause