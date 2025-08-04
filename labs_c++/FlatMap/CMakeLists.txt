cmake_minimum_required(VERSION 3.16)

project(task1)

set(CMAKE_CXX_STANDARD 17)

add_executable(task1 test.cpp FlatMap.cpp FlatMap.h
        SizeManager.cpp
        SizeManager.h
        CellSearcher.cpp
        CellSearcher.h)
