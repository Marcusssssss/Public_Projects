// Set_On_DLLA.cpp : Defines the entry point for the console application.
//
#include "UI.h"
#include "Tests.h"
#include <iostream>
#include <Windows.h>

using namespace std;

int main()
{
	TestsOnSet::testForAddingAndRemoving();
	TestsOnSet::testForReuninon();
	TestsOnSet::testForIntersection();
	TestsOnSet::testForDifference();
	TestsOnSet::testForDynamicVector();
	TestsOnSet::testForDLLA();
	TestsOnSet::testsForDLLAIterator();
	UI ui{}; ui.run();
	return 0;
}

