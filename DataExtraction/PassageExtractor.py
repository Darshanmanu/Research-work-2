import nltk

FileReader_Entity=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\EntityUnSelPassage.txt",encoding="utf-8").read()
FileReader_Person=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\PersonUnSelPassage.txt",encoding="utf-8").read()
FileReader_Descript=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\DescriptUnSelPassage.txt",encoding="utf-8").read()
FileReader_Location=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationUnSelPassage.txt",encoding="utf-8").read()
FileReader_Numeric=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\NumericUnSelPassage.txt",encoding="utf-8").read()

def callFunction(Reader,name):
    File_writer=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\"+name+"FinUnPassage.txt",'w',encoding="utf-8")
    Sentence=''
    for data in Reader.split('\n'):
        Sentence=Sentence+data
    Sentence_List=nltk.sent_tokenize(Sentence)
    count=0
    for sen in Sentence_List:
        if count > 3500:
            break
        if len(sen) >= 10:
            File_writer.write(sen+"\n")
            File_writer.flush()
            count=count+1

    print("Completed Writing ",name)
   # print("Count :",count)
    File_writer.close()


callFunction(FileReader_Entity,'Entity')
callFunction(FileReader_Person,'Person')
callFunction(FileReader_Numeric,'Numeric')
callFunction(FileReader_Location,'Location')
callFunction(FileReader_Descript,'Descript')