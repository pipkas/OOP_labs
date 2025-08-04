#include <iostream>
#include "FlatMap.h"
#include "SizeManager.h"

void SizeManager::change_size(FlatMap &map, const int cell, const size_t new_m_size) {

    auto *new_m_keys  = new (std::nothrow) std::string[new_m_size];
    assert(new_m_keys != nullptr);
    auto *new_m_values = new (std::nothrow) std::string[new_m_size];
    assert(new_m_values != nullptr);

    for (auto i = 0; i < cell; i++){
        new_m_keys[i] = map.m_keys[i];
        new_m_values[i] = map.m_values[i];
    }

    if (new_m_size > map.m_size) {
        for (auto i = cell + 1; i <= map.m_number; i++){
            new_m_keys[i] = map.m_keys[i - 1];
            new_m_values[i] = map.m_values[i - 1];
        }
    }
    else {
        for (auto i = cell; i < map.m_number - 1; i++) {
            new_m_keys[i] = map.m_keys[i + 1];
            new_m_values[i] = map.m_values[i + 1];
        }
    }

    map.m_size = new_m_size;

    delete [] map.m_keys;
    delete [] map.m_values;
    map.m_keys = new_m_keys;
    map.m_values = new_m_values;
}
