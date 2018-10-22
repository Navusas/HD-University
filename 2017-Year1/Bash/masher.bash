#! /bin/bash

###
### Program that counts Aa-Zz , numbers  and other characters
###

### Initialize variables
totalLetters=0
totalNumbers=0
totalCharacters=0
terminatedPoint=0
spaces=0
terminated=false

increase()
{
	## Increase variable by 1
	## takes varaible as parameter
	echo $(($1 + 1))	
}
containsTermination()
{
	## determines whether string contains terminate symbol *
	## :parameter: - string
	if [[ $1 == *"*"* ]]; then 
		echo false
	else 
		echo true
	fi
}
userTerminated()
{
	clear
	## determines whether user terminates the string 
	## if not - adds it to main string
	## :parameter: - main string
	loop=$(containsTermination $string)
	while [[ "$loop" == true ]]; do
		echo -e "Current string to mash is: $string"
		echo -e "Enter additional string or a '*' to exit"
		echo -e "Entering a '*' anywhere in the string will terminate the script"
		read tmpString
		string+=$tmpString
		loop=$(containsTermination $string)
		clear
	done

}
isEmpty()
{
	clear
	## determines whether string is terminated
	## :parameter: - main string
	while [[ ${#string} -le 0 ]]; do
		echo -e "\"masher\" requires single argument"
		echo -e "Entering a '*' anywhere in the string will terminate the script"
		printf "String to mash:"
		read tmpString
		string+=$tmpString
		clear
	done
}

output()
{
	clear
	echo -e "Terminated at position no: $terminatedPoint\n"
	echo -e "String =>\t$string\t<= contains $((${#string} - 1)) elements\n"
	echo -e "Number of spaces: $spaces"
	echo -e "Number of digits: $totalNumbers"
	echo -e "Number of letters: $totalLetters"
	echo -e "Number of other characters: $totalCharacters"
	echo -e "Number of characters mashed before control value: $terminatedPoint"
}

containsStar()
{
	# takes main string as parameter
	# checks if " *" has been used (Space + star) , so that it could give except state
	if [[ $(grep -o " *" <<< "$1" | wc -l) -ge 1 ]]; then ## need to except " *" case
		echo "masher: InputError - space before * found"
		exit
	fi
}


# 		__name__ = '__main__'
string+=$@ # set string to argumentss

isEmpty "$string" # check if string is empty
containsStar "$string" # check if user terminates program by putting * into it. 
userTerminated "$string" # check if string has * in it

for i in $(seq 1 ${#string});
do
	letter=${string:i-1:1} # takes character from string
	if [[ "$letter" == "*" && "$terminated" = false ]]; then
			terminatedPoint=$(($i-1))
			terminated=true
	elif [[ "$letter" == [Aa-Zz] && "$terminated" = false ]]; then
		totalLetters=$(increase $totalLetters)
	elif [[ "$letter" == [0-9] && "$terminated" = false ]]; then
		totalNumbers=$(increase $totalNumbers)
	elif [[ "$letter" == " "  && "$terminated" = false ]]; then
		spaces=$(increase $spaces)
	elif [[ "$terminated" = false ]]; then
		totalCharacters=$(increase $totalCharacters)
	fi
done

output

