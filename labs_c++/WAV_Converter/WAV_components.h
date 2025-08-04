#pragma once
#include "general_include.h"

class RIFF_header {
protected:
    uint32_t m_header_size = 0;
    std::string m_format;
public:
    RIFF_header() = default;

    explicit RIFF_header(std::fstream &file)
    {
        fill_header(file);
    };
    void fill_header(std::fstream &file);
    ~RIFF_header() = default;
};

class Fmt_subheader {
protected:
    uint32_t m_chunk_size = 0;
    uint16_t m_audio_format = 0;
    uint16_t m_num_chunks = 0;
    uint32_t m_sample_rate = 0;
    uint32_t m_byte_rate = 0;
    uint16_t m_block_align = 0;
    uint16_t m_bits_per_sample = 0;
public:
    Fmt_subheader() = default;

    explicit Fmt_subheader(std::fstream& file)
    {
        fill_subheader(file);
    }
    friend class WAVfile;
    void fill_subheader(std::fstream& file);
    ~Fmt_subheader() = default;
    uint32_t get_chunk_size(){return m_chunk_size;}
    uint16_t get_audio_format(){return m_audio_format;}
    uint16_t get_num_chunks(){return m_num_chunks;}
    uint32_t get_sample_rate(){return m_sample_rate;}
    uint32_t get_byte_rate(){return m_byte_rate;}
    uint16_t get_block_align(){return m_block_align;}
    uint16_t get_bits_per_sample(){return m_bits_per_sample;}
};

class Data_of_wav_file {
protected:
    uint32_t m_chunk_size = 0;
public:
    Data_of_wav_file() = default;
    explicit Data_of_wav_file(std::fstream &file) {
        fill_data_header(file);
    }
    void fill_data_header(std::fstream &file);
    ~Data_of_wav_file() = default;
    uint32_t get_chunk_size() { return m_chunk_size; }
};



