import sys
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, confusion_matrix, classification_report
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import time

#Name: ETHAN MASSINGILL
#Project: Senior Research Project


# Load the data
data = pd.read_csv('spam.csv')

#spam.csv code
#this is need only for spam.csv. The data originally said spam or ham for the label
# but I wanted to saw it to binary to make it easier to read

data['Label']=data['Category'].apply(lambda x:1 if x=='spam' else 0)
# Split the data into training and testing sets


#Uncomment below for datasets over than spam.csv
#X_train, X_test, y_train, y_test = train_test_split(data.Body, data.Label, test_size=0.2, random_state=30)
#spam.csv code
#splits between training and testing set. Randomly picks data from the dataset. test_size determies the % of data to be tested
X_train, X_test, y_train, y_test = train_test_split(data.Message, data.Label, test_size=0.4, random_state=70)

# Vectorize the email body using the CountVectorizer
#this is very important to get the data prepared correctly for the model.
#It is using bag of words technique
vectorizer = CountVectorizer()
X_train_vec = vectorizer.fit_transform(X_train).toarray()
X_train_vec = MinMaxScaler().fit_transform(X_train_vec)
X_train_vec = np.reshape(X_train_vec, (X_train_vec.shape[0], -1))
X_test_vec = vectorizer.transform(X_test).toarray()
X_test_vec = MinMaxScaler().fit_transform(X_test_vec)
X_test_vec = np.reshape(X_test_vec, (X_test_vec.shape[0], -1))


#START OF THE MODELS. FIRST IS ELM. Then it does some metric evaluations. Then NB model. Then metric evaluation. Finally, the metrics are processed.


# ***************THIS IS THE START OF THE ELM ***************

print("Starting ELM.....")
start_time_ELM = time.time()
#### START OF ELM MODEL DO NOT TOUCH ####
# Set up the neural network


###ELM STRUCTURE
###Requires a single forward fed dense neural network. Hidden_Size can be tuned to the dataset size for more accurate results. while at the cost of computer power.

#this is the set-up to define the neural network itself. The input size is the shape (size) of the training matrix.
#The hidden_size is the number of hidden neurons in the dense single layer
#the input weights are randomly determined based on the hidden size and input size)
#the biases are also randomly picked using np as defined by an elm
input_size = X_train_vec.shape[1]
hidden_size = 700
input_weights = np.random.normal(size=[input_size,hidden_size])
biases = np.random.normal(size=[hidden_size])
# Activation function. This is using sigmoid but can be change if needed

def sigmoid(x):
    return 1/(1+ np.exp(-x))

# Applies the hidden nodes with the input weights, input and dot product.
#then applies the biases
#finally runs through sigmoid activation

#This calculates is the input forward step
def hidden_nodes(X):
    G = np.dot(X, input_weights)
    G = G + biases
    H = sigmoid(G)
    return H
#This performs the actual output weight calculator based on data passed through the dense layer.
#Calculates on a np.dot and Moore-Penrose pseudoinverse (pinv) matrix inverse.
# Takes the word vector as X and the y training values.
#This is the crucial step of the model.
#******this is the only learning phase for the model******
output_weights = np.dot(np.linalg.pinv(hidden_nodes(X_train_vec)), y_train)

# This is the prediction function. It takes the number of hidden nodes as the X paramater, passes them to out.
# Then does a dot prodcut on out and output weights and then returns out.
def predict(X):
    out = hidden_nodes(X)
    out = np.dot(out, output_weights)
    return out

# Takes the model (called predict) and passes it through the test
prediction = predict(X_test_vec)


end_time=time.time()

# ***************END OF ELM MODEL*********************


#***********BEGIN OF EVALUATION OF ELM**************
elapsed_time=end_time-start_time_ELM
# Get the predicted label for each sample
train_labels = np.unique(y_train)
test_labels = np.unique(y_test)

#making sure that each label is valid before output
unique_labels = np.unique(y_test)
valid_labels = [label for label in unique_labels if np.sum(y_test == label) > 0]

#Uses sklearn's metrics for scoring calling each of them.
# Checks to make sure the label is either 0 or 1,
# the average is weighted and zero division error is handled
accuracy = accuracy_score(y_test, np.round(prediction).astype(int))
precision = precision_score(y_test, np.round(prediction).astype(int), labels=valid_labels, average='weighted', zero_division=1)
recall = recall_score(y_test, np.round(prediction).astype(int), labels=valid_labels, average='weighted', zero_division=1)
f1 = f1_score(y_test, np.round(prediction).astype(int), labels=valid_labels, average='weighted', zero_division=1)





#************END OF EVALUATION OF ELM**********



# **********************THIS IS THE START OF THE NAIVE BAYES MODEL ****************************


print("Starting NB Model....")
start_time_NB = time.time()
#### START OF NB MODEL DO NOT TOUCH ####
nb = MultinomialNB()
nb.fit(X_train_vec, y_train)

# Make predictions on the testing data
nb_predictions = nb.predict(X_test_vec)

# Make predictions on the test data

end_time_NB=time.time()

elapsed_time_NB=end_time_NB-start_time_NB
# Get the predicted label for each sample


# *******************END OF NB MODEL**********************






#*************BEGIN OF DATA EVALUATION METRICS + VISUALS*************

#This section first calculates the confusion matrixs for each model. Outputs them in a heat map.
# Outputs a bar chart comparing the 2. Then outputs a time bar chart.
#Parts of the print statements are here for error checking the bar charts. I left them in so it helps look at the models closer.



