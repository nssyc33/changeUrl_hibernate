package testGitHub.io.Service;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
	 
class BigData {
    private int[] array = new int[2500]; //10000byte, 10K
}
 
public class WeakSoftStrongReference {
    private List<WeakReference<BigData>> weakRefs = new ArrayList<>();
    private List<SoftReference<BigData>> softRefs = new ArrayList<>();
    private List<BigData> strongRefs = new ArrayList<>();
    
    
    public void weakReferenceTest() {
        try {
            for (int i = 0; true; i++) {
            	System.out.println("Weak test");
                weakRefs.add(new WeakReference<BigData>(new BigData()));
            }
        } catch (OutOfMemoryError ofm) { // weak일 경우 out of memory 발생 하지 않는다.
            System.out.println("out of memory!");
        }
    }
    
    public void softReferenceTest() {
        try {
            for (int i = 0; true; i++) {
            	System.out.println("Soft test");
                softRefs.add(new SoftReference<BigData>(new BigData()));
            }
        } catch (OutOfMemoryError ofm) { // weak일 경우 out of memory 발생 하지 않는다.
            System.out.println("out of memory!");
        }
    }
    
    
    public void strongReferenceTest() {
        try {
            for (int i = 0; true; i++) {
            	System.out.println("Strong test");
                strongRefs.add(new BigData());
            }
        } catch (OutOfMemoryError ofm) { // Strong일 경우 out of memory 발생
            System.out.println("out of memory!");
        }
    }
    
 
    public static void main(String[] args) {
        System.out.println("실행");
        
//        Reference test = new Reference();
//        test.weakReferenceTest();
//        test.softReferenceTest();
//        test.strongReferenceTest();
        
//        new Thread(
//        	new Runnable() {
//				public void run() {
//					Reference r = new Reference();
//					r.weakReferenceTest();
//				}
//			}
//       	).start();
//        
        new Thread(
        	new Runnable() {
				public void run() {
					WeakSoftStrongReference r = new WeakSoftStrongReference();
					r.softReferenceTest();
				}
			}
       	).start();
        
//        new Thread(
//        	new Runnable() {
//				public void run() {
//					Reference r = new Reference();
//					r.strongReferenceTest();
//				}
//			}
//       	).start();
        
        
        System.out.println("종료");
    }
}