#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <set>

#include "bus.h"
#include "myList.h"

template <typename Iterator>
void insertion_sort(Iterator first, Iterator last) {
    if (!(first < last))
        return;

    for (Iterator i = first + 1; i != last; ++i)
        for (Iterator j = i; j != first && *j < *(j - 1); --j)
            std::iter_swap(j - 1, j);
}




int main() {

    myList<Bus> busList;
    int n = 5; // количество строк с описанием автобусов в файле
    Bus bus[n];

    std::ifstream infile;
    infile.open ("../input.txt");

    for(int i = 0; i < n; i ++){
        infile >> bus[i];

    }


    infile.close();
    //insertion_sort(bus, bus + n);

    //for(int i = 0; i < n; i ++)
     //   std::cout << bus[i];
   //std::cout << *std::max_element(bus, bus + n);

    for(int i = 0; i < n; i ++)
        busList.insert(bus[i]);

    std::ofstream myfile;
    myfile.open ("out.txt");
    myfile << "name and sname \t car number \t route\t\n";


     auto start = busList.begin();
     while(start != busList.end()){
         myfile << *start;
         start.operator++();
     }

    myfile.close();

    return 0;
}