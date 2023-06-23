# Ethan Massingill
# CSCI 236
# 10/11/2021
# Program - Benfords Law (word counting part)
# Solved in about 4 hours, hit a bug with Odyssey text file that caused longer time spent
# B version
# For some reason Odyssey was throwing a "UnicodeDecodeError" so I had to add "encoding='utf-8" so that it was able to read the file.
# Runs well but added slight deviasion to file output for odyssey_counts.txt, it added the "encoding="utf-8" so the first paragraph reads 10 words instead of 9
def main():
    #reads in file
    file =open("odyssey.txt", encoding="utf-8")
    para =file.readlines()
    paraCounts=[]
    count =0
    #loop for checking if its a paragraph or not
    for line in para:
        #takes out all the white space from data
        lineCheck = line.split()
        #nested if statement for adding final count to list if it finds a new paragraph, but only if the count is not 0
        if lineCheck == []:
            if count!=0:
                paraCounts.append(count)
            count =0
        #adding each lines word count to the total for that paragraph
        else:
            count = count + len(lineCheck)
    #creating the odyssey file with a loop
    f = open("odyssey_counts2.txt", "w")
    for i in range(0, len(paraCounts)):
        f.write(str(paraCounts[i])+"\n")
    f.close

main()