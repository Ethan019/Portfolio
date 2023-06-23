import hashlib, uuid


# read the hash values you need to crack one by one:
hashvalues = open("finalhas.txt", 'r')
for hashvalue in hashvalues.readlines():
    hashvalue = hashvalue.strip('\n')
    temp = hashvalue.split(':')

    salt = temp[1]
    hashvalue = temp[0]
    salt2 = temp[1]
    hashvale2=temp[0]
    print(hashvalue)

    # read each password from the passwordList.txt.
    passlist = open("passList.txt", 'r')
    for password in passlist.readlines():

        password = password.strip('\n')



        #print(password)
        #convert each common password to different hash values.
        #you have to reverse password+salt if the salt comes before the hash in the text file

        hashguess_md5 = hashlib.md5(bytes(password+ salt, 'utf-8')).hexdigest()
        hashguess_sha1 = hashlib.sha1(bytes(password +salt, 'utf-8')).hexdigest()
        hashguess_sha224 = hashlib.sha224(bytes(password + salt, 'utf-8')).hexdigest()
        hashguess_sha256 = hashlib.sha256(bytes(password + salt, 'utf-8')).hexdigest()
        hashguess_sha512 = hashlib.sha512(bytes(password + salt, 'utf-8')).hexdigest()



        # compare the hash values to find the clear password.
        if hashguess_md5 == hashvalue:
            print("[+] The Password is: " + str(password))
            #print(hashguess_md5)
            break
        elif hashguess_sha1 == hashvalue:
            print("[+] The Password is: " + str(password))
            #print(hashguess_sha1)
            break
        elif hashguess_sha224 == hashvalue:
            print("[+] The Password is: " + str(password))
            break
        elif hashguess_sha256 == hashvalue:
            print("[+] The Password is: " + str(password))
            break
        elif hashguess_sha512 == hashvalue:
            print("[+] The Password is: " + str(password))
            break
