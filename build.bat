:: For Windows only

@echo off

rem worldmanager Build Script
rem Use this to build the project instead of running gradle jar

echo worldmanager Build Script
echo Building project...
rmdir /s /q output
call ./gradlew jar
jlink --add-modules java.base,java.desktop,java.net.http,jdk.crypto.cryptoki --output output --no-man-pages --no-header-files

set MAVEN_REPO_DIR=%userprofile%\.m2\repository\
set LIBRARIES=output\worldmanager\objects\main
set HASH_RESULT=""
set FILE="json-20240303.jar"



mkdir output\worldmanager
move /y build\libs\worldmanager-0.2.0.jar output\worldmanager\worldmanager.jar


xcopy /y worldmanager\objects\ output\worldmanager\objects\ /E /I

copy /y worldmanager.json output\
windres src\main\cpp\windows\worldmanager.rc -o output\res.o
g++ -o output\worldmanager.exe src\main\cpp\windows\worldmanager.cpp output\res.o
del output\res.o

call cd output && worldmanager.exe && exit