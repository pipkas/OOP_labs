#include <iostream>
#include <type_traits>
#include "gtest/gtest.h"
using namespace task1;

TEST(CellSearcherTest, TenElements) {
    std::string key = "key23";
    std::string keys[10];
    for (int i = 0; i < 10; i++)
      keys[i] = "key" + std::to_string(i);
    const size_t number = 10;
    bool is_found;
    int cell = CellSearcher::find_cell(key, is_found, keys, number);
    ASSERT_FALSE(is_found);
    ASSERT_EQ(cell, 3);
}

TEST(CellSearcherTest, ThousandElements) {
    std::string key1 = "key1099";
    std::string key2 = "key999";
    std::string keys[1000];
    for (int i = 1000; i < 2000; i++)
        keys[i - 1000] = "key" + std::to_string(i);
    const size_t number = 1000;
    bool is_found1;
    bool is_found2;
    int cell1 = CellSearcher::find_cell(key1, is_found1, keys, number);
    int cell2 = CellSearcher::find_cell(key2, is_found2, keys, number);
    ASSERT_TRUE(is_found1);
    ASSERT_FALSE(is_found2);
    ASSERT_EQ(cell1, 99);
    ASSERT_EQ(cell2, 1000);
}

TEST(SizeManagerTest, OneElement) {
    FlatMap gav;
    gav["test"] = "myau";
    ASSERT_EQ(gav.get_size(), 100);
    SizeManager::change_size(gav, 2, 105);
    ASSERT_EQ(gav.get_size(), 105);
    ASSERT_TRUE(gav.contains("test"));
}

TEST(SizeManagerTest, Empty) {
    FlatMap gav;
    ASSERT_EQ(gav.get_size(), 100);
    SizeManager::change_size(gav, 2, 105);
    ASSERT_EQ(gav.get_size(), 105);
}

TEST(SizeManagerTest, FullOfElements) {
    FlatMap gav;
    for (int i = 0; i < STEP_SIZE; i++)
        gav["test" + std::to_string(i)] = "myau" + std::to_string(i);
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    SizeManager::change_size(gav, 0, STEP_SIZE + 5);
    ASSERT_EQ(gav.get_size(), STEP_SIZE + 5);
}

TEST(FlatMapTest, GetSizeTest) {
    FlatMap map;
    map["test1"] = "1";
    EXPECT_EQ(map.get_size(), 100);
}

TEST(FlatMapTest, GetNumberTest) {
    FlatMap map;
    map["test1"] = "1";
    map["test2"] = "2";
    EXPECT_EQ(map.get_number(), 2);
}

TEST(FlatMapTest, EmptyConstructor){
    FlatMap gav;
    ASSERT_EQ(gav.get_size(), 100);
    ASSERT_EQ(gav.get_number(), 0);
}

TEST(FlatMapTest, OperatorInsertCommon){
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    ASSERT_EQ(gav["test2"], "boll");
    ASSERT_EQ(gav["test1"], "bone");
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    ASSERT_EQ(gav.get_number(), 2);
}

TEST(FlatMapTest, OperatorInsertManyElements){
    FlatMap gav;
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    for (int i = 0; i <= 3 * STEP_SIZE; i++)
        gav["test" + std::to_string(i)] = "myau" + std::to_string(i);
    ASSERT_EQ(gav.get_size(), 4 * STEP_SIZE);
}


TEST(FlatMapTest, OperatorInsertAbsenceElement){
    FlatMap gav;
    ASSERT_EQ(gav["test1"], "");
}

TEST(FlatMapTest, OperatorInsertRedesignation){
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test1"] = "boll";
    ASSERT_EQ(gav["test1"], "boll");
    ASSERT_EQ(gav.get_number(), 1);
}

TEST(FlatMapTest, OperatorInsertAbsenceCell){
    FlatMap gav;
    gav[""] = "gooool";
    ASSERT_EQ(gav[""], "gooool");
}

TEST(FlatMapTest, OperatorEqualSelfAssignment){
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    gav = gav;
    ASSERT_EQ(gav["test2"], "boll");
    ASSERT_EQ(gav["test1"], "bone");
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    ASSERT_EQ(gav.get_number(), 2);
}

TEST(FlatMapTest, OperatorEqual) {
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    FlatMap myau;
    myau = gav;
    ASSERT_EQ(gav["test1"], "bone");
    ASSERT_EQ(gav["test2"], "boll");
    ASSERT_EQ(myau["test1"], "bone");
    ASSERT_EQ(myau["test2"], "boll");
    ASSERT_EQ(myau.get_size(), STEP_SIZE);
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    ASSERT_EQ(myau.get_number(), 2);
    ASSERT_EQ(gav.get_number(), 2);
}



TEST(FlatMapTest, CopyConstructor) {
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    FlatMap myau(gav);
    ASSERT_EQ(gav["test1"], "bone");
    ASSERT_EQ(gav["test2"], "boll");
    ASSERT_EQ(myau["test1"], "bone");
    ASSERT_EQ(myau["test2"], "boll");
    ASSERT_EQ(myau.get_size(), STEP_SIZE);
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
    ASSERT_EQ(myau.get_number(), 2);
    ASSERT_EQ(gav.get_number(), 2);
}

TEST(FlatMapTest, ContainsTest){
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    ASSERT_TRUE(gav.contains("test1"));
    ASSERT_TRUE(gav.contains("test2"));
    ASSERT_FALSE(gav.contains("test3"));
}

TEST(FlatMapTest, EraseElementCommon){
    FlatMap gav;
    gav["test1"] = "bone";
    ASSERT_TRUE(gav.contains("test1"));
    ASSERT_EQ(gav.get_number(), 1);
    ASSERT_EQ(gav.erase("test1"), 1);
    ASSERT_FALSE(gav.contains("test1"));
    ASSERT_EQ(gav.get_number(), 0);
}

TEST(FlatMapTest, EraseAbsenceElement){
    FlatMap gav;
    ASSERT_FALSE(gav.contains("test1"));
    ASSERT_EQ(gav.get_number(), 0);
    ASSERT_EQ(gav.erase("test1"), 0);
    ASSERT_FALSE(gav.contains("test1"));
    ASSERT_EQ(gav.get_number(), 0);
}

TEST(FlatMapTest, ClearTestingFewElements){
    FlatMap gav;
    gav["test1"] = "bone";
    gav["test2"] = "boll";
    gav.clear();
    ASSERT_FALSE(gav.contains("test1"));
    ASSERT_FALSE(gav.contains("test2"));
    ASSERT_EQ(gav.get_number(), 0);
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
}

TEST(FlatMapTest, ClearTestingManyElements){
    FlatMap gav;
    for (int i = 0; i <= 3 * STEP_SIZE; i++)
        gav["test" + std::to_string(i)] = "myau" + std::to_string(i);
    gav.clear();
    ASSERT_FALSE(gav.contains("test1"));
    ASSERT_EQ(gav.get_number(), 0);
    ASSERT_EQ(gav.get_size(), STEP_SIZE);
}


int main(int argc, char **argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}