#*************START OF NB Evaulation metrics (First Confusion matrix, then time)******************

# Evaluate the model using various metrics
nb_accuracy = accuracy_score(y_test, nb_predictions)
nb_precision = precision_score(y_test, nb_predictions)
nb_recall = recall_score(y_test, nb_predictions)
nb_f1 = f1_score(y_test, nb_predictions)
nb_cm = confusion_matrix(y_test, nb_predictions)
nb_classification_report = classification_report(y_test, nb_predictions)

# Print the evaluation metrics
print("Naive Bayes Model Results: ")
print("*********************")
print(f"Time running: {elapsed_time_NB} seconds")
print("*********************")
num_spam = (data['Label'] == 1).sum()
num_not_spam = (data['Label'] == 0).sum()
print("Number of spam emails: ", num_spam)
print("Number of non-spam emails: ", num_not_spam)
print("*********************")
print("Metrics")
print("Accuracy:", nb_accuracy)
print("Precision:", nb_precision)
print("Recall:", nb_recall)
print("F1 Score:", nb_f1)

# Print the confusion matrix and classification report
print("Confusion Matrix:")
print(nb_cm)
print("Classification Report:")
print(nb_classification_report)


#This is the actual confusion matrix plotting using seaborn and matplotlib

cm = confusion_matrix(y_test, np.round(nb_predictions).astype(int), labels=valid_labels)
sns.heatmap(cm, annot=True, cmap='Reds', fmt='g')
plt.xlabel('Predicted')
plt.ylabel('True')
plt.title('Confusion Matrix')
plt.figure(figsize=(2, 2))

#Displaying confusion matrix
plt.show()

# *************** END OF NAIVE BAYS METRICS *********************




# **************START OF ELM METRICS Output(First confusion then time)**********

print("ELM Model Results: ")
print("*********************")
print(f"Time running: {elapsed_time} seconds")
print("Number of nodes used:", hidden_size)
print("*********************")
num_spam = (data['Label'] == 1).sum()
num_not_spam = (data['Label'] == 0).sum()
print("Number of spam emails: ", num_spam)
print("Number of non-spam emails: ", num_not_spam)
print("*********************")
print("Metrics")
print("Precision:", precision)
print("Recall:", recall)
print("F1 Score:", f1)

# Print accuracy for ELM
print("Accuracy:", accuracy*100)
#Print out of calssifaction report. (helpful for making sure confusion matrix is correct)
print(classification_report(y_test, np.round(prediction).astype(int),labels=valid_labels, zero_division=1))
#ELM confusion matrix from its predictions
cm = confusion_matrix(y_test, np.round(prediction).astype(int), labels=valid_labels)

sns.heatmap(cm, annot=True, cmap='Reds', fmt='g')
plt.xlabel('Predicted')
plt.ylabel('True')
plt.title('Confusion Matrix')
plt.figure(figsize=(2, 2))
#Showing confusion matrix for ELM
plt.show()

#*********END OF ELM METRICS OUTPUT**********




#**************START OF COMPARISON BAR CHARTS FOR POWERPOINT (Only the time chart is in the final research paper)***********

#BAR CHART FOR CONFUSION MATRIX COMPARISON

# Getting the mectrics in precent form
nb_metrics = [nb_accuracy*100, nb_precision*100, nb_recall*100, nb_f1*100]
elm_metrics = [accuracy*100, precision*100, recall*100, f1*100]

# put metric names on the bar chart
metric_names = ['Accuracy', 'Precision', 'Recall', 'F1 Score']

# bar width and height
bar_width = 0.35
bar_height = 20

# Setting up bar position and x axis
r1 = np.arange(len(nb_metrics))
r2 = [x + bar_width for x in r1]

# putting bars on the actual chart with colors
plt.bar(r1, nb_metrics, color='orange', width=bar_width, edgecolor='black', label='Naive Bayes', alpha =0.7)
plt.bar(r2, elm_metrics, color='blue', width=bar_width, edgecolor='black', label='ELM', alpha =0.7, )

# adding labels to the chart
plt.xlabel('Metrics')
plt.ylabel('Value (%)')
plt.xticks([r + bar_width/2 for r in range(len(nb_metrics))], metric_names)
plt.title('Confusion Matrix Score of Naive Bayes and ELM models')
plt.legend()

# Display bar chart for confusion matrix comparison
plt.show()

#*********** END OF CONFUSION MATRIX BAR CHART ********


#*****************START OF TIME BAR CHART *******************

# grabbing the time from above and assigning it to new values
nb_time = elapsed_time_NB
elm_time = elapsed_time

#Putting names of model on bar chart for time
time_names = ['Naive Bayes', 'ELM']

#bar chart width and height
bar_width = 0.35
bar_height = 5

#Putting the itmes correctly in the bar chart
r1 = np.arange(len(time_names))

# Plotting the bar chart time
plt.bar(r1, [nb_time, elm_time], color='purple', width=bar_width, edgecolor='black', alpha=0.7)

# Adding labels to the sides and top of the chart
plt.xlabel('Model')
plt.ylabel('Time (in seconds)')
plt.xticks([r for r in range(len(time_names))], time_names)
plt.title('Comparison of Naive Bayes and ELM running times')

#Showing time bar chart
plt.show()

#*************END OF BAR CHART FOR TIME ***********


#*************END OF DATA OUTPUT****************

sys.exit(0)

