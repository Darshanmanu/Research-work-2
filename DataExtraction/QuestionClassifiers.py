import nltk
import random
from DataExtraction import DescriptPassageExtractor,NumericPassageExtractor,PersonPassageExtractor,\
    LocationPassageExtractor,EntityPassageExtractor
import pickle
from nltk.classify import naivebayes
from sklearn.naive_bayes import MultinomialNB, BernoulliNB
from sklearn.linear_model import LogisticRegression, SGDClassifier
from sklearn.svm import SVC,LinearSVC
from nltk.classify.scikitlearn import SklearnClassifier
from statistics import mode
from nltk.classify import ClassifierI


Descrpit_sentence=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\DescriptQuestion.txt",encoding="utf-8").read()
Numeric_sentence=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\NumericQuestion.txt",encoding="utf-8").read()
Location_sentence=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationQuestion.txt",encoding="utf-8").read()
Entity_sentence=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\EntityQuestion.txt",encoding="utf-8").read()
Person_sentence=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\PersonQuestion.txt",encoding="utf-8").read()


def callFunction(MainDocument,document,tag):
    for doc in document.split('\n'):
        MainDocument.append((doc,tag))
    return MainDocument


def callWordsExtractor(total_words,words_list):
    for w in words_list:
        total_words.append(w.lower())
    return total_words

MainDocument=[]


MainDocument=callFunction(MainDocument,Descrpit_sentence,'Descript')
MainDocument=callFunction(MainDocument,Numeric_sentence,'Numeric')
MainDocument=callFunction(MainDocument,Location_sentence,'Location')
MainDocument=callFunction(MainDocument,Entity_sentence,'Entity')
MainDocument=callFunction(MainDocument,Person_sentence,'Person')

random.shuffle(MainDocument)

Descript_words=nltk.word_tokenize(Descrpit_sentence)
print("Des :",len(Descript_words))
Numeric_words=nltk.word_tokenize(Numeric_sentence)
print("Des :",len(Numeric_words))
Location_words=nltk.word_tokenize(Location_sentence)
print("Des :",len(Location_words))
Entity_words=nltk.word_tokenize(Entity_sentence)
print("Des :",len(Entity_words))
Person_words=nltk.word_tokenize(Person_sentence)
print("Des :",len(Person_words))

total_words=[]

total_words=callWordsExtractor(total_words,Descript_words)
total_words=callWordsExtractor(total_words,Numeric_words)
total_words=callWordsExtractor(total_words,Location_words)
total_words=callWordsExtractor(total_words,Entity_words)
total_words=callWordsExtractor(total_words,Person_words)

total_words=nltk.FreqDist(total_words)
Freq_words=list(total_words.keys())[:1500]


def find_features(document):
    f_words=nltk.word_tokenize(document)
    features={}
    for w in Freq_words:
        features[w]=(w in f_words)
    return features


print(len(MainDocument))


MainDocument=MainDocument[:5000]
featuresets = [(find_features(rev), category) for (rev, category) in MainDocument]

random.shuffle(featuresets)

# positive data example:
training_set = featuresets


def tell_me_the_answer(classifiers,document):
    ret_list = []
    for doc1 in document.split('\n'):
        ret_list.append(classifiers.classify(find_features(doc1)))
    return ret_list


Test_file=open("D:\\research 2\\Algorithms\\the-file-name1.txt",encoding="utf-8").read()
LinearSVC_classifier = SklearnClassifier(LinearSVC())
LinearSVC_classifier.train(training_set)
print("From Linear SVC\n")
Linear_svc_list=tell_me_the_answer(LinearSVC_classifier , Test_file)


Logistic_classifier = SklearnClassifier(LogisticRegression())
Logistic_classifier.train(training_set)
print("From Logistic Classifier")
logistic_list=tell_me_the_answer(Logistic_classifier , Test_file)

count = 0
value = 0
for linear in Linear_svc_list:
    list=[]
    if linear == Linear_svc_list[count]:
        list.append(linear)
    else:
        list.append(linear)
        list.append(Linear_svc_list[count])
    Test_file2=open("D:\\research 2\\Algorithms\\Queries\\File"+str(count)+"\\MyFile"+str(count)+".txt")
    for l in list:
        if l == 'Descript':
            DescriptPassageExtractor.testdocumenttester(Test_file2)
        else:
            if l == 'Numeric':
                NumericPassageExtractor.testdocumenttester(Test_file2)
            else:
                if l == 'Person':
                    PersonPassageExtractor.testdocumenttester(Test_file2)
                else:
                    if l == 'Location':
                        LocationPassageExtractor.testdocumenttester(Test_file2)
                    else:
                        EntityPassageExtractor.testdocumenttester(Test_file2)




# Multi_classifier = SklearnClassifier(MultinomialNB())
# Multi_classifier.train(training_set)
# print("From MultinomialNB")
# classifyTheSentence(Multi_classifier,Test_file)