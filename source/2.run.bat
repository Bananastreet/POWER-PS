@echo off
title Power-ps
echo Loading Up Files...
"C:\Program Files (x86)\Java\jdk1.8.0_65\bin\java.exe" -Xmx1000m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar;deps/mvgate3.jar; server.Server
pause