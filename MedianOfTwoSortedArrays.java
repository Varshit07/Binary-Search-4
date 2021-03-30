/**
 * TC: O(log(min (n1, n2)))
 * SC: O(1)
 * Approach: Do a binary search on the smaller array
 *           Based on the mid in the binary search, partition 2 arrays such that the total elements in left partitions equal the total elements in the right partitions
 *           We have hit the correct partition if the last element of left partiton in first array is less than or equal to the first element in the right partition of second array AND
             last element of left partition in the second array is less than or equal to the first elment in the right partition of first array     
 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null) {
            return 0.0;
        }
        
        int n1 = nums1.length, n2 = nums2.length;
        
        // ensure binary search is done on the smaller array
        if(n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int low = 0, high = nums1.length;
        while(low <= high) {
            // partition the arrays
            int partX = low + (high - low)/2;
            int partY = (n1 + n2)/2 - partX;
            
            // get elements near partition also taking care of the boundary conditions
            double l1 = ((partX - 1) < 0) ? Integer.MIN_VALUE : nums1[partX - 1];
            double l2 = ((partY - 1) < 0) ? Integer.MIN_VALUE : nums2[partY - 1];
            double r1 = (partX == n1) ? Integer.MAX_VALUE : nums1[partX];
            double r2 = (partY == n2) ? Integer.MAX_VALUE : nums2[partY];
              
            // foud the right partiiton
            if(l1 <= r2 && l2 <= r1) {
                // even 
                if((n1 + n2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2))/2;
                }
                //odd
                else {
                    return Math.min(r1, r2);
                }
            }
            // look for smaller element in first half of first array
            else if(l1 > r2) {
                high = partX - 1;
            }
            // look for larger element in second half of first array
            else if(l2 > r1) {
                low = partX + 1;
            }
        }
        
        return 0.0;
        }
}
