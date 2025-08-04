#include "WAVfile.h"
#include <cstring>

WAVfile::WAVfile(const char *file_name): m_file_name(file_name)
{
    m_file.open(file_name, std::ios::in | std::ios::binary);
    if (!m_file.is_open())
        throw Error_opening(m_file_name);
    header.fill_header(m_file);
    subheader.fill_subheader(m_file);
    data.fill_data_header(m_file);
}

WAVfile::WAVfile(const char *file_name, WAVfile &in) : m_file_name(file_name)
{
    m_file.open(file_name, std::ios::out | std::ios::binary | std::ios::trunc);
    if (!m_file.is_open())
        throw Error_opening(m_file_name);
    file_work::fill_output_wav_file(m_file, in.m_file, in.m_file.tellg());
    header = in.header;
    subheader = in.subheader;
    data = in.data;
}


uint32_t WAVfile::get_seconds_count() {
    if(!m_file.is_open())
        throw Error_opening(m_file_name);
    return data.get_chunk_size() / subheader.get_byte_rate() + 1;
}


std::streamsize WAVfile::get_second(std::vector<int16_t>& second)
{
    if(!this->m_file.is_open())
        throw Error_opening(m_file_name);
    second.resize(subheader.get_sample_rate());
    this->m_file.read(reinterpret_cast<char*>(second.data()),subheader.get_byte_rate());
    return m_file.gcount();
}


void WAVfile::write_second(std::vector<int16_t>& second) {
    if(!m_file.is_open())
        throw Error_opening(m_file_name);
    m_file.write(reinterpret_cast<const char*>(second.data()), static_cast<std::streamsize>(second.size() * sizeof(int16_t)));
    /*char data[subheader.get_byte_rate()];
    std::memcpy(data, second.data(), second.size() * sizeof(int16_t));
    this->m_file.write(data, static_cast<std::streamsize>(second.size() * sizeof(int16_t)));*/
    if (this->m_file.fail())
        throw Error_writing(m_file_name);
}

