#pragma once
#include <cstdint>
#include <ios>
#include "my_exceptions.h"


constexpr char SIZEOF_CHUNKID (4);
constexpr char SIZEOF_FORMAT (4);
constexpr char ERROR_INPUT (4);
constexpr char ERROR_OPENING (5);
constexpr char ERROR_READING (6);
constexpr char ERROR_WRITING (7);
constexpr char FOUND (1);
constexpr char NOT_FOUND (0);
constexpr std::streamsize BUFFER_SIZE (1000000);
constexpr uint32_t MUTE (1);
constexpr uint32_t MIX (2);
constexpr uint32_t INCREASE (3);
constexpr uint32_t NOTHING (4);
constexpr uint16_t VOLUME_INCREMENT (3);
