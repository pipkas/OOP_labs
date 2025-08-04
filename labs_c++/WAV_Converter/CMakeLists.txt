cmake_minimum_required(VERSION 3.29)
project(task2)

set(CMAKE_CXX_STANDARD 20)

add_executable(task2 main.cpp
        WAVfile.cpp
        file_work.cpp
        ConvertFile.cpp
        Audio_converter.h
        Converters.cpp
        Converters.h
        Sound_Processor.cpp
        Sound_Processor.h
        WAV_components.cpp
        WAV_components.h
)
