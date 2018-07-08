import ast

FileReader=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Location.txt",encoding="utf-8").read()
FileWriter=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationSelPassage.txt",'w',encoding="utf-8")
FileWriter2=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\LocationUnSelPassage.txt",'w',encoding="utf-8")

for Data in FileReader.split("\n"):
    d=ast.literal_eval(Data)
    sel=0
    unsel=0
    List=d['passages']
  #  print(List)
    for L in List:
        string=L['passage_text']
        if L['is_selected'] == 1:
            FileWriter.write(string+"\n")
        else:
            FileWriter2.write(string+"\n")
    FileWriter.flush()
    FileWriter2.flush()
