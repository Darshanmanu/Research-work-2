import nltk
import random
from nltk.classify import naivebayes
from sklearn.naive_bayes import MultinomialNB, BernoulliNB
from sklearn.linear_model import LogisticRegression, SGDClassifier
from sklearn.svm import SVC,LinearSVC
from nltk.classify.scikitlearn import SklearnClassifier
from statistics import mode
from nltk.classify import ClassifierI
import time


File_Desc_Reader=open("D:\\research 2\\Data\\V1 Dataset\\V1 FInal\\EntityTrainPassage.txt",encoding='utf-8').read()
File_Desc_Reader2=open("D:\\research 2\\Data\\V1 Dataset\\V1 FInal\\EntityTrainUnPassage.txt",encoding='utf-8').read()


class VoteClassifier(ClassifierI):
    def __init__(self, *classifiers):
        self._classifiers = classifiers

    def classify(self, features):
        try:
            votes = []
            for c in self._classifiers:
                v = c.classify(features)
                votes.append(v)
            return mode(votes)
        except:
            return 'ExceptGood'

    def confidence(self, features):
        votes = []
        for c in self._classifiers:
            v = c.classify(features)
            votes.append(v)

        choice_votes = votes.count(mode(votes))
        conf = choice_votes / len(votes)
        return conf


Main_Data = []

for reader1 in File_Desc_Reader.split('\n'):
    Main_Data.append((reader1,'Good'))
for reader2 in File_Desc_Reader2.split('\n'):
    Main_Data.append((reader2,'Bad'))

random.shuffle(Main_Data)

all_words_list = []

Good_words = nltk.word_tokenize(File_Desc_Reader)
Bad_words = nltk.word_tokenize(File_Desc_Reader2)


for w1 in Good_words:
    all_words_list.append(w1.lower())

for w2 in Bad_words:
    all_words_list.append(w2.lower())


all_words_list = nltk.FreqDist(all_words_list)
Freq_words=list(all_words_list.keys())[:4000]


def feature_extraction(document):
    f_words = nltk.word_tokenize(document)
    features = {}
    for w in Freq_words:
        features[w] = (w in f_words)
    return features


Review_Document = [(feature_extraction(review),category) for (review,category) in Main_Data]
Train_data = Review_Document[:4000]


def testdocumenttester(Test_data):

    print("Evaluating Linear SVC Classifier... ")
    start = time.time()
    classifier6 = SklearnClassifier(LinearSVC())
    classifier6.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    print("Evaluating Logistic Regression Classifier...")
    start = time.time()
    Logistic_regression_classifier = SklearnClassifier(LogisticRegression())
    Logistic_regression_classifier.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    print("Evaluating Naive Bayes  Classifier..")
    start = time.time()
    Naive_classifier = naivebayes.NaiveBayesClassifier.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    print("Evaluating Multinomial Classifier")
    start = time.time()
    Multi_Classifier = SklearnClassifier(MultinomialNB())
    Multi_Classifier.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    print("Evaluating SDG Classifier")
    start = time.time()
    SGD_Classifier = SklearnClassifier(SGDClassifier())
    SGD_Classifier.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    print("Evaluating Bernoulli Classifier")
    start = time.time()
    BernoulliNB_Classifier = SklearnClassifier(BernoulliNB())
    BernoulliNB_Classifier.train(Train_data)
    end = time.time()
    t_min = (end - start)
    print("Complete successfully...", t_min, "secs")

    Voter_Classifier = VoteClassifier(classifier6, Logistic_regression_classifier, Naive_classifier, Multi_Classifier,
                                      SGD_Classifier, BernoulliNB_Classifier)

    count = 0
    uncount = 0
    conf_uncount = 0
    calsen = Test_data.split('\n')
    total = len(calsen)
    st = 0
    for sentence in Test_data.split('\n'):
        feature = feature_extraction(sentence)
        vote_result = Voter_Classifier.classify(feature)
        if vote_result != 'ExceptGood':
            confidence = Voter_Classifier.confidence(feature)
            if confidence >= 0.6 and vote_result == 'Good':
                count = count + 1
            else:
                if vote_result == 'Good':
                    count = count + 1
                else:
                    uncount = uncount + 1
        else:
            count = count + 1
        st = st + 1
        per = (st / total) * 100
        per2 = int(per)
        print('\rCompleted....', per2, '%', end='', flush=True)

    print("\n\t\t\tAccuracy Status For Entity\t\t\t")
    print("Number of Statements:\t\t\t", (count + uncount))
    print("Number of Good Answers(conf >= 0.6):\t\t\t", count)
    print("Number of Bad Answers:\t\t\t", uncount)
    print("Confidence Level:\t\t\t>0.6(60%)")
    print("Total Accuracy:\t\t\t", count / (count + uncount) * 100)
