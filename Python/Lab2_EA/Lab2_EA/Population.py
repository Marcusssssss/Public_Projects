from Individ import Individ
from random import randint

class Population:
    
    def __init__(self, noPopulation, noIndivids, valuesSet):
        self.__noIndivids = noIndivids
        self.__listOfIndivids = self.createPopulation(noPopulation, valuesSet)

    def createPopulation(self, noPopulation, valuesSet):
        l=list()
        for i in range(noPopulation):
            l.append(Individ(valuesSet, self.generateBinaryArr(valuesSet)))
        return l

    def generateBinaryArr(self, valuesSet):
        l = list([0]*len(valuesSet))
        len1 = randint(1, len(valuesSet) - 2)
        len2 = len(valuesSet) - len1 - 1
        vals = valuesSet.copy()
        for i in range(0, len1):
            found=False
            while found==False:
                x=randint(0, len(valuesSet)-1)
                if vals[x]!=-1:
                    l[x]=1 # 1 for D1
                    vals[x]=-1
                    found=True
        for i in range(0, len(valuesSet)):
            if vals[i]!=-1:
                l[i]=2
                vals[i]=-1
        return l

    def generateOneSet(self, size, valuesSet):
        vals = list(valuesSet)
        y = vals[randint(0, size)]
        sett = set()
        sett.add(y)
        for x in range(size):
            while y in sett:
                y = vals[randint(0, len(vals) - 1)]
            sett.add(y)
        return sorted(sett)

    def getNoIndivids(self):
        return self.__noIndivids

    def getListOfIndivids(self):
        return self.__listOfIndivids

    def setListOfIndivids(self, list):
        self.__listOfIndivids = list[:]

    def evaluate(self):
        return 0

    def selection(self):
        return 0

    def __str__(self):
        k = 0
        content=""
        for i in self.__listOfIndivids:
            content+="Individ " + str(k) + str(i)+"\n"
            k+=1
        return content



