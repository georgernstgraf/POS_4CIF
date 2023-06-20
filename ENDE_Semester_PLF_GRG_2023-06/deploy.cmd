echo off
chcp 65001
SETLOCAL ENABLEDELAYEDEXPANSION

SET BUILD_DIR=build
SET EXAM_TARGET=C:\school\exam\%BUILD_DIR%
SET CURRENT_DATE=%DATE:~-4%-%DATE:~-7,2%-%DATE:~-10,2%
SET CURRENT=%CD%
for %%I in (.) do set PROJECT_NAME=%%~nxI
SET TARGET=%CURRENT%/%BUILD_DIR%/%PROJECT_NAME%
SET TARGET_DOC=%TARGET%/doc
SET STUDENT=%CURRENT%/student
SET STUDENT_SRC=%CURRENT%/student/src
SET PDF_FILE=%PROJECT_NAME%.pdf
SET PDF_FULL=%CURRENT%/doc/%PDF_FILE%

SET "ZIPPER_PATH=C:\Program Files\GRG-Tools\bro-tools\assets\zipper"


SET CLEAN=%1
if "%CLEAN%"=="c" (
    echo CLEAN AKTIVE!!!!!!!
    SET CLEANING_TARGET=%TARGET%
    CALL :clean_target %CLEANING_TARGET%
)


if NOT EXIST %TARGET% CALL :create_target %TARGET%





:: generate ------------------------------------------------------------------------------------------------------------
:generate

echo create build folder

echo copy src folder %STUDENT_SRC%  %TARGET%
xcopy "%STUDENT%" "%TARGET%" /i /s /e

echo mkdir doc folder %TARGET_DOC%
mkdir "%TARGET_DOC%"

echo copy zipper resources
xcopy "%ZIPPER_PATH%" "%TARGET%" /i /s /e

echo copy pdf document
copy "%CURRENT%\doc\%PDF_FILE%" "%TARGET_DOC%"
GOTO :deploy_exam


GOTO:EOF
::----------------------------------

:deploy_exam
SET BUILD_DIR=%TARGET%
SET DEPLOY_TARGET=%EXAM_TARGET%
cmd /wait /c bro zip "%BUILD_DIR%" -o "%DEPLOY_TARGET%" -i %BUILD_DIR%/.zipignore
REM call explorer "%DEPLOY_TARGET%"
GOTO :EOF

:: clean target folder
:clean_target ----------------------------------------------------------------------------------------------------------
SETLOCAL
SET CURRENT=%CD%
if NOT "%CLEANING_TARGET%"=="%CURRENT%" (
    echo cleaning target folder=%CLEANING_TARGET%
    pause
    del /s /q "%CLEANING_TARGET%"
    rmdir /s /q "%CLEANING_TARGET%"
)
GOTO :EOF


:: create target folder
:create_target ---------------------------------------------------------------------------------------------------------
SETLOCAL
SET TAR=%1
echo create target folder=%TARGET%
mkdir "%TARGET%"
GOTO :EOF



:EOF