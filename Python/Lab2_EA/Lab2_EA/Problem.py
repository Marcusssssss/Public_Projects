class Problem:

    def __init__(self, file):
        self.__max = 0
        self.__min = 1000
        self.__set = set()
        self.__subsets = []
        self.loadData(file)

    def getInitialSet(self):
        return self.__set

    def getSubsets(self):
        return self.__subsets

    def getMaxVal(self):
        return self.__max
    
    def getMinVal(self):
        return self.__min

    def loadData(self, fileName):
        file = open(fileName)
        self.__set = [int(x) for x in next(file).split()]

        for x in self.__set:
            if self.__max < x:
                self.__max = x
            if self.__min > x:
                self.__min = x
        sett = set()
        for line in file:
            sett = {int(x) for x in line.split()}
            self.__subsets.append(sett)

        print("Set A: " + str(self.__set))
        print("S-sets: " + str(self.__subsets))
