#include <iostream>
#include "CellSearcher.h"

int CellSearcher::find_cell(const std::string& key, bool& is_found, std::string *keys, const size_t number)
{
    if (number == 0)
        return 0;
    auto left = 0;
    auto right = static_cast<int>(number) - 1;
    while (left <= right){
        const auto mid = left + (right - left) / 2;
        std::string& cur = keys[mid];
        if (cur == key) {
            is_found = true;
            return mid;
        }
        if (key < cur)
            right = mid - 1;
        else
            left = mid + 1;
    }
    is_found = false;
    return left;
}
