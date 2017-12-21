package me.feiliu.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * @author liufei
 * @since 2017/12/21
 */
public class TwoSum {

    /**
     * O(n2)
     * O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = {0, 0};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * O(n)
     * O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int a = target - nums[i];
            if (map.containsKey(a)) {
                return new int[]{i, map.get(a)};
            }
            map.put(nums[i], i);
        }
        throw new RuntimeException("no...");
    }

    /**
     * O(n)
     * O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int a = target - nums[i];
            if (map.containsKey(a) && map.get(a) != nums[i]) {
                return new int[]{i, map.get(a)};
            }
        }
        throw new RuntimeException("no...");
    }


    public static void main(String[] args) {
        int[] nums = {-3,4,3,90};
        int target = 0;
        int[] result = TwoSum.twoSum2(nums, target);
        System.out.println(result[0] + "," + result[1]);
    }
}
