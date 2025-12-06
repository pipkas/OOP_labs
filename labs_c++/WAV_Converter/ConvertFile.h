#pragma once
#include <map>
#include <vector>
#include <fstream>
#include "general_include.h"

class ConvertFile {
    std::string m_file_name;
    std::fstream m_file;
    std::vector <std::vector <uint32_t> > m_data;
    uint8_t m_quantity = 0;
public:
    explicit ConvertFile(const char* file_name);
    void fill_mute_data();
    void fill_mix_data();
    void fill_increase_data();
    virtual ~ConvertFile() = default;

    std::string& get_file_name(){return m_file_name;}
    uint8_t get_quantity(){return m_quantity;}
    std::vector <uint32_t>& get_data(int No_converter){return m_data.at(No_converter - 1);}
};
