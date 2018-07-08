FileReader_Entity=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\EntityFinPassage.txt",encoding="utf-8").read()
FileReader_Person=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\PersonFinPassage.txt",encoding="utf-8").read()
FileReader_Descript=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\DescriptFinPassage.txt",encoding="utf-8").read()
FileReader_Location=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationFinPassage.txt",encoding="utf-8").read()
FileReader_Numeric=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\NumericFinPassage.txt",encoding="utf-8").read()

def callFunction(Reader,name):
    File_writer=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\"+name+"TrainPassage.txt",'w',encoding="utf-8")

    File_writer2 = open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\" + name + "TestPassage.txt", 'w',encoding="utf-8")
    count=0
    count2=0
    for read in Reader.split('\n'):
        if count > 2500:
            File_writer2.write(read+"\n")
            File_writer2.flush()
            count2=count2+1
        else:
            File_writer.write(read+"\n")
            File_writer.flush()
            count = count + 1

    print("Completed Writing ",name)
    print("Count :",count)
    print("Count2 :",count2)
    File_writer.close()
    File_writer2.close()


callFunction(FileReader_Entity,'Entity')
callFunction(FileReader_Person,'Person')
callFunction(FileReader_Numeric,'Numeric')
callFunction(FileReader_Location,'Location')
callFunction(FileReader_Descript,'Descript')