# Ethan Massingill
# CSCI 236
# 11/12/2021
# Program - Webserver
# 5 hours
# A
# I went back and studied some ways to implement templets and use loops inside the html code itself. It helped a ton with some youtube vidoes and I figured out the issues I was having. 

# After we dicussed templets in class I got the program working pretty well. It seems to work great on my end. 
 
from flask.scaffold import F
import requests
import os
from tqdm import tqdm
from bs4 import BeautifulSoup as bs
from urllib.parse import urljoin, urlparse
from flask import Flask, render_template, request
 
#starts the flask app
app = Flask(__name__)
 
 
#html code. Couldnt figure out the output for images

# let's make a URL validator, that makes sure that the URL passed is a valid one, as there are some websites that put encoded data in the place of a URL, so we need to skip those:
 
def is_valid(url):
	"""
	Checks whether `url` is a valid URL.
	"""
	parsed = urlparse(url)
	return bool(parsed.netloc) and bool(parsed.scheme)
 
# the core function that grabs all image URLs of a web page
 
def get_all_images(url):
	"""
	Returns all image URLs on a single `url`
	"""
	print("get images from: " + url)
	soup = bs(requests.get(url).content, "html.parser")
	urls = []
	for img in tqdm(soup.find_all("img"), "Extracting images"):
		img_url = img.attrs.get("src")
		if not img_url:
			# if img does not contain src attribute, just skip
			continue
		# make the URL absolute by joining domain with the URL that is just extracted
		img_url = urljoin(url, img_url)
		# finally, if the url is valid then keep it
		if is_valid(img_url):
			urls.append(img_url)
	return urls
#the default homepage that searches. Methods is used by the html code to tell it what to do.	
@app.route("/", methods=["GET"])
def home():
    return render_template("HOME.html")
 
#This part of the code gets ran after the user inputs the url. It sends that url into the python code then returns the list for processing. 
@app.route("/", methods = ["POST"])
def imageOutput():
	# get all images for the request form of "url" found on the second template. 	
	imgs = get_all_images(request.form["url"])
	#this part takes the imgs list and passes it to the loop found in IMAGES.HTML for output. It also can return NEW urls for processing the same loop again each time with the "URL" 2 paramater
	return render_template("IMAGES.html", url=request.form["url"], imgs=imgs)
	
 
#starts the local host
if __name__=="__main__":
     app.run(host="localhost", debug=True)