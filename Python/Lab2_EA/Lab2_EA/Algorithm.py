from Problem import Problem
from Population import Population
from Individ import Individ
from random import randint
import matplotlib.pyplot as plotty

class Algorithm:
    
    def __init__(self, file, paramFile, toBe=False):
        self.__problem = Problem(file)
        file = open(paramFile)
        self.__dimPopulation = int(file.readline())
        self.__dimIndividual = int(file.readline())
        self.__noIterations = int(file.readline())
        self.__min = self.__problem.getMinVal()
        self.__max = self.__problem.getMaxVal()
        self.__population = Population(self.__dimPopulation, self.__dimIndividual, self.__problem.getInitialSet())
        self.__x = 0
        self.__toBeDrawn=toBe

    def getProblem(self):
        return self.__problem

    def getData(self):
        return self.__data

    def getPopulation(self):
        return self.__population

    def readParameters(self, file_name):
        return 0

    def iteration(self, pM):
        i1 = randint(0, len(self.__population.getListOfIndivids()) - 1)
        i2 = randint(0, len(self.__population.getListOfIndivids()) - 1)

        listOfIndivids = self.__population.getListOfIndivids()

        # child
        c = listOfIndivids[i1].crossover(listOfIndivids[i2])
        c.mutate(pM)

        f1 = listOfIndivids[i1].fitness(self.__problem)
        f2 = listOfIndivids[i2].fitness(self.__problem)
        fc = c.fitness(self.__problem)

        if f1 > fc:
            listOfIndivids[i1].setBinaryArr(c.getBinaryArr())
        if f2 > fc:
            listOfIndivids[i2].setBinaryArr(c.getBinaryArr())

        return self.__population

    def run(self):
        pM = self.__dimPopulation / self.__noIterations

        for i in range(self.__noIterations):
            p = self.iteration(pM)

        self.statistics()

    def statistics(self):
        #print the best individual

        graded = [ (x.fitness(self.__problem), x) for x in self.__population.getListOfIndivids()]

        graded.sort(key=lambda x: x[0], reverse=True)
        result = graded[0]
        print('\nThe best solution is:\n' + str(result[1]) + ' \nIt has its fitness = ' + str(result[0]) + "\n")

        if self.__toBeDrawn:
            fitnesses = [ x.fitness(self.__problem) for x in self.__population.getListOfIndivids()]
            plotty.plot(fitnesses)
            plotty.show()