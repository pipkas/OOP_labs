#include "file_work.h"


bool file_work::read_chunk(std::fstream& file, const std::string& chunkID)
{

    char buffer[SIZEOF_CHUNKID];
    if (!file.is_open())
        throw Error_opening();
    file.read(buffer, SIZEOF_CHUNKID);
    if (file.gcount() != SIZEOF_CHUNKID)
        throw Error_reading(chunkID);
    if (std::string(buffer, SIZEOF_CHUNKID) == chunkID)
        return FOUND;
    else
        return NOT_FOUND;
}


void file_work::find_block(std::fstream& file, const std::string& chunkID)
{

    while(!file.eof()) {
        bool is_found = read_chunk(file, chunkID);
        if (is_found == NOT_FOUND) {
            uint32_t chunk_size;
            read_value(file, chunk_size);
            file.seekg(chunk_size,std::ios::cur);
        }
        else
            return;
    }
    throw Wrong_data(chunkID);
}


void file_work::fill_output_wav_file(std::fstream& file_out, std::fstream& file_in, std::streampos pos)
{
    file_in.seekg(std::ios::beg);
    file_out.seekg(std::ios::beg);

    char buffer[BUFFER_SIZE];
    std::streamsize bytesToCopy = pos;

    while (bytesToCopy > 0) {
        std::streamsize bytesToRead = std::min(BUFFER_SIZE, bytesToCopy);
        file_in.read(buffer, bytesToRead);

        if (file_in.gcount() != bytesToRead)
            throw Error_reading();
        file_out.write(buffer, bytesToRead);
        bytesToCopy -= bytesToRead;
    }
}
