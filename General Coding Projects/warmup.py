# Ethan Massingill
# CSCI 236
# 8/25/2021
# Program - Warmup
# Solved in 20 minutes, 1 hour total spent trying recursion 
# This does not have one
# when trying to use recursion in the curzonCheck method ran into stack limit
# runs fine

#prompts user for integer input
user_num = int (input())

#method for handeling user num
def curzonCheck(user_num):
    #if curzon return True
    if((pow(2,user_num)+1) % ((2*user_num)+1)==0):
        return True

#while loop that prompts every time user enters a number !=0
#Terminates if 0 is inputed
while user_num != 0:
    #cumlative sum of all curzon's found inside range 0-user_num, starts at 0
    curzon_sum = 0
    #check for non negative number
    if user_num >=0:
        #loop that takes every number in the range and checks if its a curzon. 
        while user_num>0:
            #if curzon is found khbnvbnhkdtfyiugoits added to curzon_sum, then goes to next number in range
            if curzonCheck(user_num)==True:
                curzon_sum+=user_num
                user_num = (user_num-1)
            #if curzon is not found moves to next number    
            else:
                user_num= (user_num-1)
        #after loop is finished print the culmative curzon number
        print(curzon_sum)
    #if input is negative output error message
    else:
        print("input not valid")
    #inputs new user input
    user_num = int (input())    