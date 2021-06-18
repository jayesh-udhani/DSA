
public class MaxProductSubarray {
	public int maxProduct(int[] nums) {
        boolean nonPositiveAns=true;
        int i,n=nums.length,res=Integer.MIN_VALUE,max_pos=1,max_neg=1,max=Integer.MIN_VALUE,t;
        for(i=0;i<n;i++)
        {
            max=Math.max(max,nums[i]);
            if(nums[i]>0)
                nonPositiveAns=false;
            if(nums[i]<0 && i>0 && nums[i-1]<0)
                nonPositiveAns=false;
            if(nums[i]==0)
            {
                res=Math.max(res,0);
                max_pos=1;
                max_neg=1;
            }
            else if(nums[i]<0)
            {
                t=max_pos;
                max_pos=Math.max(1,nums[i]*max_neg);
                max_neg=Math.min(nums[i],t*nums[i]);
                res=Math.max(res,Math.max(max_pos,max_neg));
            }
            else
            {
                max_neg=Math.min(1,nums[i]*max_neg);
                max_pos=Math.max(nums[i],nums[i]*max_pos);
                res=Math.max(res,Math.max(max_pos,max_neg));
            }
        }
        return nonPositiveAns ? max : res;
    }
}
