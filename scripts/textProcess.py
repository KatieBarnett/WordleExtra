import re
for wordLength in range(10):
	with open('wordfiles/words_' + str(wordLength+1) + '_letters.txt', 'w') as outputFile:
		with open('wordfiles/engmix.txt','r', encoding='unicode_escape') as inputFile:
		    for line in inputFile:
		    	strippedLine = line.strip()
		    	if len(strippedLine) == wordLength+1 and re.match('([a-z]+)$', strippedLine):
		    		outputFile.write(strippedLine + '\n')