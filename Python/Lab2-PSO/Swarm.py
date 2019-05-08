from Particle import  Particle

class Swarm:

    def __init__(self, no, dim, problem):
        self._particles = [Particle(dim, problem) for x in range(no)]
        self._noOfParticles = no

    def __len__(self):
        return len(self._particles)

    def getListOfParticles(self):
        return self._particles

    def getNoOfParticles(self):
        return self._noOfParticles

    def setListOfParticles(self, particles):
        self._particles = particles

    def setNoOfParticles(self, no):
        self._noOfParticles = no

    def getBestNeighbour(self, particle):
        return particle

    def getBestParticles(self):
        return []