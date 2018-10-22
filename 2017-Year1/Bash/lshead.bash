#! /bin/bash

#lshead –list the first few lines of every file in a directory specified by the argument.
#This command should also allow options to either list the first n lines or the last n lines of the files.  
#Example command:  lshead – head 10 documents would list the first ten lines in every file in the documents directory.


#set variables 
###  lines  ###
headLines=5 # default lines 
tailLines=5 # default lines 

### arguments exceptions ###
totalTailArgs=0
totalHeadArgs=0

### others ### 
directory=0 # directory from users inputs
path=0 # all path finded using "find"	
head=false
tail=false		
errorExit()
{	
	#
	#	exit with error echo
	#
	echo -e $1
	exit
}
					
isdigit()
{
	#
	#	check if value is digit and return true/false
	#	:parameter: - 1 variable
	#
	if ! [[ "$1" =~ ^[0-9]+$ ]]; then
		echo -1
	else
		echo 0
	fi
}

output()
{
	#
	# :function: Find a path where is the file  and prints lines. 
	#
	path=$(findDirectory) # finds path if directory has been set 
	index=0
	for file in $(find $path -not -path '*/\.*' -type f ); do # for every file in directory recursivelly 
		echo -e "\n\t==>\t"$file"\t<==\n"
		if [[ "$head" = true ]]; then # if -head used print top lines
			echo -e "Top $headLines lines: _________ "
			head -n $headLines $file
		fi
		if [[ "$tail" = true ]]; then # if -tail used print last lines
			echo ""
			echo -e "Last $tailLines lines: _________"
			tail -n $tailLines $file
		fi

	done

}
findDirectory()
{
			#find directories
				### Working principle ###
				### 1. Checks if directory exists
				### 2. If exists more then one, uses -maxdepth 1 to look for top file only
				### 3. If non exists = terminates program
				### 4 . If just 1 exists = returns path	
	dirExists=$(find ~ -not -path '*/\.*' -wholename "*$directory" | wc -l) # found directory
	dirPath=$(find ~ -not -path '*/\.*' -wholename "*$directory")
	if [[ $dirExists -gt 1 ]]; then
		dirExists=$(find ~ -maxdepth 1 -not -path '*/\.*' -wholename "*$directory"| wc -l)
		dirPath=$(find ~ -maxdepth 1 -not -path '*/\.*' -wholename "*$directory")
		if [[ $dirExists -gt 1 ]]; then				
			errorExit "lshead: $dirExists directories found called \"$directory\"\nPlease be more specific f.e - /home/bin"
		fi
	fi	
	if [[ $dirExists -eq 0 ]]; then
		errorExit "lshead: no directory found. Try go to place where directory you are looking for is located"
	elif [[ $dirExists -eq 1 ]]; then
		echo $dirPath
	fi
}
argumentsAmount()
{
			# Calculates totalArguments for each -head / -tail / -ht
			### if argument has been already used - returns false
			### if argument hasn't been used, increase the value, so next time it will be used it will return -false
#$1 = variable
	if [[ $1 -ge 1 ]]; then # if command exeeded, return false
		echo -1
	else  # if command not exeeded increase value to +1
		echo $(($1 + 1))
	fi

}

POSITIONAL=()

while [[ $# -gt 0 ]]; do # while there is arguments
	arg="$1"
	case $arg in
	-head)
		shift # shift the -head argument
		totalHeadArgs=$(argumentsAmount $totalHeadArgs) # checks if arguments has not been exeeded
			if [[ $totalHeadArgs == -1 ]]; then # if previuos function returned false (command used more then once) terminate
				errorExit "lshead: all commands can be used once only!"
			fi
		defaultValue=$(isdigit "$1") # checks if it is digit
		if [[ $defaultValue == 0 ]]; then 
			headLines="$1" # sets to users argument
			head=true
			shift
		else head=true		# leaves to default
		fi
	;;
	-tail)
		shift # shift the -tail argument
		totalTailArgs=$(argumentsAmount $totalTailArgs) # checks if arguments has not been exeeded
		if [[ $totalTailArgs == -1 ]]; then
			errorExit "lshead: all commands can be used once only!"
		fi
		defaultValue=$(isdigit "$1")
		if [[ $defaultValue == 0 ]]; then 
			tail=true
			tailLines="$1"
			shift
		else tail=true
		fi
	;;
	*) # if just directory specified
		directory="$1" # just set current argument to directory
		if [[ "$head" = false && "$tail" == false ]]; then
				head=true
		fi
		shift
	;;
	esac
done
	output # outputs 
