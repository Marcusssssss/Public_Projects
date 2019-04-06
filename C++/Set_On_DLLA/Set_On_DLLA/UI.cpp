#include "UI.h"

using namespace std;

UI::UI(){}

UI::~UI(){}

int UI::printMenu()
{
	int option;
	cout << "1 - Print the brands from Set1\n";
	cout << "2 - Print the brands from Set2\n";
	cout << "3 - Add car brand in Set1 (it it isn't existent)\n";
	cout << "4 - Add car brand in Set2 (it it isn't existent)\n";
	cout << "5 - Remove car brand in Set1 (if it's existent)\n";
	cout << "6 - Remove car brand in Set2 (it it's existent)\n";
	cout << "7 - Set1 'reunion' Set2\n";
	cout << "8 - Set1 'intersection' Set2\n";
	cout << "9 - Set1 'difference' Set2\n";
	cout << "10 - Set2 'difference' Set1\n";
	cout << "11 - Solving the chosen problem\n";
	cin >> option;
	return option;
}

string UI::choose(std::string j, std::string str)
{
	string regions[4]{ "sb.txt", "cj.txt", "b.txt", "if.txt" };
	int result;
	cout << "Please, choose a text file for Set" + j + ":\n";
	for (int i = 0; i < 4; i++)
		if(str.compare(regions[i])!=0)
			cout << i << " - " << regions[i] << '\n';
	cin >> result;
	return regions[result];
}

void UI::solvingTheProblem()
{
	/*
	"
	all the car brands registered in Cluj (CJ) and in Sibiu (SB),
	which are also registered in Ilfov (IF), excepting Bucharest (B)."

	Realisticlly speaking, I think that all brand cars mentioned below exist in theese counties or in capital.
	So, it might be useful one or two centuries ago.
	But, take it just ipotetically.:)
	*/

	Set CJ{ 10, "cj.txt" }, SB{ 10, "sb.txt" }, IF{ 10, "if.txt" }, B{ 100, "b.txt" };
	Set CJ_and_SB = Set::reunion(CJ, SB);
	Set IF_not_B = Set::difference(IF, B);
	Set intersection = Set::intersection(CJ_and_SB, IF_not_B);

	DLLAIterator it{ intersection.list };

	cout << "The car brands from CJ and SB intersected with those from IF, but not from B:\n";
	while (it.valid())
	{
		cout << intersection.list.nodes[it.currentElement].info.c_str() << endl;
		it.next();
	}
}

void UI::printSet(Set s)
{
	DLLAIterator it{ s.list };
	while (it.valid())
	{
		cout << it.list.nodes[it.currentElement].toString();
		it.next();
	}
}

void UI::run()
{	
	Set s1{ 100,  choose("1", "")};
	Set s2{ 100,  choose("2", s1.fileName)};
	DLLANode brand{};
	while (true)
	{
		cout << "\n\n";
		int op = printMenu();
		switch (op)
		{
		case 1:
			cout << "Elements from set1: ";
			printSet(s1);
			break;
		case 2:
			cout << "Elements from set2: ";
			printSet(s2);
			break;
		case 3:
			cout << "Enter a car brand, please: ";
			cin >> brand.info;
			s1.add(brand);
			break;
		case 4:
			cout << "Enter a car brand, please: ";
			cin >> brand.info;
			s2.add(brand);
			break;
		case 5:
			cout << "Enter a car brand, please: ";
			cin >> brand.info;
			s1.remove(brand);
			break;
		case 6:
			cout << "Enter a car brand, please: ";
			cin >> brand.info;
			s2.remove(brand);
			break;
		case 7:
			printSet(Set::reunion(s1, s2));
			break;
		case 8:
			printSet(Set::intersection(s1, s2));
			break;
		case 9:
			printSet(Set::difference(s1, s2));
			break;
		case 10:
			printSet(Set::difference(s2, s1));
			break;
		case 11:
			solvingTheProblem();
			break;
		default:
			cout << "Choose an existent option, please!\n";
			break;
		}
		cout << "\n\n";
	}
}
