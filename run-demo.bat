@echo off
setlocal
chcp 65001 > nul
cd /d "%~dp0"

echo ===================================================
echo   FastSSML Showcase Demo
echo ===================================================
echo.

echo [1/2] Building FastSSML...
call mvn clean package -DskipTests -q
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] FastSSML build failed!
    pause
    exit /b %ERRORLEVEL%
)

echo [2/2] Building and Launching Demo...
cd examples\Demo
call mvn compile -DskipTests -q
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Demo compilation failed!
    cd ..\..
    pause
    exit /b %ERRORLEVEL%
)

java -cp "target\classes;..\..\target\classes;..\..\target\*" fastssml.demo.Demo

cd ..\..
pause