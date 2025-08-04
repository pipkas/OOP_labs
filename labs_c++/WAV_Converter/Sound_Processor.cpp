#include "Sound_Processor.h"
#include "Audio_converter.h"
#include "Converters.h"

void Sound_Processor::check_input_data() {
    const uint32_t seconds_count = m_input_files[0].get()->get_seconds_count();
    const size_t input_files_count = m_input_files.size();
    for (int i = 1; i <= txt.get_quantity(); i++) {
        std::vector data = txt.get_data(i);
        switch (data.at(0)) {
            case MUTE: {
                if (data.at(1) >= seconds_count || data.at(2) > seconds_count)
                    throw Wrong_data("out of range in mute converter");
                break;
            }
            case MIX: {
                if (data.at(1) > input_files_count || data.at(2) > seconds_count)
                    throw Wrong_data("out of range in mix converter");
                break;
            }
            case INCREASE: {
                if (data.at(1) >= seconds_count || data.at(2) > seconds_count)
                    throw Wrong_data("out of range in increase converter");
                break;
            }
            default:
                throw Wrong_data(txt.get_file_name());
        }
    }
}


bool check_sec(int sec, std::vector<uint32_t>& data) {
    switch (data.at(0)) {
        case MUTE: {
            if (sec >= data.at(1) && sec <= data.at(2))
                return true;
            return false;
        }
        case MIX: {
            if (sec >= data.at(2))
                return true;
            return false;
        }
        case INCREASE: {
            if (sec >= data.at(1) && sec <= data.at(2))
                return true;
            return false;
        }
        default:
            throw Wrong_data("");
    }
}


void Sound_Processor::process() {
    check_input_data();

    const uint32_t seconds_count = m_input_files.at(0).get()->get_seconds_count();
    for (int i = 0; i < seconds_count; i++) {
        std::vector<int16_t> sec;
        std::streamsize bytes = m_input_files.at(0).get()->get_second(sec);
        sec.resize(bytes / sizeof(int16_t));
        for (int j = 1; j <= txt.get_quantity(); j++) {
            std::vector <uint32_t> data = txt.get_data(j);
            if(check_sec(i, data)) {
                auto converter = ConverterFactory::createConverter(data, m_input_files);
                converter->process(sec);
            }
        }
        this->m_out.write_second(sec);

    }
}

