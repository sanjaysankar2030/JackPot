@echo off

REM Change to the src directory
cd E:\Projects\Bpe_Java\src

REM Compile Java files and output class files to the classes directory
javac -d ..\classes Main.java Tokenizers\BpeTokenizer.java

REM Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b
)

REM Change to the classes directory
cd ..\classes

REM Run the main class (specifying the package)
echo -------------------------------------------------
java main.Main
echo -------------------------------------------------

pause

REM Delete all generated .class files
echo Cleaning up class files...
del /s /q *.class

REM (Optional) remove empty directories after deletion
for /d %%x in (*) do rd /s /q "%%x"

REM Return to the src directory (optional)
cd ..\src
