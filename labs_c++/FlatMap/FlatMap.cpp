#include <iostream>
#include <cstring>
#include "FlatMap.h"
#include "CellSearcher.h"
#include "SizeManager.h"

FlatMap& FlatMap::operator=(const FlatMap& other_map){
    if (this == &other_map)
        return *this;
    this->m_size = other_map.m_size;
    this->m_number = other_map.m_number;
    delete [] this->m_keys;
    delete [] this->m_values;

    m_keys  = new (std::nothrow) std::string[m_size];
    assert(m_keys != nullptr);
    m_values = new (std::nothrow) std::string[m_size];
    assert(m_values != nullptr);

    for (int i = 0; i < other_map.m_number; i++){
        m_keys[i] = other_map.m_keys[i];
        m_values[i] = other_map.m_values[i];
    }
    return *this;
}


bool FlatMap::contains(const std::string& key) const
{
    bool is_found;
    CellSearcher::find_cell(key, is_found, this->m_keys, this->m_number);
    return is_found;
}


std::string& FlatMap::operator[](const std::string& key) {
    bool is_found;
    const int cell = CellSearcher::find_cell(key, is_found, this->m_keys, this->m_number);
    if (!is_found){
        if (this->m_number == this->m_size)
            SizeManager::change_size(*this, cell, this->m_size + STEP_SIZE);
        else {
            for(int i = static_cast<int>(this->m_number) - 1; i >= cell; i--) {
                this->m_values[i] = this->m_values[i + 1];
                this->m_keys[i] = this->m_keys[i + 1];
            }
        }
        this->m_keys[cell] = key;
        this->m_number++;
    }
    return this->m_values[cell];
}


std::size_t FlatMap::erase(const std::string& key)
{
    bool is_found;
    const int cell = CellSearcher::find_cell(key, is_found, this->m_keys, this->m_number);
    if (!is_found)
        return 0;

    if (this->m_size - this->m_number >= 2 * STEP_SIZE)
        SizeManager::change_size(*this, cell, this->m_size - STEP_SIZE);
    else {
        for(int i = cell; i < static_cast<int>(this->m_number); i++) {
            this->m_keys[i + 1] = this->m_keys[i];
            this->m_values[i + 1] = this->m_values[i];
        }
    }
    this->m_number--;
    return 1;
}

void FlatMap::clear() {
    delete [] this->m_keys;
    delete [] this->m_values;
    this->m_keys = nullptr;
    this->m_values = nullptr;
    FlatMap clean;
    *this = clean;
}
