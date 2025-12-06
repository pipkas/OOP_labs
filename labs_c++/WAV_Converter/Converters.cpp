#include "Converters.h"

void Mute_converter::process(std::vector<int16_t>& audioData){
    for (int i = 0; i < audioData.size(); i++)
        audioData.at(i) = 0;
}

void Mix_converter::process(std::vector<int16_t>& audioData) {
    size_t mixSize = std::min(audioData.size(), m_second.size());
    for (size_t i = 0; i < mixSize; ++i) {
        audioData.at(i) = (audioData.at(i) + m_second.at(i)) / 2;
    }
}

void Increase_converter::process(std::vector<int16_t>& audioData) {
    for (int i = 0; i < audioData.size(); i++)
        audioData.at(i) *= VOLUME_INCREMENT;
}

std::unique_ptr<Audio_converter> ConverterFactory::createConverter
    (std::vector<uint32_t>& data, std::vector<std::unique_ptr<WAVfile>>& input_files)
{
    switch(data.at(0)) {
        case MUTE:
            return std::make_unique<Mute_converter>();
        case MIX: {
            std::vector<int16_t> sec;
            input_files.at(data.at(1) - 1).get()->get_second(sec);
            return std::make_unique<Mix_converter>(sec);
        }
        case INCREASE:
            return std::make_unique<Increase_converter>();
        default:
            throw Wrong_data("");
    }
}
