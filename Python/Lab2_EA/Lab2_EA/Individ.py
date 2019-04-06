from random import random, randint

class Individ:
    
    def __init__(self, values):
        self.__values = values
        self.__sets = self.generateSets()

    def generateSets(self):
        l = list()
        len1 = randint(1, len(self.__values) - 2)
        len2 = len(self.__values) - len1 - 1
        vals = self.generateOneSet(len1, self.__values)
        l.append(set(vals))
        l.append(set(self.__values - set(vals)))
        l.sort(key=len)
        return l

    def generateOneSet(self, size, values):
        vals = list(values)
        y = vals[randint(0, size)]
        sett = set()
        sett.add(y)
        for x in range(size):
            while y in sett:
                y = vals[randint(0, len(vals) - 1)]
            sett.add(y)
        return sorted(sett)

    def getValues(self):
        return self.__values

    def getSize(self):
        return self.__size

    def getSets(self):
        return self.__sets

    def setSize(self, size):
        self.__size = size

    def setSets(self, s):
        self.__sets = s

    def fitness(self, problem):
        count = 0
        ct1=ct2=0
        for subset in problem.getSubsets():
            if subset.issubset(self.__sets[0]):
                count-=1
                ct1+=1
            if subset.issubset(self.__sets[1]):
                count-=1
                ct2+=1

        count-=ct1+ct2

        return count 

    def mutate(self, pM):
        if pM > random():
            set1 = list(self.__sets[0])
            set2 = list(self.__sets[1])
            if len(set1) == 1:
                alpha = 0
            else: alpha = randint(0, len(set1) - 1)
            aux = set1[alpha]
            set1[alpha] = set2[alpha]
            set2[alpha] = alpha
            self.__sets[0] = set(set1)
            self.__sets[1] = set(set2)
        return self

    def crossover(self, anotherParent):
        set11 = self.__sets[0]
        set12 = self.__sets[1]
        set21 = anotherParent.getSets()[0]
        set22 = anotherParent.getSets()[1]

        set31 = set()
        set32 = set()
        
        if len((set21 - set11).union(set22 - set12)) > 0:
            set31 = (set21 - set11).union(set22 - set12)
            set32 = self.getValues() - set31
        elif len(set22.union(set12) - (set21.union(set11))) > 0:
            set31 = (set22.union(set12) - (set21.union(set11)))
            set32 = self.getValues() - set31
        else:
            set31 = (set22.union(set11)) - (set21.union(set12))
            set32 = self.getValues() - set31

        newIndivid = Individ(self.getValues())
        l = list()
        l.append(set31)
        l.append(set32)
        l.sort(key=len)
        newIndivid.setSets(l)

        return newIndivid

    def __str__(self):
        return str("D1: " + str(sorted(self.__sets[0]))) + "\n" + str("D2: " + str(sorted(self.__sets[1])))