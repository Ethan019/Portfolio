import hashlib

#This program creates hashes for the differnet hash values and ouputs their value based on user input. 

# including SHA1, SHA224, SHA256, SHA384, and SHA512, as well as MD5 algorithm


# MD5:
hashvalue = input("* Enter a string to hash:")
#hashvalue = '33333333'
hash_handle_1 = hashlib.md5()
hash_handle_1.update(hashvalue.encode())
print("MD5 Hash is: ")
print(hash_handle_1.hexdigest())

# sha-1:
hash_handle_2 = hashlib.sha1()
# update: Update the hash object with the bytes-like object.
# Byte objects are in machine readable form internally, Strings are only in human readable form.
# string.encode(): It returns an utf-8 encoded version of the string
hash_handle_2.update(hashvalue.encode())

# return the digest as a string object of double length, containing only hexadecimal digits.
# the hash value is a hexadecimal string.
print("SHA-1 Hash is: ")
print(hash_handle_2.hexdigest())

# sha-224:
hash_handle_3 = hashlib.sha224()
hash_handle_3.update(hashvalue.encode())
print("SHA-224 Hash is: ")
print(hash_handle_3.hexdigest())

# sha-256:
hash_handle_4 = hashlib.sha256()
hash_handle_4.update(hashvalue.encode())
print("SHA-256 Hash is: ")
print(hash_handle_4.hexdigest())

# sha-512:
hash_handle_5 = hashlib.sha512()
hash_handle_5.update(hashvalue.encode())
print("Sha-512 hash is: ")
print(hash_handle_5.hexdigest())
