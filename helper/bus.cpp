//
// Created by Vasiliy Isaev on 2019-12-23.
//

#include "bus.h"
Bus::Bus (){
    number_t.mainPart = "А000АА";
    number_t.region = 0;
    driver_t.name = "testName";
    driver_t.sname = "testSName";
    route_t = 0;
};

Bus::Bus (std::string carNumber, int carReg, std::string driverName, std::string driverSName, int route ){
number_t.mainPart = carNumber;
number_t.region = carReg;
driver_t.name = driverName;
driver_t.sname = driverSName;
route_t = route;
};

Bus& Bus::operator=(const Bus &bus)
{
    // проверка на самоприсваивание
    if (this == &bus)
        return *this;

    driver_t = bus.driver_t;
    number_t = bus.number_t;
    route_t = bus.route_t;

    return *this;
}

Bus::Bus(const Bus &bus):
        driver_t(bus.driver_t), number_t(bus.number_t), route_t(bus.route_t){};

bool operator<(const Bus &d1, const Bus &d2) {
     return d1.route_t < d2.route_t;
};
bool operator<=(const Bus &d1, const Bus &d2) {
    return d1.route_t <= d2.route_t;
};

bool operator>(const Bus &d1, const Bus &d2) {
    return d1.route_t > d2.route_t;
};
bool operator>=(const Bus &d1, const Bus &d2) {
    return d1.route_t >= d2.route_t;
};

void operator++( Bus &bus ){
    bus.route_t++;
};
void operator--( Bus &bus ){
    bus.route_t--;
};

std::ostream& operator<< (std::ostream &out, const Bus &bus)
{
    out << bus.driver_t.name << "\t" << bus.driver_t.sname << "\t" << \
     bus.number_t.mainPart << bus.number_t.region << "\t" << bus.route_t << "\n";

    return out;
}

std::istream& operator>> (std::istream &in, Bus &bus)
{
    in >> bus.driver_t.name;
    in >> bus.driver_t.sname;
    in >> bus.number_t.mainPart;
    in >> bus.number_t.region;
    in >> bus.route_t;
    return in;
}