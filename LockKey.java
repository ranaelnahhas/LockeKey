import java.util.*;
public class LockKey {
	
	static int MaximumEven(ArrayList <Integer> locks,int start , int end ,int sum) {
		if(locks.size()==0)
			return 0;
		if(start==end)
			return locks.get(start);
		if(start+1 == end) {
			if(locks.get(start)>locks.get(end))
				return sum;
			else
				return sum+(locks.get(end)-locks.get(start));
		} 
		int x = Difference (locks, start ,  end,sum);
		int mid = (start+end)/2;
		if(mid%2!=0) mid+=1;
		return Math.max(x,Math.max(MaximumEven(locks,start,mid-1, sum),MaximumEven(locks,mid,end,sum)));
		

}
	static int Difference (ArrayList <Integer> locks,int start , int end,int sum) {
		int indexPivot =(start+end)/2;
		if(indexPivot%2!=0) indexPivot+=1;
		int diffEvenstepL = Integer.MIN_VALUE;
		int diffOddstepL =  Integer.MIN_VALUE;
		int sumOddL=0;
		int sumEvenL=0;
		for(int i = indexPivot-1 ; i>=start ;i--) {
			if(i%2==1) {
				sumOddL+=locks.get(i);
				if((sumOddL-sumEvenL)>diffOddstepL)
					diffOddstepL = sumOddL-sumEvenL;
			}
			else {
				sumEvenL+=locks.get(i);
				if((sumOddL-sumEvenL)>diffEvenstepL)
					diffEvenstepL = sumOddL-sumEvenL;
			}
			
			
		}
		int diffEvenstepR = Integer.MIN_VALUE;
		int diffOddstepR =  Integer.MIN_VALUE;
		int sumOddR=0;
		int sumEvenR=0;
		for(int i = indexPivot ; i<=end-1 ;i++) {
			if(i==indexPivot) {
				sumEvenR+=locks.get(i);
				diffOddstepR = locks.get(i)*-1;
				if(indexPivot!=end) {
					sumOddR+=locks.get(i+1);
					diffOddstepR +=sumOddR;
				}
			}
			else {
				if(i%2==1) {
					sumEvenR+=locks.get(i+1);
					if((sumOddR-sumEvenR)>diffEvenstepR)
						diffEvenstepR= sumOddR-sumEvenR;
				}
				else {
					sumOddR+=locks.get(i+1);
					if((sumOddR-sumEvenR)>diffOddstepR)
						diffOddstepR = sumOddR-sumEvenR;
				}
					
			}
			
		}
		
		if(diffOddstepR<0) diffOddstepR=0;
		if(diffEvenstepR<0) diffEvenstepR=0;
		if(diffOddstepL<0) diffOddstepL=0;
		if(diffEvenstepL<0) diffEvenstepL=0;
		int diff1= diffOddstepR + diffEvenstepL;
		int diff2 = diffEvenstepR+diffOddstepL;
		if(diff2 == diffOddstepL)
			diff2-=locks.get(indexPivot);
		if(diff1 == diffEvenstepL)
			diff1-=locks.get(indexPivot);
		if(diff1 <0 && diff2<0)
			return sum;
		
		return sum+Math.max(diff1,diff2);
	
	
}
	public static void main (String[] args) {
		ArrayList<Integer> lock =new ArrayList<>();
		lock.add(1);
		lock.add(3);
		lock.add(4);
		lock.add(2);
//		lock.add(7);
//		lock.add(6);
//		lock.add(2);
//		lock.add(9);
		int sum =0;
		for(int i=0;i<lock.size();i++) {
			if(i%2==0)
				sum+=lock.get(i);
		}
		System.out.println(MaximumEven(lock,0,lock.size()-1,sum));
			
		}
}
