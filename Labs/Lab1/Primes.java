public class Primes {
    public static void main(String[] args) {
        for(int r = 2; r <= 100; r++){
            if (isPrime(r)){
                System.out.println(r);
            }
        }    
    } 
    public static boolean isPrime(int n) {
        for(int i = 2; i < n; i++ ){
            if (n % i == 0){
                return false;
            
            }
        
        }
        return true;
    } 
    
}
