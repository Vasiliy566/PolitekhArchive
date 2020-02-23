//
// Created by Vasiliy Isaev on 2019-12-23.
//

#ifndef UNTITLED3_BUS_H
#define UNTITLED3_BUS_H
#include<string>
#include <iostream>
class Bus {
public:

    // номер автобуса
    struct number{
        std::string mainPart; // основная часть: А680ЕМ
        int region;           // регион: 192
    } number_t;

    // водитель автобуса
    struct driver{
        std::string name;     // имя: Иван
        std::string sname;    // фамилия: Иванов
    } driver_t;
    unsigned int route_t; // номер маршрута: 1

    // пустой конструктор, заполнен тестовым примером
    Bus ();

    // конструктор с параметрами
    Bus (std::string carNumber, int carReg, std::string driverName, std::string driverSName, int route );

    Bus(const Bus &bus);

    //  присваивание
    Bus& operator= (const Bus &bus);

    friend bool operator> (const Bus &d1, const Bus &d2);
    friend bool operator<= (const Bus &d1, const Bus &d2);

    friend bool operator< (const Bus &d1, const Bus &d2);
    friend bool operator>= (const Bus &d1, const Bus &d2);

    friend void operator++ (const Bus &d1);
    friend void operator-- (const Bus &d1);

    friend std::ostream& operator<< (std::ostream &out, const Bus &bus);
    friend std::istream& operator>> (std::istream &in, Bus &bus);

    ~Bus()
    {
        // освободить память если была занята
    };

};








#endif //UNTITLED3_BUS_H
