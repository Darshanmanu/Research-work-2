import ast

FileReader=open("D:\\research 2\\Ananth\\tweets.txt",encoding="utf-8").read()
count=0
for data in FileReader.split("\n"):
    d = ast.literal_eval(data)
    question = d['query']
    print(question)
    count = count + 1


# import json
#
# data=json.load(open("D:\\research 2\\Data\\V1 Dataset\\train_v1.1.json"))
# print(data['query'])