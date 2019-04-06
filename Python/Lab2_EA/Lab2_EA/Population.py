from Individ import Individ
from random import randint

class Population:
    
    def __init__(self, noPopulation, noIndivids, valuesSet):
        self.__noIndivids = noIndivids
        self.__listOfIndivids = self.createPopulation(noPopulation, valuesSet)

    def createPopulation(self, noPopulation, valuesSet):
        l=list()
        for i in range(noPopulation):
            l.append(Individ(valuesSet))
        return l

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



