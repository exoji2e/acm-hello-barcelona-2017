#!/bin/bash
for file in *.in; do
    echo $file
    python3 ../K.py < $file >> Kgen.py
done
    
