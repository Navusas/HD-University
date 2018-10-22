#! /bin/bash
# chmx – a command that takes filenames as its arguments and makes those files executable to all users.

error_exit()
{
	echo -e $1
	exit
}

findFile()
{
    ####
    ####    finds the file , its path
    ####    :return: returns files path
	file="$1"
	fileAmount=$(find . -type f -not -path '*/\.*' -wholename "*$file" | wc -l) # find file avoiding hidden files
	filePath=$(find . -type f -not -path '*/\.*' -wholename "*$file") # find path avoiding hidden files
	if [[ $fileAmount -gt 1 ]]; then # if more then one file found
		fileAmount=$(find . -maxdepth 2 -not -path '*/\.*' -wholename "*$file" | wc -l) # find file within current folder only
		filePath=$(find . -maxdepth 2 -not -path '*/\.*' -wholename "*$file" ) # find path within current folder only
		if [[ $fileAmount -gt 1 ]]; then			# if still more then one file found - exit
			error_exit "chmx: $fileAmount files found called \"$file\"\nPlease be more specific f.e - /home/bin"
		fi
	fi	

	if [[ $fileAmount -eq 0 ]]; then # if no files found - exit
		echo "chmx : no file found. Try go to place where file you are looking for is located"
	elif [[ $fileAmount -eq 1 ]]; then # if exactly 1 file found / return path 
		echo $filePath
	fi

}
##    __name__ = '__main__'

POSITIONAL=()
	

while [[ $# -gt 0 ]]; do # while there is at least 1 argument after script
	tmpFile="$1" # set that argument to variable
	if chmod +x $(findFile "$1") &>> .chmxError.log; then # try to call chmod function,, if error occurs store it into .chmxError.log file
		echo "chmx: permission succesfully set to execute file \""$tmpFile"\" to all users"
	else 
        rm $PWD/.chmxError.log # delete that file
        echo "chmx: no file found. Try go to place where file you are looking for is located" # if no file found
	fi
	shift # shift to next element
done


