/**
 * 
 */
package in.gov.nha.bis;

import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class Test2 {
	public static void main(String[] args) {
		
		
		if(CheckRedisEnbleFunction.convertArrayToList("24,".split(",")).contains("24")) {
			System.out.println("24");
		}else {
			System.out.println("null");
		}
			
	}

}
