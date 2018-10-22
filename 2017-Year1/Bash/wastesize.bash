#! /bin/bash
#wastesize - which reports the number of files in the waste bin.
cd $HOME/bin/.waste
amount=$(find . -maxdepth 1| wc -l) # finds amount of files not recursively
allFiles=$(find . | wc -l) # finds all files recursively
totalSize=$(du -bh --summarize ) # list -a (all) -b(bytes) h(human readable format) within max-depth 1 only

echo "There is $(($amount-1)) (recursively $(($allFiles - 1))) files into Waste bin" # echo -1
echo "Waste bin size is - $totalSize"
