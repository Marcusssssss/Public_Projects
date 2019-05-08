from random import randint, random
from math import exp
import matplotlib.pyplot as pyp

from Problem import Problem
from Swarm import Swarm


class Controller:

    def __init__(self, problemFile, paramFile, toBe=False):
        self._problem = Problem(problemFile)
        self._dimParticle = len(self._problem.getVertices())
        self._noPopulation, self._sizeOfNeighborhood, self._w, self._c1, self._c2 = 0, 0, 0, 0, 0
        self.loadParameters(paramFile)
        self._population = Swarm(self._noPopulation, self._dimParticle, self._problem)
        self._neighborhoods = self.selectNeighbors()
        self._toBeDrawn=toBe

    def getPopulation(self):
        return self._population

    def setPopulation(self):
        return self._population

    def selectNeighbors(self):
        pop = self._population
        nSize = self._sizeOfNeighborhood

        if (nSize > len(pop)):
            nSize = len(pop)

        # Attention if nSize==len(pop) this selection is not a propper one
        # use a different approach (like surfle to form a permutation)
        neighbors = []
        for i in range(len(pop)):
            localNeighbor = []
            for j in range(nSize):
                x = randint(0, len(pop) - 1)
                while (x in localNeighbor):
                    x = randint(0, len(pop) - 1)
                localNeighbor.append(x)
            neighbors.append(localNeighbor.copy())
        return neighbors

    def sigmoidFunction(self, x):
        return 1/(1 + exp(-x))

    def iteration(self):
        pop, neighbors, w, c1, c2 = self._population.getListOfParticles(), self._neighborhoods, self._w, self._c1, self._c2

        bestNeighbors = []
        # determine the best neighbor for each particle
        for i in range(len(pop)):
            bestNeighbors.append(neighbors[i][0])
            for j in range(1, len(neighbors[i])):
                if (pop[bestNeighbors[i]].fitness > pop[neighbors[i][j]].fitness):
                    bestNeighbors[i] = neighbors[i][j]

        # update the velocity for each particle
        for i in range(len(pop)):
            for j in range(len(pop[0].velocity)):
                newVelocity = w * pop[i].velocity[j]
                newVelocity = newVelocity + c1 * random() * (pop[bestNeighbors[i]].position[j] - pop[i].position[j])
                newVelocity = newVelocity + c2 * random() * (pop[i].bestPosition[j] - pop[i].position[j])
                pop[i].velocity[j] = newVelocity

        # update the position for each particle
        for i in range(len(pop)):
            newposition = []
            for j in range(len(pop[i].velocity)):
                if(random()>=self.sigmoidFunction(pop[i].velocity[j])):
                    newposition.append(1)
                else: newposition.append(2)
            pop[i].position=newposition
        self._population.setListOfParticles(pop)
        return self._population

    def runAlgorithm(self):

        for i in range(self._noPopulation):
            self._population = self.iteration()

        best = 0
        fitnesses=[]
        P = self._population.getListOfParticles()
        for i in range(0, len(self._population)):
            fitnesses.append(P[i].fitness)
            if (P[i].fitness < P[best].fitness):
                best = i

        if self._toBeDrawn:
            pyp.plot(fitnesses)
            pyp.show()

        print('The best particle has: \n' + str(P[best]) + 'Fitness = ' + str(P[best].fitness)+'\n')

    def loadParameters(self, paramFile):
        f = open(paramFile)
        line = f.readline().split()
        self._noPopulation, self._sizeOfNeighborhood, self._w, self._c1, self._c2 = \
            int(line[0]), int(line[1]), float(line[2]), float(line[3]), float(line[4])
        f.close()


def tests():
    for i in range(30):
        print("Test " + str(i+1) + ":\n")
        ctrl = Controller("tests.txt", "testParam.in")
        ctrl.runAlgorithm()


print("Tests:\n")
tests()

print("\n\nTHE MAIN PROBLEM:\n")
ctrl = Controller("data.txt", "param.in", True)
ctrl.runAlgorithm()