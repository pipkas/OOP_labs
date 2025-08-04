#pragma once
#include <vector>
#include "general_include.h"
#include "WAV_components.h"
#include "file_work.h"

class WAVfile
{
protected:
    std::string m_file_name;
    std::fstream m_file;
public:
    RIFF_header header;
    Fmt_subheader subheader;
    Data_of_wav_file data;

    WAVfile() = default;
    explicit WAVfile(const char *file_name);
    WAVfile(const char *file_name, WAVfile &in);

    friend void fill_output_wav_file(std::fstream& file_out, std::fstream& file_in, std::streampos pos);

    virtual ~WAVfile() = default;
    std::string get_file_name(){ return m_file_name; };
    uint32_t get_seconds_count();
    std::streamsize get_second(std::vector<int16_t>& second);
    void write_second(std::vector<int16_t>& second);
};
