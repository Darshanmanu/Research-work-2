import json
from pprint import pprint
import sys
import ast

data=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\NewJson.json",encoding="utf8").read()
dict={}
count=0
numericFile= open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Numeric.txt",'w',encoding="utf8")
descriptionFile=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Descript.txt",'w',encoding="utf8")
locationFile=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Location.txt",'w',encoding="utf8")
entityFile=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Entity.txt",'w',encoding="utf8")
personFile=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Person.txt",'w',encoding="utf8")

numeric_test= open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Numerictest.txt",'w',encoding="utf8")
description_test=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Descripttest.txt",'w',encoding="utf8")
location_test=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Locationtest.txt",'w',encoding="utf8")
entity_test=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Entitytest.txt",'w',encoding="utf8")
person_test=open("D:\\research 2\\Data\\V1 Dataset\\V1 Final\\Persontest.txt",'w',encoding="utf8")


descript_count=0
person_count=0
entity_count=0
numeric_count=0
location_count=0

count=0

for d in data.split("\n"):
    if count == 28697:
        break
    if count == 3214 or count == 3215 or count == 4606 or count == 4607 or count == 4608 or count == 28697:
        count=count+1
        continue
    #print(count)
    dict = ast.literal_eval(d)
    if dict['query_type'] == 'numeric':
        if numeric_count < 1000:
            numericFile.write(d + "\n")
            numericFile.flush()
            numeric_count=numeric_count+1
        else:
            numeric_test.write(d+"\n")
            numeric_test.flush()

    else:
        if dict['query_type'] == 'description':
            if descript_count < 1000:
                descript_count=descript_count+1
                descriptionFile.write(d + "\n")
                descriptionFile.flush()
            else:
                description_test.write(d+"\n")
                description_test.flush()
        else:
            if dict['query_type'] == 'location':
                if location_count < 1000:
                    location_count=location_count+1
                    locationFile.write(d + "\n")
                    locationFile.flush()
                else:
                    location_test.write(d+"\n")
                    location_test.flush()
            else:
                if dict['query_type'] == 'entity':
                    if entity_count < 1000:
                        entity_count=entity_count+1
                        entityFile.write(d + "\n")
                        entityFile.flush()
                    else:
                        entity_test.write(d+"\n")
                        entity_test.flush()
                else:
                    if person_count < 1000:
                        person_count=person_count+1
                        personFile.write(d + "\n")
                        personFile.flush()
                    else:
                        person_test.write(d+"\n")
                        person_test.flush()

    count = count + 1
    count2 = (count / 30000) * 100
    count3 = (int)(count2)
    print('\rCompleted....',count3,'%',end='',flush=True)

print('Desc;',descript_count)
print('Loca:',location_count)
print('enti:',entity_count)
print('pers:',person_count)
print('Numer:',numeric_count)
numericFile.close()
descriptionFile.close()
entityFile.close()
personFile.close()
locationFile.close()

# data=open("D:\\research 2\\Data\\V1 Dataset\\Numeric.json",encoding="utf8").read()
#
# for d in data.split("\n"):
#     print(d)
#     exit(0)