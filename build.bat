:: For Windows only

@echo off

rem worldmanager Build Script
rem Use this to build the project instead of running mvn package

echo worldmanager Build Script
echo Building project...
rmdir /s /q output
call mvn package -f "pom.xml"
jlink --add-modules java.base,java.desktop,java.net.http,jdk.crypto.cryptoki --output output --no-man-pages --no-header-files

set MAVEN_REPO_DIR=%userprofile%\.m2\repository\
set LIBRARIES=output\worldmanager\objects\main\1
set HASH_RESULT=""
set FILE="json-20240303.jar"



mkdir output\worldmanager
move /y target\worldmanager.jar output\worldmanager

mkdir %LIBRARIES%
copy /y %MAVEN_REPO_DIR%\org\json\json\20240303\json-20240303.jar %LIBRARIES%
copy /y %MAVEN_REPO_DIR%\com\github\Querz\NBT\6.1\NBT-6.1.jar %LIBRARIES%



copy /y worldmanager.json output\
g++ native\windows\worldmanager.cpp -o output\worldmanager.exe
set FILE="json-20240303.jar"
call :HASH_FILE

set FILE="NBT-6.1.jar"
call :HASH_FILE


:HASH_FILE
for /f %%a in ('certutil.exe -hashfile "%LIBRARIES%\%FILE%" SHA256 ^| findstr /i /v "SHA256 hash of version:"') do set "HASH_RESULT=%%a"
rename %LIBRARIES%\%FILE% %HASH_RESULT%".jar"
exit /b
