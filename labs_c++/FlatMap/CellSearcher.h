#ifndef CELLSEARCHER_H
#define CELLSEARCHER_H
#include <iostream>
#include "FlatMap.h"


class CellSearcher {
public:
    static int find_cell(const std::string& key, bool& is_found, std::string *keys, size_t number);
};



#endif //CELLSEARCHER_H
