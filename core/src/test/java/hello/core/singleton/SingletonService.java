package hello.core.singleton;

public class SingletonService {

    /*
    * (1) static 영역에 객체를 딱 1개만 생성해둔다.
    *     클래스 변수 : 같은 클래스의 모든 인스턴스들이 공유하는 변수
    * */
    private static final SingletonService instance = new SingletonService();

    // (2) 인스턴스가 필요하면 이 public static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    // (3) 생성자를 private으로 선언해서 외부에서 new 키워드를 사용해서 객체를 생성하지 못하도록 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
