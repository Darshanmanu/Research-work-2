import nltk

FileReader_Entity=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\EntityUnSelPassage.txt",encoding="utf-8").read()
FileReader_Person=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\PersonUnSelPassage.txt",encoding="utf-8").read()
FileReader_Descript=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\DescriptUnSelPassage.txt",encoding="utf-8").read()
FileReader_Location=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationUnSelPassage.txt",encoding="utf-8").read()
FileReader_Numeric=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\NumericUnSelPassage.txt",encoding="utf-8").read()


def callFunction(Reader):
    Sentence = ''
    for data in Reader.split('\n'):
        Sentence = Sentence + data;
    count=0
    count2=0
    Sentence_List = nltk.sent_tokenize(Sentence)
    for sent in Sentence_List:
        if len(sent) >= 10:
            count=count+1
        else:
            count2=count2+1
    print("Else Part :",count2)
    return count


print("Entity Type :",callFunction(FileReader_Entity))
print("Person Type:",callFunction(FileReader_Person))
print("Location Type:",callFunction(FileReader_Location))
print("Description Type:",callFunction(FileReader_Descript))
print("Numeric Type:",callFunction(FileReader_Numeric))


