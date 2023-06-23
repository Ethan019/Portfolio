# Ethan Massingill
# CSCI 236
# 10/20/2021
# Program - Recommender
# Took 3 days working on and off for certain functions. Had issues with the Recomendation part (using dot product) and sorting using Lamda (looked up how to sort this way and found it super helpful)
# N/a
# Issues ran into - Ran into several issues and needed many print statements to figure out what the code was doing. Also handeled a key/value error that was fixed. The average compasrision using dot com was hard to write but I was able to finish it. 
# Runs pretty well. Think I could have maybe combined some functions but tryed to make it as readable as possible. 
import sys  

def main():
    if (len(sys.argv) != 2): 
        #If the wrong arguments are passesd in the program quits
	    print("USAGE: python recommender.py filename") 
	    quit() 

    #opens the file
    ratingsFile = open(sys.argv[1],"r") 
    #passes in the file into lines to be read by the program
    lines = ratingsFile.readlines()
    #Builds set of book then returns it as a list from the file
    books = fileBooks(lines)

    #these are the driver functinos that handle the inital computations before the user promppts are listed. This builds the averages for all books aonce along with building the dictionary of users, and list of books. 
    #creates list of users 
    users =fileUsers(lines)
    #builds dictionary of books with 0 values for the length of all books first
    ratings = ratingsDict(users, books)
    #then adds in the user ratings for each book at that books index from the book list(this is why the books list has to be created first)
    ratings = updateR(ratings, lines, books)
    #takes the user ratings and book list then passes in the function to get the averages for each book
    bookAvg=(averages(ratings, books))
    #sorts the averages (this lamda function was hard to get correct. Took about an hour messing with it to get it where its suppose to be)
    bookAvg.sort(key=lambda x:x[1], reverse=True)

    #this is the main prompt part for the user first prints commands for user to enter
    print("Welcome to the CSCI 236 Book Recommender. Type the word in the left column to do the action on the right.")
    print("recommend : recommend books for a particular user")
    print("averages  : output the average ratings of all books in the system")
    print("quit      : exit the program")   
    while True:
        #passes in inputs (makes sure they are the correct case) 
        # if quit is passed in program stops
        inp = input("next task? ").lower()
        if inp =='quit':
            print()
            break
        #this statment displays all averages
        elif inp =='averages':
            displayAvg(bookAvg)   
        elif inp =='recommend':
            #asks for the user to recomend by. If user is not found in the keys then it displays the book averages for all books
            user = input("user? ")
            if user not in ratings.keys():
                displayAvg(bookAvg)
               
            else:
                #this computes the similarity factor of passed in user, then sorts them based on high scores. Then passes in this value to find the top averages of non negative user scores
                sim=similarFactor(user, ratings)
                sim.sort()
                top = topAverage(sim, ratings, books)
                #displays a list of books to what the user wants. 
                displayR(top)
        print()
#fucntion for reading in books from file list 
# it returns a list of books (to be used later)          
def fileBooks(lines):
    books =set()
    for lineCount in range(0, len(lines)-1, 3):
        books.add(lines[lineCount+1].rstrip())
    return list(books)

#builds the set of users, returns them as a list(so it can be used)
def fileUsers(lines):
    users = set()
    for lineCount in range(0, len(lines)-1, 3):
        users.add(lines[lineCount].rstrip())
    return list(users)    

#this builds and dictionary of size *len(books) and populates the key's(names) value as a list of zeros
#it returns the default list to be updated by updateR()
#it passes in users and books passed made from the functions on line 71, and 64
def ratingsDict(users, books):
    ratingsDict = dict.fromkeys(users, None)
    for line in range(0, len(users)):
        key = users[line]
        ratingsDict[key]=[0]*len(books)
    return ratingsDict

#this updates the dictionary of user scores for each book at THAT books index.(this was hard to write). 
# It then returns the updated populated list of user ratings
def updateR(ratings, lines, books):
     for lineCount in range(0, len(lines)-1, 3):
        k = lines[lineCount].strip()
        rat = int(lines[lineCount+2].strip())
        i = books.index(lines[lineCount+1].strip())
        for keys in ratings.keys():
            if keys ==k:
                tempL =ratings.get(keys)
                tempL[i]=rat
                break
            else:
                continue
     return ratings
#uses for loop to check every book in books for their average. It calls the computeBookAvg() function to find the average of the current book
# it returns a list of averages 
def averages(ratings, books):
    avergs=[]
    for book in books:
        theAvg = (book, computeBookAvg(book, books, ratings))
        avergs.append(theAvg)
    return avergs
#this computes the average of given book for the passed in book users/books/rating in file
#returns the average that is THEN used by averages() under its for loop for all books
def computeBookAvg(book, books, raitings):
 total = 0
 count=0
 for i in raitings.keys():
     if raitings[i][books.index(book)] !=0:
         total +=raitings[i][books.index(book)]
         count = count+1
 return (total/count) 
#this is the dot prodcut function (this was the hardest to write for me)
#computes similarities for SimilarFactor function. for each user it scores them then returns this total so its stored in a list in SimilarFactor()
def dotProduct(a_user, b_user):
    tot =0
    for i in range(len(a_user)):
        tot +=a_user[i] * b_user[i]
    return tot
#this takes the user given by prompt and the entire list of user ratings
#it then calls the dotProduct function to compute the similarity of users
#then it passes this list to the function mostS
#on line 124 it makes sure the user that was passed from prompt is NOT passed into dotProduct(this keeps it from comparing to itself)
def similarFactor(user, ratings):
    similarList =[]
    for names in ratings.keys():
        if names !=user:
            #this calls the dot product function and the later passes it into mostS
            similarList.append((dotProduct(ratings[user], ratings[names]), names))
    return mostS(similarList)

#this is chained to similarFactor. It passes in the list from there to finally build list of most similar users to the user that was passed in.
#returns a list of similar users
def mostS(similarList):
    mostSi=[]
    for i in range(3):
        i = similarList.pop()
        mostSi.append(i[1])
    return mostSi

#this conputes the averages for the top similar users to give it a rating high for the users most similar. 
#passes in the list from function mostS which is a list of most similar users, along with the master list and book titles.
def topAverage(mostS, ratings, books):

    listRat = [0]*len(books)
    for i in range(len(listRat)):
        
        count =0
        total =0
        for name in mostS:
            if ratings[name][i]!=0:
                total+=ratings[name][i]
                count+=1
        if count!=0:
            listRat[i]=total/count
    return recomenB(listRat, books)

#this finally builds the list of books it recomends, returns as a list of tupiles. The number is how high the book is recomened for the user    
#it passes in the book list (titles) along with the list from the fucntion topAverages(). It is chained to that function so its only called by it. 
def recomenB(listRat, books):
    recomList=[]
    for i in range(0, len(books)):
        if listRat[i] >0:
            recomList.append((listRat[i],books[i]))
    return recomList

#both of these are print fucntions. The average for the recomended list is sorted but all averages is already sorted
#outputs print message formated to how the directions were given
#fist function handles the averages for all books list, second handles the print function for the list of recomendations
def displayAvg(bookAvg):
    for books in bookAvg:
        print(books[0], books[1])
def displayR(topAverage):
    topAverage.sort()
    for books in range(len(topAverage)):
        books = topAverage.pop()
        print(books[1], books[0])
main()