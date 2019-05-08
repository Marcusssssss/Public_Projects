from random import random, randint

class Individ:
    
    def __init__(self, values, binaryArr):
        self.__values = values
        self.__binaryArr = binaryArr
    
    def getValues(self):
        return self.__values

    def getSize(self):
        return len(values)

    def getBinaryArr(self):
        return self.__binaryArr

    def setBinaryArr(self, s):
        self.__binaryArr = s

    def fitness(self, problem):
        count = 0
        ct1=ct2=0
        set1=set()
        set2=set()

        for i in range(0, len(self.__binaryArr)-1):
            if self.__binaryArr[i]==1:
                set1.add(self.__values[i])
            else:
                set2.add(self.__values[i])

        
        for subset in problem.getSubsets():
            if subset.issubset(set1):
                count-=1
                ct1+=1
            if subset.issubset(set2):
                count-=1
                ct2+=1
        count-=ct1+ct2

        return count 

    def mutate(self, pM):
        if pM > random():
            try:
                x=randint(0, len(self.__values)-1)
                if(self.__binaryArr[x]==1):
                    self.__binaryArr[x]=2
                else: self.__binaryArr[x]=1
            except IndexError:
                return self
        return self

    def crossover(self, anotherParent):
        """
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

        l = list()
        l.append(set31)
        l.append(set32)
        l.sort(key=len)
        """
        arr1=self.getBinaryArr()
        arr2=anotherParent.getBinaryArr()
        x=randint(0, len(arr1)-1)
        l=list()
        l=arr1[:x]
        l.extend(arr2[x+1:len(arr2)-1])
        return Individ(self.getValues(), l)

    def __str__(self):
        set1 = {self.__values[i] for i in range(0, len(self.__binaryArr)-1) if self.__binaryArr[i]==1}
        set2 = {self.__values[i] for i in range(0, len(self.__binaryArr)-1) if self.__binaryArr[i]==2}
        return "D1: " + str(sorted(set1)) + "\n" + "D2: " + str(sorted(set2))