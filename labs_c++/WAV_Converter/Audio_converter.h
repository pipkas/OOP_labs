#pragma once
#include <vector>
#include <cstdint>
#include <memory>

class Audio_converter {
public:
    virtual void process(std::vector<int16_t>& audioData) = 0;
    virtual ~Audio_converter() = default;
};

