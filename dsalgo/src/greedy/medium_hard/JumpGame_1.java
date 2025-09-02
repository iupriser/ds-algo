package greedy.medium_hard;

//https://leetcode.com/problems/jump-game/description/
public class JumpGame_1 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxIndexCanBeReached = 0;
        for (int index = 0; index < n; index++) {
            if (maxIndexCanBeReached < index) return false;
            maxIndexCanBeReached = Math.max(maxIndexCanBeReached, index + nums[index]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 1, 1, 0, 2, 5};
        JumpGame_1 obj = new JumpGame_1();
        System.out.println(obj.canJump(nums));

    }
}
