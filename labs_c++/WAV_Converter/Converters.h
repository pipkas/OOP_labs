#pragma once
#include "Audio_converter.h"
#include "general_include.h"
#include "WAVfile.h"

class Mute_converter : public Audio_converter
{
public:
    Mute_converter() = default;
    void process(std::vector<int16_t>& audioData) override;
    ~Mute_converter() override = default;
};


class Mix_converter : public Audio_converter
{
    std::vector<int16_t> m_second;
public:
    explicit Mix_converter(const std::vector<int16_t>& second) : m_second(second) {}

    void process(std::vector<int16_t>& audioData) override;
    ~Mix_converter() override = default;
};


class Increase_converter : public Audio_converter
{
public:
    Increase_converter() = default;

    void process(std::vector<int16_t>& audioData) override;

    ~Increase_converter() override = default;
};

class ConverterFactory {
public:
    static std::unique_ptr<Audio_converter> createConverter
    (std::vector<uint32_t>& data, std::vector<std::unique_ptr<WAVfile>>& input_files);
};
