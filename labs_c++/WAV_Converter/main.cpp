#include <vector>
#include <memory>
#include <iostream>
#include "general_include.h"
#include "WAVfile.h"
#include "ConvertFile.h"
#include "Sound_Processor.h"


int main(const int argc, char *argv[])
{
    if (argc < 4) {
        std::cerr << "not enough files\n";
        return ERROR_INPUT;
    }
    unsigned int number_of_input_files = argc - 3;
    std::vector<std::unique_ptr<WAVfile>> input_files;
    input_files.reserve(number_of_input_files);
    try {
        ConvertFile txt (argv[number_of_input_files + 2]);
        for (int i = 1; i < number_of_input_files + 1; i++)
            input_files.emplace_back(std::make_unique<WAVfile>(argv[i]));
        WAVfile out (argv[number_of_input_files + 1], *input_files.at(0).get());
        Sound_Processor sound(input_files, out, txt);
        sound.process();
    }
    catch(const Error_opening& err){
        std::cerr << "error with opening: " + std::string(err.what()) << std::endl;
        return ERROR_OPENING;
    }
    catch(const Wrong_data& err) {
        std::cerr << "error with input data: "+ std::string(err.what()) << std::endl;
        return ERROR_INPUT;
    }
    catch(const Error_reading& err) {
        std::cerr << "error with reading: "+ std::string(err.what()) << std::endl;
        return ERROR_READING;
    }
    catch(const Error_writing& err) {
        std::cerr << "error with writing: "+ std::string(err.what()) << std::endl;
        return ERROR_WRITING;
    }

}
