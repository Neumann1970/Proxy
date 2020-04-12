public class Proxy implements Subject {
    ConcreateSubject cs;

    @Override
    public void doSomeWork() {
        System.out.println("Proxy is calling NOW");
        if(cs == null){
            cs = new ConcreateSubject();
        }
        cs.doSomeWork();
    }
}
