#ifndef TASK1_FLATMAP_H
#define TASK1_FLATMAP_H
#include <iostream>
#include "FlatMap.h"
#include <cassert>
#define STEP_SIZE 100

class FlatMap {
    std::string *m_values;
    std::string *m_keys;
    std::size_t m_number;
    std::size_t m_size;
public:

    FlatMap() : m_number(0), m_size(STEP_SIZE)
    {
        m_keys  = new (std::nothrow) std::string[m_size];
        assert(m_keys != nullptr);
        m_values = new (std::nothrow) std::string[m_size];
        assert(m_values != nullptr);
    };

    FlatMap(const FlatMap& other_map) :  m_number(other_map.m_number), m_size(other_map.m_size)
    {
        m_keys  = new (std::nothrow) std::string[m_size];
        assert(m_keys != nullptr);
        m_values = new (std::nothrow) std::string[m_size];
        assert(m_values != nullptr);
        for (int i = 0; i < other_map.m_number; i++){
            m_keys[i] = other_map.m_keys[i];
            m_values[i] = other_map.m_values[i];
        }
    };

    virtual ~FlatMap()
    {
        delete [] m_keys;
        delete [] m_values;
        m_keys  = nullptr;
        m_values = nullptr;
    };

    friend class SizeManager;
    friend class CellSearcher;

    [[nodiscard]] std::size_t get_size() const { return m_size; };

    [[nodiscard]] std::size_t get_number() const { return m_number; };

    FlatMap& operator=(const FlatMap& other_map);

    std::string& operator[](const std::string& key);

    [[nodiscard]] bool contains (const std::string& key) const;

    std::size_t erase(const std::string& key);

    void clear();

};



#endif //TASK1_FLATMAP_H
