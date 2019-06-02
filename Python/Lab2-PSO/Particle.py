from random import randint


class Particle:

    def __init__(self, l, problem):
        self._position = [randint(1, 2) for x in range(l)]

        x = randint(0, l - 1)
        y = l - 1 - randint(0, l - 1)
        self._position[x], self._position[y] = 1, 2

        self._problem = problem
        self.evaluate()
        self.velocity = [0 for i in range(l)]

        # the memory of that particle
        self._bestPosition = self._position.copy()
        self._bestFitness = self._fitness

    def countNotVisitedWithBFS(self, G, gg):
        # from GeeksForGeeks: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
        notVisited = len(G) - 1
        visited = [False] * len(self._problem.getVertices())
        queue = []
        try:
            queue.append(G[0])
            visited[G.index(G[0])] = True
            while queue:
                s = queue.pop(0)
                for i in G:
                    if [s, i] in self._problem.getEdges() and not visited[G.index(i)]:
                        queue.append(i)
                        visited[G.index(i)] = True
                        notVisited -= 1
        except IndexError:
            notVisited=len(G)*5
        return notVisited

    def fit(self, position):
        f = 0
        vertices = self._problem.getVertices()
        l1 = [vertices[i] for i in range(len(self._position)) if self._position[i] == 1]
        l2 = [vertices[i] for i in range(len(self._position)) if self._position[i] == 2]
        f += self.countNotVisitedWithBFS(l1, l2) + self.countNotVisitedWithBFS(l2, l1)
        f += (max(len(l1), len(l2)) - min(len(l1), len(l2))) * 2
        return f

    def evaluate(self):
        """ evaluates the particle """
        self._fitness = self.fit(self._position)

    @property
    def position(self):
        """ getter for position """
        return self._position

    @property
    def fitness(self):
        """ getter for fitness """
        return self._fitness

    @property
    def bestPosition(self):
        """ getter for best position """
        return self._bestPosition

    @property
    def bestFitness(self):
        """getter for best fitness """
        return self._bestFitness

    @position.setter
    def position(self, newPosition):
        self._position = newPosition.copy()
        # automatic evaluation of particle's fitness
        self.evaluate()
        # automatic update of particle's memory
        if (self._fitness < self._bestFitness):
            self._bestPosition = self._position
            self._bestFitness = self._fitness

    def __str__(self):
        vertices = self._problem.getVertices()
        l1 = [vertices[i] for i in range(len(self._position)) if self._position[i] == 1]
        l2 = [vertices[i] for i in range(len(self._position)) if self._position[i] == 2]
        return "G1 : " + str(l1) + "\nG2 : " + str(l2) + "\n\n"