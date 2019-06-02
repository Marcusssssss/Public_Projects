from Algorithm import Algorithm

def tests():
    for i in range(30):
        print("Test "+str(i+1)+":")
        algo = Algorithm("tests.txt", "paramTest.txt")
        algo.run()
        print("\n")

class Application:

    def main():
        print("\nTesting: \n")
        tests()
        print("\nThe tests are finished. This is a bigger example and here is the input:\n")
        algo = Algorithm("data.txt", "param.in", True)
        algo.run()

Application.main()