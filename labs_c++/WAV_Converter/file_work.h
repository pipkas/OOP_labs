#pragma once
#include <fstream>
#include "general_include.h"

class file_work {
public:
    template <typename T>
    static void read_value(std::fstream& file, T& value) {
        if (!file.is_open())
            throw Error_opening();
        file.read(reinterpret_cast<char*>(&value), sizeof(T));
        if (file.gcount() != sizeof(T))
            throw Error_reading();
    }
    static bool read_chunk(std::fstream& file, const std::string& chunkID);
    static void find_block(std::fstream& file, const std::string& chunkID);
    static void fill_output_wav_file(std::fstream& file_out, std::fstream& file_in, std::streampos pos);
};

