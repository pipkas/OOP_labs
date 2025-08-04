#pragma once
#include <vector>
#include <memory>
#include <fstream>
#include "WAVfile.h"
#include "ConvertFile.h"

class Sound_Processor {
    std::vector<std::unique_ptr<WAVfile>>& m_input_files;
    WAVfile& m_out;
    ConvertFile &txt;
public:
    Sound_Processor(std::vector<std::unique_ptr<WAVfile>>& input_files, WAVfile& out, ConvertFile& txt):
                    m_input_files(input_files), m_out(out), txt(txt) {}
    void process();
    void check_input_data();
    virtual ~Sound_Processor() = default;
};

