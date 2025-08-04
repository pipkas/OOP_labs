#include "ConvertFile.h"
#include <limits>
#include "general_include.h"

ConvertFile::ConvertFile(const char* file_name) : m_file_name(file_name)
{
    m_file.open(file_name,  std::ios::in);
    if (!m_file.is_open())
        throw Error_opening(file_name);
    std::map <std::string, int> convert{
                {"mute", MUTE}, {"mix", MIX}, {"increase", INCREASE}, {"", NOTHING}
    };
    while (!m_file.eof()) {
        std::string word;
        m_file >> word;
        switch (convert[word]) {
            case MUTE:
                fill_mute_data();  m_quantity++; break;
            case MIX:
                fill_mix_data(); m_quantity++; break;
            case INCREASE:
                fill_increase_data(); m_quantity++; break;
            case NOTHING:
                this->m_file.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); break;
            default:
                throw Wrong_data(m_file_name);
        }
    }
    if (m_quantity == 0)
        throw Wrong_data("Empty file " + m_file_name);
    m_file.close();
}



void ConvertFile::fill_mute_data() {
    std::vector <uint32_t> data(3);
    data.at(0) = MUTE;
    this->m_file >> data.at(1);
    this->m_file >> data.at(2);
    if (this->m_file.fail())
        throw Wrong_data(m_file_name);
    m_data.push_back(data);
}

void ConvertFile::fill_mix_data() {
    std::vector <uint32_t> data(3);
    data.at(0) = MIX;
    char symbol;
    this->m_file.ignore(1);
    this->m_file.get(symbol);
    if (symbol != '$')
        throw Wrong_data(m_file_name);
    this->m_file >> data.at(1);
    this->m_file >> data.at(2);
    if (this->m_file.fail())
        throw Wrong_data(m_file_name);
    m_data.push_back(data);
}

void ConvertFile::fill_increase_data() {
    std::vector <uint32_t> data(3);
    data.at(0) = INCREASE;
    this->m_file >> data.at(1);
    this->m_file >> data.at(2);
    if (this->m_file.fail())
        throw Wrong_data(m_file_name);
    m_data.push_back(data);
}
