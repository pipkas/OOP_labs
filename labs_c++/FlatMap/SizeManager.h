#ifndef SIZEMANAGER_H
#define SIZEMANAGER_Hvoid
#include "FlatMap.h"

class SizeManager {
public:
    static void change_size(FlatMap &map, int cell, size_t new_m_size);
};

#endif //SIZEMANAGER_H
