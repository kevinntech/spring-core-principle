package hello.core.singleton;

public class StatefulService {

    /*
    * 테스트 코드에서 처음에 10000원이 설정 되었다가 20000원으로 변경된다.
    * */
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        /*
        * 주문을 한 다음, price에 값을 저장해두고 getPrice()로 꺼내려고 한다.
        * */
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제가 된다.
    }

    public int getPrice(){
        return price;
    }

}

