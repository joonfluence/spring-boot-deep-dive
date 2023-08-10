package jdk;

interface MyInterface{
    void doSomething();

    default void doSomethingElse(){
        System.out.println("Doing something else");
    }
}

class MyClass implements MyInterface {
    @Override
    public void doSomething() {
        System.out.println("Doing something");
    }
}

class Main {
    public static void main(String[] args){
        MyClass myObject = new MyClass();
        myObject.doSomething(); // Output: "Doing something"
        myObject.doSomethingElse(); // 따로 오버라이딩하지 않았음에도, 다음과 같이 정상 출력됩니다. Output: "Doing something else"
    }
}