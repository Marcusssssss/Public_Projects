class Problem:

    def __init__(self, file):
        self._vertices = []
        self._edges = []
        self._n = 0
        self._noIt=0
        self.loadProblem(file)

    def getVertices(self):
        return self._vertices

    def getEdges(self):
        return self._edges

    def setVertices(self, vertices):
        self._vertices = vertices

    def setEdges(self, edges):
        self._edges = edges

    def getN(self):
        return self._n

    def setN(self, n):
        self._n = n

    def getNoIterations(self):
        return self._noIt

    def loadProblem(self, f):
        file = open(f)
        self._n = int(file.readline())
        for line in file:
            line = line.split()
            self._edges.append([int(line[0]), int(line[1])])
        verticesSet = set([i[0] for i in self._edges if i[0] not in self._vertices])
        self._vertices=list(verticesSet)
        file.close()