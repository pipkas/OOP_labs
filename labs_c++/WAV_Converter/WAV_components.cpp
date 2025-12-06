#include "WAV_components.h"
#include "file_work.h"

void RIFF_header::fill_header(std::fstream &file) {
    file_work::find_block(file, "RIFF");
    file_work::read_value(file, m_header_size);

    if(file_work::read_chunk(file, "WAVE"))
        m_format = "WAVE";
}


void Fmt_subheader::fill_subheader(std::fstream& file)
{
    file_work::find_block(file, "fmt ");

    file_work::read_value(file, m_chunk_size);
    file_work::read_value(file, m_audio_format);
    file_work::read_value(file, m_num_chunks);
    file_work::read_value(file, m_sample_rate);
    file_work::read_value(file, m_byte_rate);
    file_work::read_value(file, m_block_align);
    file_work::read_value(file, m_bits_per_sample);
}


void Data_of_wav_file::fill_data_header(std::fstream &file) {
    file_work::find_block(file, "data");
    file_work::read_value(file, m_chunk_size);
}