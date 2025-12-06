#pragma once
#include <exception>
#include <string>

class Error_opening : public std::exception
{
    std::string m_message;
public:
    explicit Error_opening(const std::string& message)
                           : m_message(message){}
    explicit Error_opening() : m_message(){}
    const char* what() const noexcept override {
        return m_message.c_str();
    }
    ~Error_opening() override = default;
};

class Error_reading : public std::exception
{
    std::string m_message;
public:
    explicit Error_reading(const std::string& message) : m_message(message){}
    explicit Error_reading(): m_message(){}
    const char* what() const noexcept override {
        return m_message.c_str();
    }
    virtual ~Error_reading() = default;
};


class Error_writing : public std::exception
{
    std::string m_message;
    public:
    explicit Error_writing(const std::string& message)
                           : m_message(message){}
    explicit Error_writing() : m_message(){}
    const char* what() const noexcept override {
        return m_message.c_str();
    }
    ~Error_writing() override = default;
};

class Wrong_data : public std::exception
{
    std::string m_message;
public:
    explicit Wrong_data(const std::string& message)
                           : m_message(message){}
    const char* what() const noexcept override {
        return m_message.c_str();
    }
    virtual ~Wrong_data() = default;
};


